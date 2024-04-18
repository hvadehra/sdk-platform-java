/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/control.proto

// Protobuf Java Version: 3.25.3
package com.google.api;

/**
 *
 *
 * <pre>
 * Selects and configures the service controller used by the service.
 *
 * Example:
 *
 *     control:
 *       environment: servicecontrol.googleapis.com
 * </pre>
 *
 * Protobuf type {@code google.api.Control}
 */
public final class Control extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.api.Control)
    ControlOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use Control.newBuilder() to construct.
  private Control(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private Control() {
    environment_ = "";
    methodPolicies_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new Control();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.api.ControlProto.internal_static_google_api_Control_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.api.ControlProto.internal_static_google_api_Control_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.api.Control.class, com.google.api.Control.Builder.class);
  }

  public static final int ENVIRONMENT_FIELD_NUMBER = 1;

  @SuppressWarnings("serial")
  private volatile java.lang.Object environment_ = "";
  /**
   *
   *
   * <pre>
   * The service controller environment to use. If empty, no control plane
   * feature (like quota and billing) will be enabled. The recommended value for
   * most services is servicecontrol.googleapis.com
   * </pre>
   *
   * <code>string environment = 1;</code>
   *
   * @return The environment.
   */
  @java.lang.Override
  public java.lang.String getEnvironment() {
    java.lang.Object ref = environment_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      environment_ = s;
      return s;
    }
  }
  /**
   *
   *
   * <pre>
   * The service controller environment to use. If empty, no control plane
   * feature (like quota and billing) will be enabled. The recommended value for
   * most services is servicecontrol.googleapis.com
   * </pre>
   *
   * <code>string environment = 1;</code>
   *
   * @return The bytes for environment.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getEnvironmentBytes() {
    java.lang.Object ref = environment_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      environment_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int METHOD_POLICIES_FIELD_NUMBER = 4;

  @SuppressWarnings("serial")
  private java.util.List<com.google.api.MethodPolicy> methodPolicies_;
  /**
   *
   *
   * <pre>
   * Defines policies applying to the API methods of the service.
   * </pre>
   *
   * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.api.MethodPolicy> getMethodPoliciesList() {
    return methodPolicies_;
  }
  /**
   *
   *
   * <pre>
   * Defines policies applying to the API methods of the service.
   * </pre>
   *
   * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.google.api.MethodPolicyOrBuilder>
      getMethodPoliciesOrBuilderList() {
    return methodPolicies_;
  }
  /**
   *
   *
   * <pre>
   * Defines policies applying to the API methods of the service.
   * </pre>
   *
   * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
   */
  @java.lang.Override
  public int getMethodPoliciesCount() {
    return methodPolicies_.size();
  }
  /**
   *
   *
   * <pre>
   * Defines policies applying to the API methods of the service.
   * </pre>
   *
   * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
   */
  @java.lang.Override
  public com.google.api.MethodPolicy getMethodPolicies(int index) {
    return methodPolicies_.get(index);
  }
  /**
   *
   *
   * <pre>
   * Defines policies applying to the API methods of the service.
   * </pre>
   *
   * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
   */
  @java.lang.Override
  public com.google.api.MethodPolicyOrBuilder getMethodPoliciesOrBuilder(int index) {
    return methodPolicies_.get(index);
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(environment_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, environment_);
    }
    for (int i = 0; i < methodPolicies_.size(); i++) {
      output.writeMessage(4, methodPolicies_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(environment_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, environment_);
    }
    for (int i = 0; i < methodPolicies_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, methodPolicies_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof com.google.api.Control)) {
      return super.equals(obj);
    }
    com.google.api.Control other = (com.google.api.Control) obj;

    if (!getEnvironment().equals(other.getEnvironment())) return false;
    if (!getMethodPoliciesList().equals(other.getMethodPoliciesList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ENVIRONMENT_FIELD_NUMBER;
    hash = (53 * hash) + getEnvironment().hashCode();
    if (getMethodPoliciesCount() > 0) {
      hash = (37 * hash) + METHOD_POLICIES_FIELD_NUMBER;
      hash = (53 * hash) + getMethodPoliciesList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.api.Control parseFrom(java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.Control parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.Control parseFrom(com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.Control parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.Control parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.Control parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.Control parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.api.Control parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.api.Control parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.api.Control parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.api.Control parseFrom(com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.api.Control parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(com.google.api.Control prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   *
   *
   * <pre>
   * Selects and configures the service controller used by the service.
   *
   * Example:
   *
   *     control:
   *       environment: servicecontrol.googleapis.com
   * </pre>
   *
   * Protobuf type {@code google.api.Control}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.api.Control)
      com.google.api.ControlOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.api.ControlProto.internal_static_google_api_Control_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.api.ControlProto.internal_static_google_api_Control_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.api.Control.class, com.google.api.Control.Builder.class);
    }

    // Construct using com.google.api.Control.newBuilder()
    private Builder() {}

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      environment_ = "";
      if (methodPoliciesBuilder_ == null) {
        methodPolicies_ = java.util.Collections.emptyList();
      } else {
        methodPolicies_ = null;
        methodPoliciesBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.api.ControlProto.internal_static_google_api_Control_descriptor;
    }

    @java.lang.Override
    public com.google.api.Control getDefaultInstanceForType() {
      return com.google.api.Control.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.api.Control build() {
      com.google.api.Control result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.api.Control buildPartial() {
      com.google.api.Control result = new com.google.api.Control(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.google.api.Control result) {
      if (methodPoliciesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          methodPolicies_ = java.util.Collections.unmodifiableList(methodPolicies_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.methodPolicies_ = methodPolicies_;
      } else {
        result.methodPolicies_ = methodPoliciesBuilder_.build();
      }
    }

    private void buildPartial0(com.google.api.Control result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.environment_ = environment_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.api.Control) {
        return mergeFrom((com.google.api.Control) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.api.Control other) {
      if (other == com.google.api.Control.getDefaultInstance()) return this;
      if (!other.getEnvironment().isEmpty()) {
        environment_ = other.environment_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (methodPoliciesBuilder_ == null) {
        if (!other.methodPolicies_.isEmpty()) {
          if (methodPolicies_.isEmpty()) {
            methodPolicies_ = other.methodPolicies_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureMethodPoliciesIsMutable();
            methodPolicies_.addAll(other.methodPolicies_);
          }
          onChanged();
        }
      } else {
        if (!other.methodPolicies_.isEmpty()) {
          if (methodPoliciesBuilder_.isEmpty()) {
            methodPoliciesBuilder_.dispose();
            methodPoliciesBuilder_ = null;
            methodPolicies_ = other.methodPolicies_;
            bitField0_ = (bitField0_ & ~0x00000002);
            methodPoliciesBuilder_ =
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                    ? getMethodPoliciesFieldBuilder()
                    : null;
          } else {
            methodPoliciesBuilder_.addAllMessages(other.methodPolicies_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10:
              {
                environment_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
            case 34:
              {
                com.google.api.MethodPolicy m =
                    input.readMessage(com.google.api.MethodPolicy.parser(), extensionRegistry);
                if (methodPoliciesBuilder_ == null) {
                  ensureMethodPoliciesIsMutable();
                  methodPolicies_.add(m);
                } else {
                  methodPoliciesBuilder_.addMessage(m);
                }
                break;
              } // case 34
            default:
              {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    private int bitField0_;

    private java.lang.Object environment_ = "";
    /**
     *
     *
     * <pre>
     * The service controller environment to use. If empty, no control plane
     * feature (like quota and billing) will be enabled. The recommended value for
     * most services is servicecontrol.googleapis.com
     * </pre>
     *
     * <code>string environment = 1;</code>
     *
     * @return The environment.
     */
    public java.lang.String getEnvironment() {
      java.lang.Object ref = environment_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        environment_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * The service controller environment to use. If empty, no control plane
     * feature (like quota and billing) will be enabled. The recommended value for
     * most services is servicecontrol.googleapis.com
     * </pre>
     *
     * <code>string environment = 1;</code>
     *
     * @return The bytes for environment.
     */
    public com.google.protobuf.ByteString getEnvironmentBytes() {
      java.lang.Object ref = environment_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
        environment_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * The service controller environment to use. If empty, no control plane
     * feature (like quota and billing) will be enabled. The recommended value for
     * most services is servicecontrol.googleapis.com
     * </pre>
     *
     * <code>string environment = 1;</code>
     *
     * @param value The environment to set.
     * @return This builder for chaining.
     */
    public Builder setEnvironment(java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }
      environment_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The service controller environment to use. If empty, no control plane
     * feature (like quota and billing) will be enabled. The recommended value for
     * most services is servicecontrol.googleapis.com
     * </pre>
     *
     * <code>string environment = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearEnvironment() {
      environment_ = getDefaultInstance().getEnvironment();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The service controller environment to use. If empty, no control plane
     * feature (like quota and billing) will be enabled. The recommended value for
     * most services is servicecontrol.googleapis.com
     * </pre>
     *
     * <code>string environment = 1;</code>
     *
     * @param value The bytes for environment to set.
     * @return This builder for chaining.
     */
    public Builder setEnvironmentBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);
      environment_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.util.List<com.google.api.MethodPolicy> methodPolicies_ =
        java.util.Collections.emptyList();

    private void ensureMethodPoliciesIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        methodPolicies_ = new java.util.ArrayList<com.google.api.MethodPolicy>(methodPolicies_);
        bitField0_ |= 0x00000002;
      }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.api.MethodPolicy,
            com.google.api.MethodPolicy.Builder,
            com.google.api.MethodPolicyOrBuilder>
        methodPoliciesBuilder_;

    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public java.util.List<com.google.api.MethodPolicy> getMethodPoliciesList() {
      if (methodPoliciesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(methodPolicies_);
      } else {
        return methodPoliciesBuilder_.getMessageList();
      }
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public int getMethodPoliciesCount() {
      if (methodPoliciesBuilder_ == null) {
        return methodPolicies_.size();
      } else {
        return methodPoliciesBuilder_.getCount();
      }
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public com.google.api.MethodPolicy getMethodPolicies(int index) {
      if (methodPoliciesBuilder_ == null) {
        return methodPolicies_.get(index);
      } else {
        return methodPoliciesBuilder_.getMessage(index);
      }
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder setMethodPolicies(int index, com.google.api.MethodPolicy value) {
      if (methodPoliciesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMethodPoliciesIsMutable();
        methodPolicies_.set(index, value);
        onChanged();
      } else {
        methodPoliciesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder setMethodPolicies(
        int index, com.google.api.MethodPolicy.Builder builderForValue) {
      if (methodPoliciesBuilder_ == null) {
        ensureMethodPoliciesIsMutable();
        methodPolicies_.set(index, builderForValue.build());
        onChanged();
      } else {
        methodPoliciesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder addMethodPolicies(com.google.api.MethodPolicy value) {
      if (methodPoliciesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMethodPoliciesIsMutable();
        methodPolicies_.add(value);
        onChanged();
      } else {
        methodPoliciesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder addMethodPolicies(int index, com.google.api.MethodPolicy value) {
      if (methodPoliciesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMethodPoliciesIsMutable();
        methodPolicies_.add(index, value);
        onChanged();
      } else {
        methodPoliciesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder addMethodPolicies(com.google.api.MethodPolicy.Builder builderForValue) {
      if (methodPoliciesBuilder_ == null) {
        ensureMethodPoliciesIsMutable();
        methodPolicies_.add(builderForValue.build());
        onChanged();
      } else {
        methodPoliciesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder addMethodPolicies(
        int index, com.google.api.MethodPolicy.Builder builderForValue) {
      if (methodPoliciesBuilder_ == null) {
        ensureMethodPoliciesIsMutable();
        methodPolicies_.add(index, builderForValue.build());
        onChanged();
      } else {
        methodPoliciesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder addAllMethodPolicies(
        java.lang.Iterable<? extends com.google.api.MethodPolicy> values) {
      if (methodPoliciesBuilder_ == null) {
        ensureMethodPoliciesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(values, methodPolicies_);
        onChanged();
      } else {
        methodPoliciesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder clearMethodPolicies() {
      if (methodPoliciesBuilder_ == null) {
        methodPolicies_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        methodPoliciesBuilder_.clear();
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public Builder removeMethodPolicies(int index) {
      if (methodPoliciesBuilder_ == null) {
        ensureMethodPoliciesIsMutable();
        methodPolicies_.remove(index);
        onChanged();
      } else {
        methodPoliciesBuilder_.remove(index);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public com.google.api.MethodPolicy.Builder getMethodPoliciesBuilder(int index) {
      return getMethodPoliciesFieldBuilder().getBuilder(index);
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public com.google.api.MethodPolicyOrBuilder getMethodPoliciesOrBuilder(int index) {
      if (methodPoliciesBuilder_ == null) {
        return methodPolicies_.get(index);
      } else {
        return methodPoliciesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public java.util.List<? extends com.google.api.MethodPolicyOrBuilder>
        getMethodPoliciesOrBuilderList() {
      if (methodPoliciesBuilder_ != null) {
        return methodPoliciesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(methodPolicies_);
      }
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public com.google.api.MethodPolicy.Builder addMethodPoliciesBuilder() {
      return getMethodPoliciesFieldBuilder()
          .addBuilder(com.google.api.MethodPolicy.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public com.google.api.MethodPolicy.Builder addMethodPoliciesBuilder(int index) {
      return getMethodPoliciesFieldBuilder()
          .addBuilder(index, com.google.api.MethodPolicy.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * Defines policies applying to the API methods of the service.
     * </pre>
     *
     * <code>repeated .google.api.MethodPolicy method_policies = 4;</code>
     */
    public java.util.List<com.google.api.MethodPolicy.Builder> getMethodPoliciesBuilderList() {
      return getMethodPoliciesFieldBuilder().getBuilderList();
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.api.MethodPolicy,
            com.google.api.MethodPolicy.Builder,
            com.google.api.MethodPolicyOrBuilder>
        getMethodPoliciesFieldBuilder() {
      if (methodPoliciesBuilder_ == null) {
        methodPoliciesBuilder_ =
            new com.google.protobuf.RepeatedFieldBuilderV3<
                com.google.api.MethodPolicy,
                com.google.api.MethodPolicy.Builder,
                com.google.api.MethodPolicyOrBuilder>(
                methodPolicies_,
                ((bitField0_ & 0x00000002) != 0),
                getParentForChildren(),
                isClean());
        methodPolicies_ = null;
      }
      return methodPoliciesBuilder_;
    }

    @java.lang.Override
    public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:google.api.Control)
  }

  // @@protoc_insertion_point(class_scope:google.api.Control)
  private static final com.google.api.Control DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.api.Control();
  }

  public static com.google.api.Control getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Control> PARSER =
      new com.google.protobuf.AbstractParser<Control>() {
        @java.lang.Override
        public Control parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          Builder builder = newBuilder();
          try {
            builder.mergeFrom(input, extensionRegistry);
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
          } catch (com.google.protobuf.UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
          } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e)
                .setUnfinishedMessage(builder.buildPartial());
          }
          return builder.buildPartial();
        }
      };

  public static com.google.protobuf.Parser<Control> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Control> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.api.Control getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
