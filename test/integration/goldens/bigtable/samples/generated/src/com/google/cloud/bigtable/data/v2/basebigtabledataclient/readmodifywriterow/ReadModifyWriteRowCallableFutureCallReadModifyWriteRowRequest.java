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

package com.google.cloud.bigtable.data.v2.samples;

// [START bigtable_v2_generated_basebigtabledataclient_readmodifywriterow_callablefuturecallreadmodifywriterowrequest]
import com.google.api.core.ApiFuture;
import com.google.bigtable.v2.ReadModifyWriteRowRequest;
import com.google.bigtable.v2.ReadModifyWriteRowResponse;
import com.google.bigtable.v2.ReadModifyWriteRule;
import com.google.bigtable.v2.TableName;
import com.google.cloud.bigtable.data.v2.BaseBigtableDataClient;
import com.google.protobuf.ByteString;
import java.util.ArrayList;

public class ReadModifyWriteRowCallableFutureCallReadModifyWriteRowRequest {

  public static void main(String[] args) throws Exception {
    readModifyWriteRowCallableFutureCallReadModifyWriteRowRequest();
  }

  public static void readModifyWriteRowCallableFutureCallReadModifyWriteRowRequest()
      throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (BaseBigtableDataClient baseBigtableDataClient = BaseBigtableDataClient.create()) {
      ReadModifyWriteRowRequest request =
          ReadModifyWriteRowRequest.newBuilder()
              .setTableName(TableName.of("[PROJECT]", "[INSTANCE]", "[TABLE]").toString())
              .setAppProfileId("appProfileId704923523")
              .setRowKey(ByteString.EMPTY)
              .addAllRules(new ArrayList<ReadModifyWriteRule>())
              .build();
      ApiFuture<ReadModifyWriteRowResponse> future =
          baseBigtableDataClient.readModifyWriteRowCallable().futureCall(request);
      // Do something.
      ReadModifyWriteRowResponse response = future.get();
    }
  }
}
// [END bigtable_v2_generated_basebigtabledataclient_readmodifywriterow_callablefuturecallreadmodifywriterowrequest]
