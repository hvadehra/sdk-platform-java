/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.redis.v1beta1.samples;

// [START redis_v1beta1_generated_cloudredisclient_createinstance_callablefuturecallcreateinstancerequest]
import com.google.api.core.ApiFuture;
import com.google.cloud.redis.v1beta1.CloudRedisClient;
import com.google.cloud.redis.v1beta1.CreateInstanceRequest;
import com.google.cloud.redis.v1beta1.Instance;
import com.google.cloud.redis.v1beta1.LocationName;
import com.google.longrunning.Operation;

public class CreateInstanceCallableFutureCallCreateInstanceRequest {

  public static void main(String[] args) throws Exception {
    createInstanceCallableFutureCallCreateInstanceRequest();
  }

  public static void createInstanceCallableFutureCallCreateInstanceRequest() throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (CloudRedisClient cloudRedisClient = CloudRedisClient.create()) {
      CreateInstanceRequest request =
          CreateInstanceRequest.newBuilder()
              .setParent(LocationName.of("[PROJECT]", "[LOCATION]").toString())
              .setInstanceId("instanceId902024336")
              .setInstance(Instance.newBuilder().build())
              .build();
      ApiFuture<Operation> future = cloudRedisClient.createInstanceCallable().futureCall(request);
      // Do something.
      Operation response = future.get();
    }
  }
}
// [END redis_v1beta1_generated_cloudredisclient_createinstance_callablefuturecallcreateinstancerequest]