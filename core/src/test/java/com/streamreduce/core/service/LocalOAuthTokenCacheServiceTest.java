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

import org.junit.Before;
import org.junit.Test;
import org.scribe.model.Token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LocalOAuthTokenCacheServiceTest {

    LocalOAuthTokenCacheService oAuthTokenCacheService;

    @Before
    public void setUp() throws Exception {
        oAuthTokenCacheService = new LocalOAuthTokenCacheService();
        Token testToken = new Token("access","secret");
        oAuthTokenCacheService.cacheToken(testToken);
    }

    @Test
    public void testCacheToken() throws Exception {
        Token testToken = new Token("another","token");
        oAuthTokenCacheService.cacheToken(testToken);
        assertEquals(2,oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testCacheNullToken() throws Exception {
        oAuthTokenCacheService.cacheToken(null);
        assertEquals(1, oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testCacheTokenWithSameKey() throws Exception {
        Token newToken = new Token("access","someOtherSecret");
        oAuthTokenCacheService.cacheToken(newToken);
        assertEquals(newToken, oAuthTokenCacheService.retrieveToken("access"));
    }

    @Test
    public void testCacheTokenWithNullTokenField() throws Exception {
        Token testToken = new Token(null,"secret");
        oAuthTokenCacheService.cacheToken(testToken);
        assertEquals(1,oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testRetrieveToken() throws Exception {
        Token testToken = new Token("access","secret");
        oAuthTokenCacheService.cacheToken(testToken);
        Token retrievedToken = oAuthTokenCacheService.retrieveToken("access");
        assertEquals(testToken,retrievedToken);
    }

    @Test
    public void testRetrieveTokenNullKey() throws Exception {
        Token testToken = new Token("access","secret");
        oAuthTokenCacheService.cacheToken(testToken);
        Token retrievedToken = oAuthTokenCacheService.retrieveToken(null);
        assertEquals(null,retrievedToken);
    }

    @Test
    public void testRemoveToken() throws Exception {
        oAuthTokenCacheService.removeToken("access");
        assertEquals(0,oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testRemoveTokenNullKey() throws Exception {
        oAuthTokenCacheService.removeToken(null);
        assertEquals(1,oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testRemoveTokenNonExistentKey() throws Exception {
        oAuthTokenCacheService.removeToken("hotsauce");
        assertEquals(1, oAuthTokenCacheService.tokenCache.size());
    }

    @Test
    public void testRetrieveAndRemoveToken() throws Exception {
        Token retrievedToken = oAuthTokenCacheService.retrieveAndRemoveToken("access");
        assertEquals(0,oAuthTokenCacheService.tokenCache.size());
        assertNotNull(retrievedToken);
    }

    @Test
    public void testRetrieveAndRemoveToken_NonExistentKey() throws Exception {
        Token retrievedToken = oAuthTokenCacheService.retrieveAndRemoveToken("not here");
        assertEquals(1,oAuthTokenCacheService.tokenCache.size());
        assertNull(retrievedToken);
    }

    @Test
    public void testRetrieveAndRemoveToken_NullKey() throws Exception {
        Token retrievedToken = oAuthTokenCacheService.retrieveAndRemoveToken(null);
        assertEquals(1,oAuthTokenCacheService.tokenCache.size());
        assertNull(retrievedToken);
    }
}
