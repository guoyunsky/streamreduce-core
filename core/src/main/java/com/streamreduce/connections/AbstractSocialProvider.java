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

package com.streamreduce.connections;

/**
 * Abstract class to be extended by all {@link com.streamreduce.connections.SocialProvider} objects of type
 * {@link com.streamreduce.ConnectionTypeConstants.SOCIAL_TYPE}.
 */
public abstract class AbstractSocialProvider extends AbstractConnectionProvider implements SocialProvider{

    AbstractSocialProvider() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return SocialProvider.TYPE;
    }

}
