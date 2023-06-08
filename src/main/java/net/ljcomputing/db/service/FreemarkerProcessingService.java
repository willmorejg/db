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
package net.ljcomputing.db.service;

import java.util.Map;
import net.ljcomputing.db.model.Database;

/** The Interface defining a FreeMarker processing Service. */
public interface FreemarkerProcessingService {
    /**
     * Process the given FreeMarker Template using the given Database as input and write the result
     * to the given output file.
     *
     * @param database
     * @param template
     * @param outputFile
     * @throws Exception
     */
    void process(Database database, String template, String outputFile) throws Exception;

    /**
     * Create a Map of key / value pairs used by the FreeMarker Template.
     *
     * @param database
     * @return
     */
    Map<String, Object> createRoot(Database database);
}
