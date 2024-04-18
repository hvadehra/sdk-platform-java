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
// source: google/api/routing.proto

// Protobuf Java Version: 3.25.3
package com.google.api;

public interface RoutingRuleOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.api.RoutingRule)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * A collection of Routing Parameter specifications.
   * **NOTE:** If multiple Routing Parameters describe the same key
   * (via the `path_template` field or via the `field` field when
   * `path_template` is not provided), "last one wins" rule
   * determines which Parameter gets used.
   * See the examples for more details.
   * </pre>
   *
   * <code>repeated .google.api.RoutingParameter routing_parameters = 2;</code>
   */
  java.util.List<com.google.api.RoutingParameter> getRoutingParametersList();
  /**
   *
   *
   * <pre>
   * A collection of Routing Parameter specifications.
   * **NOTE:** If multiple Routing Parameters describe the same key
   * (via the `path_template` field or via the `field` field when
   * `path_template` is not provided), "last one wins" rule
   * determines which Parameter gets used.
   * See the examples for more details.
   * </pre>
   *
   * <code>repeated .google.api.RoutingParameter routing_parameters = 2;</code>
   */
  com.google.api.RoutingParameter getRoutingParameters(int index);
  /**
   *
   *
   * <pre>
   * A collection of Routing Parameter specifications.
   * **NOTE:** If multiple Routing Parameters describe the same key
   * (via the `path_template` field or via the `field` field when
   * `path_template` is not provided), "last one wins" rule
   * determines which Parameter gets used.
   * See the examples for more details.
   * </pre>
   *
   * <code>repeated .google.api.RoutingParameter routing_parameters = 2;</code>
   */
  int getRoutingParametersCount();
  /**
   *
   *
   * <pre>
   * A collection of Routing Parameter specifications.
   * **NOTE:** If multiple Routing Parameters describe the same key
   * (via the `path_template` field or via the `field` field when
   * `path_template` is not provided), "last one wins" rule
   * determines which Parameter gets used.
   * See the examples for more details.
   * </pre>
   *
   * <code>repeated .google.api.RoutingParameter routing_parameters = 2;</code>
   */
  java.util.List<? extends com.google.api.RoutingParameterOrBuilder>
      getRoutingParametersOrBuilderList();
  /**
   *
   *
   * <pre>
   * A collection of Routing Parameter specifications.
   * **NOTE:** If multiple Routing Parameters describe the same key
   * (via the `path_template` field or via the `field` field when
   * `path_template` is not provided), "last one wins" rule
   * determines which Parameter gets used.
   * See the examples for more details.
   * </pre>
   *
   * <code>repeated .google.api.RoutingParameter routing_parameters = 2;</code>
   */
  com.google.api.RoutingParameterOrBuilder getRoutingParametersOrBuilder(int index);
}
