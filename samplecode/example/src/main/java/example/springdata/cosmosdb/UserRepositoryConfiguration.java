/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.cosmosdb;

import com.azure.data.cosmos.CosmosKeyCredential;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableDocumentDbRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableDocumentDbRepositories
@EnableConfigurationProperties(DocumentDbProperties.class)
@PropertySource("classpath:application.properties")
public class UserRepositoryConfiguration extends AbstractDocumentDbConfiguration {

    @Autowired
    private DocumentDbProperties properties;

    private CosmosKeyCredential cosmosKeyCredential;

    @Bean
    public DocumentDBConfig documentDBConfig() {
        this.cosmosKeyCredential = new CosmosKeyCredential(properties.getKey());
        return DocumentDBConfig.builder(properties.getUri(), cosmosKeyCredential, properties.getDatabase()).build();
    }

    public void switchToSecondaryKey() {
        this.cosmosKeyCredential.key(properties.getSecondaryKey());
    }

    public void switchToPrimaryKey() {
        this.cosmosKeyCredential.key(properties.getKey());
    }

    public void switchKey(String key) {
        this.cosmosKeyCredential.key(key);
    }
}
