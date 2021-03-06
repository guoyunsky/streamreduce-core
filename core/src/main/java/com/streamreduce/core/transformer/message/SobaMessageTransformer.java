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

package com.streamreduce.core.transformer.message;

import com.streamreduce.core.event.EventId;
import com.streamreduce.core.model.Event;
import com.streamreduce.core.model.messages.details.SobaMessageDetails;
import com.streamreduce.util.HashtagUtil;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

public class SobaMessageTransformer implements MessageTransformer {

    protected Properties messageProperties;
    protected SobaMessageDetails messageDetails;


    public SobaMessageTransformer(Properties messageProperties, SobaMessageDetails messageDetails) {
        this.messageProperties = messageProperties;
        this.messageDetails = messageDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String doTransform(Event event) {
        EventId eventId = event.getEventId();
        Map<String, Object> eventMetadata = event.getMetadata();
        String msg;

        switch (eventId) {
            case HASHTAG_ADD:
                msg = MessageFormat.format((String) messageProperties.get("message.hashtag.added"),
                        eventMetadata.get("sourceAlias"),
                        HashtagUtil.normalizeTag((String)eventMetadata.get("addedHashtag"))
                );
                break;

            case HASHTAG_DELETE:
                msg = MessageFormat.format((String) messageProperties.get("message.hashtag.deleted"),
                                           eventMetadata.get("sourceAlias"),
                                           HashtagUtil.normalizeTag((String)eventMetadata.get("addedHashtag"))
                );
                break;

            default:
                msg = (String)eventMetadata.get("message");
                break;
        }

        return msg;
    }

    @Override
    public SobaMessageDetails getDetails() {
        return messageDetails;
    }

}
