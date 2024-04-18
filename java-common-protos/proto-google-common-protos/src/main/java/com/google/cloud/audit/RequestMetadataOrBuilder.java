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
// source: google/cloud/audit/audit_log.proto

// Protobuf Java Version: 3.25.3
package com.google.cloud.audit;

public interface RequestMetadataOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.cloud.audit.RequestMetadata)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * The IP address of the caller.
   * For a caller from the internet, this will be the public IPv4 or IPv6
   * address. For calls made from inside Google's internal production network
   * from one GCP service to another, `caller_ip` will be redacted to "private".
   * For a caller from a Compute Engine VM with a external IP address,
   * `caller_ip` will be the VM's external IP address. For a caller from a
   * Compute Engine VM without a external IP address, if the VM is in the same
   * organization (or project) as the accessed resource, `caller_ip` will be the
   * VM's internal IPv4 address, otherwise `caller_ip` will be redacted to
   * "gce-internal-ip". See https://cloud.google.com/compute/docs/vpc/ for more
   * information.
   * </pre>
   *
   * <code>string caller_ip = 1;</code>
   *
   * @return The callerIp.
   */
  java.lang.String getCallerIp();
  /**
   *
   *
   * <pre>
   * The IP address of the caller.
   * For a caller from the internet, this will be the public IPv4 or IPv6
   * address. For calls made from inside Google's internal production network
   * from one GCP service to another, `caller_ip` will be redacted to "private".
   * For a caller from a Compute Engine VM with a external IP address,
   * `caller_ip` will be the VM's external IP address. For a caller from a
   * Compute Engine VM without a external IP address, if the VM is in the same
   * organization (or project) as the accessed resource, `caller_ip` will be the
   * VM's internal IPv4 address, otherwise `caller_ip` will be redacted to
   * "gce-internal-ip". See https://cloud.google.com/compute/docs/vpc/ for more
   * information.
   * </pre>
   *
   * <code>string caller_ip = 1;</code>
   *
   * @return The bytes for callerIp.
   */
  com.google.protobuf.ByteString getCallerIpBytes();

  /**
   *
   *
   * <pre>
   * The user agent of the caller.
   * This information is not authenticated and should be treated accordingly.
   * For example:
   *
   * +   `google-api-python-client/1.4.0`:
   *     The request was made by the Google API client for Python.
   * +   `Cloud SDK Command Line Tool apitools-client/1.0 gcloud/0.9.62`:
   *     The request was made by the Google Cloud SDK CLI (gcloud).
   * +   `AppEngine-Google; (+http://code.google.com/appengine; appid:
   * s~my-project`:
   *     The request was made from the `my-project` App Engine app.
   * </pre>
   *
   * <code>string caller_supplied_user_agent = 2;</code>
   *
   * @return The callerSuppliedUserAgent.
   */
  java.lang.String getCallerSuppliedUserAgent();
  /**
   *
   *
   * <pre>
   * The user agent of the caller.
   * This information is not authenticated and should be treated accordingly.
   * For example:
   *
   * +   `google-api-python-client/1.4.0`:
   *     The request was made by the Google API client for Python.
   * +   `Cloud SDK Command Line Tool apitools-client/1.0 gcloud/0.9.62`:
   *     The request was made by the Google Cloud SDK CLI (gcloud).
   * +   `AppEngine-Google; (+http://code.google.com/appengine; appid:
   * s~my-project`:
   *     The request was made from the `my-project` App Engine app.
   * </pre>
   *
   * <code>string caller_supplied_user_agent = 2;</code>
   *
   * @return The bytes for callerSuppliedUserAgent.
   */
  com.google.protobuf.ByteString getCallerSuppliedUserAgentBytes();

  /**
   *
   *
   * <pre>
   * The network of the caller.
   * Set only if the network host project is part of the same GCP organization
   * (or project) as the accessed resource.
   * See https://cloud.google.com/compute/docs/vpc/ for more information.
   * This is a scheme-less URI full resource name. For example:
   *
   *     "//compute.googleapis.com/projects/PROJECT_ID/global/networks/NETWORK_ID"
   * </pre>
   *
   * <code>string caller_network = 3;</code>
   *
   * @return The callerNetwork.
   */
  java.lang.String getCallerNetwork();
  /**
   *
   *
   * <pre>
   * The network of the caller.
   * Set only if the network host project is part of the same GCP organization
   * (or project) as the accessed resource.
   * See https://cloud.google.com/compute/docs/vpc/ for more information.
   * This is a scheme-less URI full resource name. For example:
   *
   *     "//compute.googleapis.com/projects/PROJECT_ID/global/networks/NETWORK_ID"
   * </pre>
   *
   * <code>string caller_network = 3;</code>
   *
   * @return The bytes for callerNetwork.
   */
  com.google.protobuf.ByteString getCallerNetworkBytes();

  /**
   *
   *
   * <pre>
   * Request attributes used in IAM condition evaluation. This field contains
   * request attributes like request time and access levels associated with
   * the request.
   *
   *
   * To get the whole view of the attributes used in IAM
   * condition evaluation, the user must also look into
   * `AuditLog.authentication_info.resource_attributes`.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Request request_attributes = 7;</code>
   *
   * @return Whether the requestAttributes field is set.
   */
  boolean hasRequestAttributes();
  /**
   *
   *
   * <pre>
   * Request attributes used in IAM condition evaluation. This field contains
   * request attributes like request time and access levels associated with
   * the request.
   *
   *
   * To get the whole view of the attributes used in IAM
   * condition evaluation, the user must also look into
   * `AuditLog.authentication_info.resource_attributes`.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Request request_attributes = 7;</code>
   *
   * @return The requestAttributes.
   */
  com.google.rpc.context.AttributeContext.Request getRequestAttributes();
  /**
   *
   *
   * <pre>
   * Request attributes used in IAM condition evaluation. This field contains
   * request attributes like request time and access levels associated with
   * the request.
   *
   *
   * To get the whole view of the attributes used in IAM
   * condition evaluation, the user must also look into
   * `AuditLog.authentication_info.resource_attributes`.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Request request_attributes = 7;</code>
   */
  com.google.rpc.context.AttributeContext.RequestOrBuilder getRequestAttributesOrBuilder();

  /**
   *
   *
   * <pre>
   * The destination of a network activity, such as accepting a TCP connection.
   * In a multi hop network activity, the destination represents the receiver of
   * the last hop. Only two fields are used in this message, Peer.port and
   * Peer.ip. These fields are optionally populated by those services utilizing
   * the IAM condition feature.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Peer destination_attributes = 8;</code>
   *
   * @return Whether the destinationAttributes field is set.
   */
  boolean hasDestinationAttributes();
  /**
   *
   *
   * <pre>
   * The destination of a network activity, such as accepting a TCP connection.
   * In a multi hop network activity, the destination represents the receiver of
   * the last hop. Only two fields are used in this message, Peer.port and
   * Peer.ip. These fields are optionally populated by those services utilizing
   * the IAM condition feature.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Peer destination_attributes = 8;</code>
   *
   * @return The destinationAttributes.
   */
  com.google.rpc.context.AttributeContext.Peer getDestinationAttributes();
  /**
   *
   *
   * <pre>
   * The destination of a network activity, such as accepting a TCP connection.
   * In a multi hop network activity, the destination represents the receiver of
   * the last hop. Only two fields are used in this message, Peer.port and
   * Peer.ip. These fields are optionally populated by those services utilizing
   * the IAM condition feature.
   * </pre>
   *
   * <code>.google.rpc.context.AttributeContext.Peer destination_attributes = 8;</code>
   */
  com.google.rpc.context.AttributeContext.PeerOrBuilder getDestinationAttributesOrBuilder();
}
