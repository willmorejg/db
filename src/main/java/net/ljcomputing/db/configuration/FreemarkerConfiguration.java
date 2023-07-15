/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.

James G Willmore - LJ Computing - (C) 2023
*/
package net.ljcomputing.db.configuration;

import freemarker.template.TemplateException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

/** FreeMarker Configuration. */
@Configuration
@Slf4j
public class FreemarkerConfiguration {
    /**
     * The FreeMarker Configuration bean.
     *
     * @param freeMarkerConfigurationFactory
     * @return
     */
    @Bean
    public freemarker.template.Configuration freeMarker(
            @Autowired FreeMarkerConfigurationFactory freeMarkerConfigurationFactory) {
        try {
            freemarker.template.Configuration cfg =
                    freeMarkerConfigurationFactory.createConfiguration();
            return cfg;
        } catch (IOException | TemplateException e) {
            log.error("ERROR: ", e);
        }

        return null;
    }
}
