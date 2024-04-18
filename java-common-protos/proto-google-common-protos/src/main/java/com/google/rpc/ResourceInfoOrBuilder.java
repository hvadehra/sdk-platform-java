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
// source: google/rpc/error_details.proto

// Protobuf Java Version: 3.25.3
package com.google.rpc;

public interface ResourceInfoOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.rpc.ResourceInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * A name for the type of resource being accessed, e.g. "sql table",
   * "cloud storage bucket", "file", "Google calendar"; or the type URL
   * of the resource: e.g. "type.googleapis.com/google.pubsub.v1.Topic".
   * </pre>
   *
   * <code>string resource_type = 1;</code>
   *
   * @return The resourceType.
   */
  java.lang.String getResourceType();
  /**
   *
   *
   * <pre>
   * A name for the type of resource being accessed, e.g. "sql table",
   * "cloud storage bucket", "file", "Google calendar"; or the type URL
   * of the resource: e.g. "type.googleapis.com/google.pubsub.v1.Topic".
   * </pre>
   *
   * <code>string resource_type = 1;</code>
   *
   * @return The bytes for resourceType.
   */
  com.google.protobuf.ByteString getResourceTypeBytes();

  /**
   *
   *
   * <pre>
   * The name of the resource being accessed.  For example, a shared calendar
   * name: "example.com_4fghdhgsrgh&#64;group.calendar.google.com", if the current
   * error is
   * [google.rpc.Code.PERMISSION_DENIED][google.rpc.Code.PERMISSION_DENIED].
   * </pre>
   *
   * <code>string resource_name = 2;</code>
   *
   * @return The resourceName.
   */
  java.lang.String getResourceName();
  /**
   *
   *
   * <pre>
   * The name of the resource being accessed.  For example, a shared calendar
   * name: "example.com_4fghdhgsrgh&#64;group.calendar.google.com", if the current
   * error is
   * [google.rpc.Code.PERMISSION_DENIED][google.rpc.Code.PERMISSION_DENIED].
   * </pre>
   *
   * <code>string resource_name = 2;</code>
   *
   * @return The bytes for resourceName.
   */
  com.google.protobuf.ByteString getResourceNameBytes();

  /**
   *
   *
   * <pre>
   * The owner of the resource (optional).
   * For example, "user:&lt;owner email&gt;" or "project:&lt;Google developer project
   * id&gt;".
   * </pre>
   *
   * <code>string owner = 3;</code>
   *
   * @return The owner.
   */
  java.lang.String getOwner();
  /**
   *
   *
   * <pre>
   * The owner of the resource (optional).
   * For example, "user:&lt;owner email&gt;" or "project:&lt;Google developer project
   * id&gt;".
   * </pre>
   *
   * <code>string owner = 3;</code>
   *
   * @return The bytes for owner.
   */
  com.google.protobuf.ByteString getOwnerBytes();

  /**
   *
   *
   * <pre>
   * Describes what error is encountered when accessing this resource.
   * For example, updating a cloud project may require the `writer` permission
   * on the developer console project.
   * </pre>
   *
   * <code>string description = 4;</code>
   *
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   *
   *
   * <pre>
   * Describes what error is encountered when accessing this resource.
   * For example, updating a cloud project may require the `writer` permission
   * on the developer console project.
   * </pre>
   *
   * <code>string description = 4;</code>
   *
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString getDescriptionBytes();
}
