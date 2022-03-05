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

package com.google.cloud.asset.v1.samples;

// [START asset_v1_generated_assetserviceclient_searchallresources_pagedcallablefuturecallsearchallresourcesrequest]
import com.google.api.core.ApiFuture;
import com.google.cloud.asset.v1.AssetServiceClient;
import com.google.cloud.asset.v1.ResourceSearchResult;
import com.google.cloud.asset.v1.SearchAllResourcesRequest;
import com.google.protobuf.FieldMask;
import java.util.ArrayList;

public class SearchAllResourcesPagedCallableFutureCallSearchAllResourcesRequest {

  public static void main(String[] args) throws Exception {
    searchAllResourcesPagedCallableFutureCallSearchAllResourcesRequest();
  }

  public static void searchAllResourcesPagedCallableFutureCallSearchAllResourcesRequest()
      throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (AssetServiceClient assetServiceClient = AssetServiceClient.create()) {
      SearchAllResourcesRequest request =
          SearchAllResourcesRequest.newBuilder()
              .setScope("scope109264468")
              .setQuery("query107944136")
              .addAllAssetTypes(new ArrayList<String>())
              .setPageSize(883849137)
              .setPageToken("pageToken873572522")
              .setOrderBy("orderBy-1207110587")
              .setReadMask(FieldMask.newBuilder().build())
              .build();
      ApiFuture<ResourceSearchResult> future =
          assetServiceClient.searchAllResourcesPagedCallable().futureCall(request);
      // Do something.
      for (ResourceSearchResult element : future.get().iterateAll()) {
        // doThingsWith(element);
      }
    }
  }
}
// [END asset_v1_generated_assetserviceclient_searchallresources_pagedcallablefuturecallsearchallresourcesrequest]
