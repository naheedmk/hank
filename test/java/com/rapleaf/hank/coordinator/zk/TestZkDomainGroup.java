/**
 *  Copyright 2011 Rapleaf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rapleaf.hank.coordinator.zk;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.zookeeper.KeeperException;

import com.rapleaf.hank.ZkTestCase;
import com.rapleaf.hank.coordinator.Domain;
import com.rapleaf.hank.coordinator.DomainGroup;
import com.rapleaf.hank.coordinator.DomainGroupChangeListener;
import com.rapleaf.hank.coordinator.DomainGroupVersion;
import com.rapleaf.hank.coordinator.VersionOrAction;
import com.rapleaf.hank.coordinator.mock.MockDomain;
import com.rapleaf.hank.partitioner.ConstantPartitioner;
import com.rapleaf.hank.partitioner.Murmur64Partitioner;
import com.rapleaf.hank.storage.constant.ConstantStorageEngine;
import com.rapleaf.hank.storage.curly.Curly;
import com.rapleaf.hank.zookeeper.ZkPath;

public class TestZkDomainGroup extends ZkTestCase {
  public class MockDomainGroupChangeListener implements DomainGroupChangeListener {
    public DomainGroup calledWith;

    @Override
    public void onDomainGroupChange(DomainGroup newDomainGroup) {
      this.calledWith = newDomainGroup;
      synchronized (this) {
        notifyAll();
      }
    }
  }

  private final String dg_root = ZkPath.append(getRoot(), "myDomainGroup");
  private final String domains_root = ZkPath.append(getRoot(), "domains");

  public void testLoad() throws Exception {
    Domain d0 = ZkDomain.create(getZk(), domains_root, "domain0", 1024, Curly.Factory.class.getName(), "---", Murmur64Partitioner.class.getName());
    Domain d1 = ZkDomain.create(getZk(), domains_root, "domain1", 1024, Curly.Factory.class.getName(), "---", Murmur64Partitioner.class.getName());

    create(ZkPath.append(dg_root, "domains"));
    create(ZkPath.append(dg_root, "domains/0"), domains_root + "/domain0");
    create(ZkPath.append(dg_root, "domains/1"), domains_root + "/domain1");
    create(ZkPath.append(dg_root, "versions"));
    create(ZkPath.append(dg_root, "versions/v1"));
    create(ZkPath.append(dg_root, "versions/v1/domain0"), "1");
    create(ZkPath.append(dg_root, "versions/v1/domain1"), "1");
    create(ZkPath.append(dg_root, "versions/v1/.complete"), "1");
    create(ZkPath.append(dg_root, "versions/v2"));
    create(ZkPath.append(dg_root, "versions/v2/domain0"), "1");
    create(ZkPath.append(dg_root, "versions/v2/domain1"), "1");

    ZkDomainGroup dgc = new ZkDomainGroup(getZk(), dg_root, null);

    assertEquals(1, dgc.getVersions().size());
    assertEquals(1, ((DomainGroupVersion) dgc.getVersions().toArray()[0]).getVersionNumber());
    assertEquals(1, dgc.getLatestVersion().getVersionNumber());
    assertEquals(Integer.valueOf(0), dgc.getDomainId("domain0"));
    assertEquals(Integer.valueOf(1), dgc.getDomainId("domain1"));
    assertEquals(new HashSet<Domain>(Arrays.asList(d0, d1)), dgc.getDomains());

    assertEquals("domain0", dgc.getDomain(0).getName());
    assertEquals("domain1", dgc.getDomain(1).getName());
  }

  public void testDomainsAndListener() throws Exception {
    DomainGroup dgc = ZkDomainGroup.create(getZk(), dg_root, "myDomainGroup", null);
    MockDomainGroupChangeListener listener = new MockDomainGroupChangeListener();
    dgc.setListener(listener);
    assertNull(listener.calledWith);

    Domain d0 = createDomain("domain0");
    Domain d1 = createDomain("domain1");

    dgc.addDomain(d0, 0);
    assertEquals(Integer.valueOf(0), dgc.getDomainId("domain0"));
    dgc.addDomain(d1, 1);
    assertEquals(Integer.valueOf(1), dgc.getDomainId("domain1"));

    assertNull(listener.calledWith);

    Map<Domain, VersionOrAction> versionMap = new HashMap<Domain, VersionOrAction>() {{
      put(new MockDomain("domain0"), new VersionOrAction(1));
      put(new MockDomain("domain1"), new VersionOrAction(3));
    }};
    dgc.createNewVersion(versionMap);

    synchronized (listener) {
      listener.wait(1000);
    }
    assertNotNull(listener.calledWith);
    assertEquals(dgc.getName(), listener.calledWith.getName());
  }

  public void testDelete() throws Exception {
    ZkDomainGroup dg = ZkDomainGroup.create(getZk(), dg_root, "myDomainGroup", null);
    assertNotNull(getZk().exists(dg.getPath(), false));
    assertTrue(dg.delete());
    assertNull(getZk().exists(dg.getPath(), false));
  }

  private Domain createDomain(String domainName) throws KeeperException, InterruptedException {
    return ZkDomain.create(getZk(),
        domains_root,
        domainName,
        1,
        ConstantStorageEngine.Factory.class.getName(),
        "---\n",
        ConstantPartitioner.class.getName());
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    create(domains_root);
    create(dg_root);
  }
}
