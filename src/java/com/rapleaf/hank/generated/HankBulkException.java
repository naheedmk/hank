/**
 * Autogenerated by Thrift Compiler (0.7.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.hank.generated;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HankBulkException extends org.apache.thrift.TUnion<HankBulkException, HankBulkException._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HankBulkException");
  private static final org.apache.thrift.protocol.TField NO_SUCH_DOMAIN_FIELD_DESC = new org.apache.thrift.protocol.TField("no_such_domain", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField INTERNAL_ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("internal_error", org.apache.thrift.protocol.TType.STRING, (short)2);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * The domain passed in the request does not correspond to a valid domain
     */
    NO_SUCH_DOMAIN((short)1, "no_such_domain"),
    /**
     * There was some internal error in the server. This is pretty bad.
     */
    INTERNAL_ERROR((short)2, "internal_error");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NO_SUCH_DOMAIN
          return NO_SUCH_DOMAIN;
        case 2: // INTERNAL_ERROR
          return INTERNAL_ERROR;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NO_SUCH_DOMAIN, new org.apache.thrift.meta_data.FieldMetaData("no_such_domain", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.INTERNAL_ERROR, new org.apache.thrift.meta_data.FieldMetaData("internal_error", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HankBulkException.class, metaDataMap);
  }

  public HankBulkException() {
    super();
  }

  public HankBulkException(_Fields setField, Object value) {
    super(setField, value);
  }

  public HankBulkException(HankBulkException other) {
    super(other);
  }
  public HankBulkException deepCopy() {
    return new HankBulkException(this);
  }

  public static HankBulkException no_such_domain(boolean value) {
    HankBulkException x = new HankBulkException();
    x.setNo_such_domain(value);
    return x;
  }

  public static HankBulkException internal_error(String value) {
    HankBulkException x = new HankBulkException();
    x.setInternal_error(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case NO_SUCH_DOMAIN:
        if (value instanceof Boolean) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Boolean for field 'no_such_domain', but got " + value.getClass().getSimpleName());
      case INTERNAL_ERROR:
        if (value instanceof String) {
          break;
        }
        throw new ClassCastException("Was expecting value of type String for field 'internal_error', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object readValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case NO_SUCH_DOMAIN:
          if (field.type == NO_SUCH_DOMAIN_FIELD_DESC.type) {
            Boolean no_such_domain;
            no_such_domain = iprot.readBool();
            return no_such_domain;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case INTERNAL_ERROR:
          if (field.type == INTERNAL_ERROR_FIELD_DESC.type) {
            String internal_error;
            internal_error = iprot.readString();
            return internal_error;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void writeValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case NO_SUCH_DOMAIN:
        Boolean no_such_domain = (Boolean)value_;
        oprot.writeBool(no_such_domain);
        return;
      case INTERNAL_ERROR:
        String internal_error = (String)value_;
        oprot.writeString(internal_error);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case NO_SUCH_DOMAIN:
        return NO_SUCH_DOMAIN_FIELD_DESC;
      case INTERNAL_ERROR:
        return INTERNAL_ERROR_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  /**
   * The domain passed in the request does not correspond to a valid domain
   */
  public boolean getNo_such_domain() {
    if (getSetField() == _Fields.NO_SUCH_DOMAIN) {
      return (Boolean)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'no_such_domain' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  /**
   * The domain passed in the request does not correspond to a valid domain
   */
  public void setNo_such_domain(boolean value) {
    setField_ = _Fields.NO_SUCH_DOMAIN;
    value_ = value;
  }

  /**
   * There was some internal error in the server. This is pretty bad.
   */
  public String getInternal_error() {
    if (getSetField() == _Fields.INTERNAL_ERROR) {
      return (String)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'internal_error' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  /**
   * There was some internal error in the server. This is pretty bad.
   */
  public void setInternal_error(String value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.INTERNAL_ERROR;
    value_ = value;
  }

  public boolean isSetNo_such_domain() {
    return setField_ == _Fields.NO_SUCH_DOMAIN;
  }


  public boolean isSetInternal_error() {
    return setField_ == _Fields.INTERNAL_ERROR;
  }


  public boolean equals(Object other) {
    if (other instanceof HankBulkException) {
      return equals((HankBulkException)other);
    } else {
      return false;
    }
  }

  public boolean equals(HankBulkException other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(HankBulkException other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  /**
   * If you'd like this to perform more respectably, use the hashcode generator option.
   */
  @Override
  public int hashCode() {
    return 0;
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
