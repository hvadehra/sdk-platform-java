/*
 * Copyright 2024 Google LLC
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
// source: google/api/client.proto

// Protobuf Java Version: 3.25.2
package com.google.api;

/**
 *
 *
 * <pre>
 * Required information for every language.
 * </pre>
 *
 * Protobuf type {@code google.api.CommonLanguageSettings}
 */
public final class CommonLanguageSettings extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.api.CommonLanguageSettings)
    CommonLanguageSettingsOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use CommonLanguageSettings.newBuilder() to construct.
  private CommonLanguageSettings(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CommonLanguageSettings() {
    referenceDocsUri_ = "";
    destinations_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new CommonLanguageSettings();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.api.ClientProto.internal_static_google_api_CommonLanguageSettings_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.api.ClientProto
        .internal_static_google_api_CommonLanguageSettings_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.api.CommonLanguageSettings.class,
            com.google.api.CommonLanguageSettings.Builder.class);
  }

  public static final int REFERENCE_DOCS_URI_FIELD_NUMBER = 1;

  @SuppressWarnings("serial")
  private volatile java.lang.Object referenceDocsUri_ = "";
  /**
   *
   *
   * <pre>
   * Link to automatically generated reference documentation.  Example:
   * https://cloud.google.com/nodejs/docs/reference/asset/latest
   * </pre>
   *
   * <code>string reference_docs_uri = 1 [deprecated = true];</code>
   *
   * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
   *     google/api/client.proto;l=122
   * @return The referenceDocsUri.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.lang.String getReferenceDocsUri() {
    java.lang.Object ref = referenceDocsUri_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      referenceDocsUri_ = s;
      return s;
    }
  }
  /**
   *
   *
   * <pre>
   * Link to automatically generated reference documentation.  Example:
   * https://cloud.google.com/nodejs/docs/reference/asset/latest
   * </pre>
   *
   * <code>string reference_docs_uri = 1 [deprecated = true];</code>
   *
   * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
   *     google/api/client.proto;l=122
   * @return The bytes for referenceDocsUri.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public com.google.protobuf.ByteString getReferenceDocsUriBytes() {
    java.lang.Object ref = referenceDocsUri_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      referenceDocsUri_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESTINATIONS_FIELD_NUMBER = 2;

  @SuppressWarnings("serial")
  private java.util.List<java.lang.Integer> destinations_;

  private static final com.google.protobuf.Internal.ListAdapter.Converter<
          java.lang.Integer, com.google.api.ClientLibraryDestination>
      destinations_converter_ =
          new com.google.protobuf.Internal.ListAdapter.Converter<
              java.lang.Integer, com.google.api.ClientLibraryDestination>() {
            public com.google.api.ClientLibraryDestination convert(java.lang.Integer from) {
              com.google.api.ClientLibraryDestination result =
                  com.google.api.ClientLibraryDestination.forNumber(from);
              return result == null ? com.google.api.ClientLibraryDestination.UNRECOGNIZED : result;
            }
          };
  /**
   *
   *
   * <pre>
   * The destination where API teams want this client library to be published.
   * </pre>
   *
   * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
   *
   * @return A list containing the destinations.
   */
  @java.lang.Override
  public java.util.List<com.google.api.ClientLibraryDestination> getDestinationsList() {
    return new com.google.protobuf.Internal.ListAdapter<
        java.lang.Integer, com.google.api.ClientLibraryDestination>(
        destinations_, destinations_converter_);
  }
  /**
   *
   *
   * <pre>
   * The destination where API teams want this client library to be published.
   * </pre>
   *
   * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
   *
   * @return The count of destinations.
   */
  @java.lang.Override
  public int getDestinationsCount() {
    return destinations_.size();
  }
  /**
   *
   *
   * <pre>
   * The destination where API teams want this client library to be published.
   * </pre>
   *
   * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
   *
   * @param index The index of the element to return.
   * @return The destinations at the given index.
   */
  @java.lang.Override
  public com.google.api.ClientLibraryDestination getDestinations(int index) {
    return destinations_converter_.convert(destinations_.get(index));
  }
  /**
   *
   *
   * <pre>
   * The destination where API teams want this client library to be published.
   * </pre>
   *
   * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
   *
   * @return A list containing the enum numeric values on the wire for destinations.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer> getDestinationsValueList() {
    return destinations_;
  }
  /**
   *
   *
   * <pre>
   * The destination where API teams want this client library to be published.
   * </pre>
   *
   * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
   *
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of destinations at the given index.
   */
  @java.lang.Override
  public int getDestinationsValue(int index) {
    return destinations_.get(index);
  }

  private int destinationsMemoizedSerializedSize;

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
    getSerializedSize();
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(referenceDocsUri_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, referenceDocsUri_);
    }
    if (getDestinationsList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(destinationsMemoizedSerializedSize);
    }
    for (int i = 0; i < destinations_.size(); i++) {
      output.writeEnumNoTag(destinations_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(referenceDocsUri_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, referenceDocsUri_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < destinations_.size(); i++) {
        dataSize +=
            com.google.protobuf.CodedOutputStream.computeEnumSizeNoTag(destinations_.get(i));
      }
      size += dataSize;
      if (!getDestinationsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(dataSize);
      }
      destinationsMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof com.google.api.CommonLanguageSettings)) {
      return super.equals(obj);
    }
    com.google.api.CommonLanguageSettings other = (com.google.api.CommonLanguageSettings) obj;

    if (!getReferenceDocsUri().equals(other.getReferenceDocsUri())) return false;
    if (!destinations_.equals(other.destinations_)) return false;
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
    hash = (37 * hash) + REFERENCE_DOCS_URI_FIELD_NUMBER;
    hash = (53 * hash) + getReferenceDocsUri().hashCode();
    if (getDestinationsCount() > 0) {
      hash = (37 * hash) + DESTINATIONS_FIELD_NUMBER;
      hash = (53 * hash) + destinations_.hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.api.CommonLanguageSettings parseFrom(java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.api.CommonLanguageSettings parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.api.CommonLanguageSettings parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
      com.google.protobuf.CodedInputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.api.CommonLanguageSettings parseFrom(
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

  public static Builder newBuilder(com.google.api.CommonLanguageSettings prototype) {
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
   * Required information for every language.
   * </pre>
   *
   * Protobuf type {@code google.api.CommonLanguageSettings}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.api.CommonLanguageSettings)
      com.google.api.CommonLanguageSettingsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.api.ClientProto
          .internal_static_google_api_CommonLanguageSettings_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.api.ClientProto
          .internal_static_google_api_CommonLanguageSettings_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.api.CommonLanguageSettings.class,
              com.google.api.CommonLanguageSettings.Builder.class);
    }

    // Construct using com.google.api.CommonLanguageSettings.newBuilder()
    private Builder() {}

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      referenceDocsUri_ = "";
      destinations_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.api.ClientProto
          .internal_static_google_api_CommonLanguageSettings_descriptor;
    }

    @java.lang.Override
    public com.google.api.CommonLanguageSettings getDefaultInstanceForType() {
      return com.google.api.CommonLanguageSettings.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.api.CommonLanguageSettings build() {
      com.google.api.CommonLanguageSettings result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.api.CommonLanguageSettings buildPartial() {
      com.google.api.CommonLanguageSettings result =
          new com.google.api.CommonLanguageSettings(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.google.api.CommonLanguageSettings result) {
      if (((bitField0_ & 0x00000002) != 0)) {
        destinations_ = java.util.Collections.unmodifiableList(destinations_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.destinations_ = destinations_;
    }

    private void buildPartial0(com.google.api.CommonLanguageSettings result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.referenceDocsUri_ = referenceDocsUri_;
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
      if (other instanceof com.google.api.CommonLanguageSettings) {
        return mergeFrom((com.google.api.CommonLanguageSettings) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.api.CommonLanguageSettings other) {
      if (other == com.google.api.CommonLanguageSettings.getDefaultInstance()) return this;
      if (!other.getReferenceDocsUri().isEmpty()) {
        referenceDocsUri_ = other.referenceDocsUri_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.destinations_.isEmpty()) {
        if (destinations_.isEmpty()) {
          destinations_ = other.destinations_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureDestinationsIsMutable();
          destinations_.addAll(other.destinations_);
        }
        onChanged();
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
                referenceDocsUri_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
            case 16:
              {
                int tmpRaw = input.readEnum();
                ensureDestinationsIsMutable();
                destinations_.add(tmpRaw);
                break;
              } // case 16
            case 18:
              {
                int length = input.readRawVarint32();
                int oldLimit = input.pushLimit(length);
                while (input.getBytesUntilLimit() > 0) {
                  int tmpRaw = input.readEnum();
                  ensureDestinationsIsMutable();
                  destinations_.add(tmpRaw);
                }
                input.popLimit(oldLimit);
                break;
              } // case 18
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

    private java.lang.Object referenceDocsUri_ = "";
    /**
     *
     *
     * <pre>
     * Link to automatically generated reference documentation.  Example:
     * https://cloud.google.com/nodejs/docs/reference/asset/latest
     * </pre>
     *
     * <code>string reference_docs_uri = 1 [deprecated = true];</code>
     *
     * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
     *     google/api/client.proto;l=122
     * @return The referenceDocsUri.
     */
    @java.lang.Deprecated
    public java.lang.String getReferenceDocsUri() {
      java.lang.Object ref = referenceDocsUri_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        referenceDocsUri_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Link to automatically generated reference documentation.  Example:
     * https://cloud.google.com/nodejs/docs/reference/asset/latest
     * </pre>
     *
     * <code>string reference_docs_uri = 1 [deprecated = true];</code>
     *
     * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
     *     google/api/client.proto;l=122
     * @return The bytes for referenceDocsUri.
     */
    @java.lang.Deprecated
    public com.google.protobuf.ByteString getReferenceDocsUriBytes() {
      java.lang.Object ref = referenceDocsUri_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
        referenceDocsUri_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Link to automatically generated reference documentation.  Example:
     * https://cloud.google.com/nodejs/docs/reference/asset/latest
     * </pre>
     *
     * <code>string reference_docs_uri = 1 [deprecated = true];</code>
     *
     * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
     *     google/api/client.proto;l=122
     * @param value The referenceDocsUri to set.
     * @return This builder for chaining.
     */
    @java.lang.Deprecated
    public Builder setReferenceDocsUri(java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }
      referenceDocsUri_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Link to automatically generated reference documentation.  Example:
     * https://cloud.google.com/nodejs/docs/reference/asset/latest
     * </pre>
     *
     * <code>string reference_docs_uri = 1 [deprecated = true];</code>
     *
     * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
     *     google/api/client.proto;l=122
     * @return This builder for chaining.
     */
    @java.lang.Deprecated
    public Builder clearReferenceDocsUri() {
      referenceDocsUri_ = getDefaultInstance().getReferenceDocsUri();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Link to automatically generated reference documentation.  Example:
     * https://cloud.google.com/nodejs/docs/reference/asset/latest
     * </pre>
     *
     * <code>string reference_docs_uri = 1 [deprecated = true];</code>
     *
     * @deprecated google.api.CommonLanguageSettings.reference_docs_uri is deprecated. See
     *     google/api/client.proto;l=122
     * @param value The bytes for referenceDocsUri to set.
     * @return This builder for chaining.
     */
    @java.lang.Deprecated
    public Builder setReferenceDocsUriBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);
      referenceDocsUri_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> destinations_ = java.util.Collections.emptyList();

    private void ensureDestinationsIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        destinations_ = new java.util.ArrayList<java.lang.Integer>(destinations_);
        bitField0_ |= 0x00000002;
      }
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @return A list containing the destinations.
     */
    public java.util.List<com.google.api.ClientLibraryDestination> getDestinationsList() {
      return new com.google.protobuf.Internal.ListAdapter<
          java.lang.Integer, com.google.api.ClientLibraryDestination>(
          destinations_, destinations_converter_);
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @return The count of destinations.
     */
    public int getDestinationsCount() {
      return destinations_.size();
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The destinations at the given index.
     */
    public com.google.api.ClientLibraryDestination getDestinations(int index) {
      return destinations_converter_.convert(destinations_.get(index));
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The destinations to set.
     * @return This builder for chaining.
     */
    public Builder setDestinations(int index, com.google.api.ClientLibraryDestination value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureDestinationsIsMutable();
      destinations_.set(index, value.getNumber());
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param value The destinations to add.
     * @return This builder for chaining.
     */
    public Builder addDestinations(com.google.api.ClientLibraryDestination value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureDestinationsIsMutable();
      destinations_.add(value.getNumber());
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param values The destinations to add.
     * @return This builder for chaining.
     */
    public Builder addAllDestinations(
        java.lang.Iterable<? extends com.google.api.ClientLibraryDestination> values) {
      ensureDestinationsIsMutable();
      for (com.google.api.ClientLibraryDestination value : values) {
        destinations_.add(value.getNumber());
      }
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearDestinations() {
      destinations_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @return A list containing the enum numeric values on the wire for destinations.
     */
    public java.util.List<java.lang.Integer> getDestinationsValueList() {
      return java.util.Collections.unmodifiableList(destinations_);
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of destinations at the given index.
     */
    public int getDestinationsValue(int index) {
      return destinations_.get(index);
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The enum numeric value on the wire for destinations to set.
     * @return This builder for chaining.
     */
    public Builder setDestinationsValue(int index, int value) {
      ensureDestinationsIsMutable();
      destinations_.set(index, value);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param value The enum numeric value on the wire for destinations to add.
     * @return This builder for chaining.
     */
    public Builder addDestinationsValue(int value) {
      ensureDestinationsIsMutable();
      destinations_.add(value);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * The destination where API teams want this client library to be published.
     * </pre>
     *
     * <code>repeated .google.api.ClientLibraryDestination destinations = 2;</code>
     *
     * @param values The enum numeric values on the wire for destinations to add.
     * @return This builder for chaining.
     */
    public Builder addAllDestinationsValue(java.lang.Iterable<java.lang.Integer> values) {
      ensureDestinationsIsMutable();
      for (int value : values) {
        destinations_.add(value);
      }
      onChanged();
      return this;
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

    // @@protoc_insertion_point(builder_scope:google.api.CommonLanguageSettings)
  }

  // @@protoc_insertion_point(class_scope:google.api.CommonLanguageSettings)
  private static final com.google.api.CommonLanguageSettings DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.api.CommonLanguageSettings();
  }

  public static com.google.api.CommonLanguageSettings getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommonLanguageSettings> PARSER =
      new com.google.protobuf.AbstractParser<CommonLanguageSettings>() {
        @java.lang.Override
        public CommonLanguageSettings parsePartialFrom(
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

  public static com.google.protobuf.Parser<CommonLanguageSettings> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommonLanguageSettings> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.api.CommonLanguageSettings getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
