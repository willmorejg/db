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
package net.ljcomputing.db.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import net.ljcomputing.db.model.Database;
import net.ljcomputing.db.service.FreemarkerProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Implementation of a FreeMarker processing Service. */
@Service
public class FreemarkerProcessingServiceImpl implements FreemarkerProcessingService {
    @Autowired private Configuration freeMarker;

    /** {@inheritDoc} */
    @Override
    public void process(final Database database, final String template, final String outputFile)
            throws Exception {
        try (FileWriter writer = new FileWriter(outputFile); ) {
            final Map<String, Object> root = createRoot(database);
            final Template tmp = freeMarker.getTemplate(template);
            tmp.process(root, writer);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> createRoot(final Database database) {
        final Map<String, Object> root = new HashMap<>();
        root.put("database", database);
        return root;
    }
}
