/*
 * Copyright 2012 Nodeable Inc
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.streamreduce.core.service;

import com.google.common.collect.Sets;
import com.streamreduce.AbstractServiceTestCase;
import com.streamreduce.ProviderIdConstants;
import com.streamreduce.connections.AuthType;
import com.streamreduce.connections.ConnectionProviderFactory;
import com.streamreduce.core.model.Connection;
import com.streamreduce.core.model.ConnectionCredentials;
import com.streamreduce.core.model.OutboundConfiguration;
import com.streamreduce.core.model.OutboundDataType;
import com.streamreduce.core.model.SobaObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class ConnectionServiceUpdateConnectionIT extends AbstractServiceTestCase {

    @Autowired
    ConnectionService connectionService;
    @Autowired
    ConnectionProviderFactory connectionProviderFactory;

    private ResourceBundle cloudBundle = ResourceBundle.getBundle("cloud");


    @Test
    public void testUpdateConnectionPersistsOutboundConfigurations() throws Exception {

        OutboundConfiguration outboundConfiguration = new OutboundConfiguration.Builder()
                .credentials(new ConnectionCredentials(cloudBundle.getString("nodeable.aws.accessKeyId"), cloudBundle.getString("nodeable.aws.secretKey")))
                .destination("eu-west-1")
                .protocol("s3")
                .namespace("com.streamreduce.bucket")
                .dataTypes(OutboundDataType.PROCESSED)
                .build();

        Connection feedConnection = new Connection.Builder()
                .alias("EC2 Test Connection Test")
                .description("Reports the status of Amazon Elastic Compute Cloud (N. California).")
                .visibility(SobaObject.Visibility.ACCOUNT)
                .provider(connectionProviderFactory.connectionProviderFromId(ProviderIdConstants.FEED_PROVIDER_ID))
                .user(testUser)
                .authType(AuthType.NONE)
                .url("status.aws.amazon.com/rss/ec2-us-west-1.rss?junkParam")
                .build();

        Connection createdFeedConnection = connectionService.createConnection(feedConnection);

        createdFeedConnection.setOutboundConfigurations(Sets.newHashSet(outboundConfiguration));
        connectionService.updateConnection(createdFeedConnection);

        Connection retrievedConnection = connectionService.getConnection(createdFeedConnection.getId());

        Assert.assertEquals(createdFeedConnection.getId(), retrievedConnection.getId());
        Assert.assertEquals(createdFeedConnection.getOutboundConfigurations(),
                retrievedConnection.getOutboundConfigurations());
    }

}
