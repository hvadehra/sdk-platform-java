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

package com.google.cloud.pubsub.v1.samples;

// [START pubsub_v1_generated_topicadminclient_listtopicsubscriptions_callablecalllisttopicsubscriptionsrequest]
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.common.base.Strings;
import com.google.pubsub.v1.ListTopicSubscriptionsRequest;
import com.google.pubsub.v1.ListTopicSubscriptionsResponse;
import com.google.pubsub.v1.TopicName;

public class ListTopicSubscriptionsCallableCallListTopicSubscriptionsRequest {

  public static void main(String[] args) throws Exception {
    listTopicSubscriptionsCallableCallListTopicSubscriptionsRequest();
  }

  public static void listTopicSubscriptionsCallableCallListTopicSubscriptionsRequest()
      throws Exception {
    // This snippet has been automatically generated for illustrative purposes only.
    // It may require modifications to work in your environment.
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      ListTopicSubscriptionsRequest request =
          ListTopicSubscriptionsRequest.newBuilder()
              .setTopic(TopicName.ofProjectTopicName("[PROJECT]", "[TOPIC]").toString())
              .setPageSize(883849137)
              .setPageToken("pageToken873572522")
              .build();
      while (true) {
        ListTopicSubscriptionsResponse response =
            topicAdminClient.listTopicSubscriptionsCallable().call(request);
        for (String element : response.getResponsesList()) {
          // doThingsWith(element);
        }
        String nextPageToken = response.getNextPageToken();
        if (!Strings.isNullOrEmpty(nextPageToken)) {
          request = request.toBuilder().setPageToken(nextPageToken).build();
        } else {
          break;
        }
      }
    }
  }
}
// [END pubsub_v1_generated_topicadminclient_listtopicsubscriptions_callablecalllisttopicsubscriptionsrequest]
