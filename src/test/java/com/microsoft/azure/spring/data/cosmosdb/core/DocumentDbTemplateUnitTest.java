/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.data.cosmosdb.core;

import com.microsoft.azure.spring.data.cosmosdb.CosmosDbFactory;
import com.microsoft.azure.spring.data.cosmosdb.common.TestConstants;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentDbTemplateUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void rejectNullDbFactory() {
        final DocumentDBConfig dbConfig = DocumentDBConfig.builder("", "", TestConstants.DB_NAME).build();
        final CosmosDbFactory cosmosDbFactory = new CosmosDbFactory(dbConfig);

        new DocumentDbTemplate(cosmosDbFactory, null, TestConstants.DB_NAME);
    }
}
