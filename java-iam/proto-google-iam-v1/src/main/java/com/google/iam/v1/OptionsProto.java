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
// source: google/iam/v1/options.proto

// Protobuf Java Version: 3.25.3
package com.google.iam.v1;

public final class OptionsProto {
  private OptionsProto() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_iam_v1_GetPolicyOptions_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_iam_v1_GetPolicyOptions_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    java.lang.String[] descriptorData = {
      "\n\033google/iam/v1/options.proto\022\rgoogle.ia"
          + "m.v1\"4\n\020GetPolicyOptions\022 \n\030requested_po"
          + "licy_version\030\001 \001(\005B}\n\021com.google.iam.v1B"
          + "\014OptionsProtoP\001Z)cloud.google.com/go/iam"
          + "/apiv1/iampb;iampb\370\001\001\252\002\023Google.Cloud.Iam"
          + ".V1\312\002\023Google\\Cloud\\Iam\\V1b\006proto3"
    };
    descriptor =
        com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
            descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[] {});
    internal_static_google_iam_v1_GetPolicyOptions_descriptor =
        getDescriptor().getMessageTypes().get(0);
    internal_static_google_iam_v1_GetPolicyOptions_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_iam_v1_GetPolicyOptions_descriptor,
            new java.lang.String[] {
              "RequestedPolicyVersion",
            });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
