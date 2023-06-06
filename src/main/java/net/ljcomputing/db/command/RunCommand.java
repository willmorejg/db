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
package net.ljcomputing.db.command;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Callable;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import net.ljcomputing.db.model.Database;
import net.ljcomputing.db.service.DatabaseMetaDataMappingService;
import net.ljcomputing.db.service.FreemarkerProcessingService;
import net.ljcomputing.db.service.TableMetaDataMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(
        name = "RunCommand",
        description = "Parse Database Schema",
        mixinStandardHelpOptions = true,
        version = "db 0.0.1")
@Slf4j
public class RunCommand implements Callable<Integer> {
    // @Autowired private DataSource dataSource;
    @Autowired private Configuration freeMarker;
    @Autowired private DatabaseMetaDataMappingService databaseMetaDataMappingService;
    @Autowired private TableMetaDataMappingService tableMetaDataMappingService;
    @Autowired private FreemarkerProcessingService freemarkerProcessingService;

    @Option(
            names = {"-n", "--database-name"},
            paramLabel = "DATABASE_NAME",
            description = "The name to assign to the database",
            required = true)
    private String databaseName;

    @Option(
            names = {"-d", "--template-directory"},
            paramLabel = "TEMPLATE_DIRECTORY",
            description = "The FreeMarker Template directory location")
    private File templateDirectory;

    @Option(
            names = {"-t", "--template-filename"},
            paramLabel = "TEMPLATE_FILE",
            description = "The FreeMarker Template filename to process",
            required = true)
    private String templateFilename;

    @Option(
            names = {"-o", "--output-filename"},
            paramLabel = "OUTPUT_FILE",
            description = "The output filename",
            required = true)
    private String outputFilename;

    @Option(
            names = {"-u", "--db-url"},
            paramLabel = "DATASOURCE_URL",
            description = "The data source URL",
            required = true)
    private String dbUrl;

    @Option(
            names = {"-r", "--db-username"},
            paramLabel = "DATASOURCE_USERNAME",
            description = "The data source username")
    private String dbUsername;

    @Option(
            names = {"-p", "--db-password"},
            paramLabel = "DATASOURCE_PASSWORD",
            description = "The data source password")
    private String dbPassword;

    @Option(
            names = {"-c", "--db-classname"},
            paramLabel = "DATASOURCE_DRIVER_CLASS",
            description = "The data source driver class",
            required = true)
    private String dbDriverClass;

    /** {@inheritDoc} */
    @Override
    public Integer call() throws Exception {
        int result = -1;

        try {
            DataSource ds =
                    DataSourceBuilder.create()
                            .url(dbUrl)
                            .username(dbUsername)
                            .password(dbPassword)
                            .driverClassName(dbDriverClass)
                            .build();

            final Database database = databaseMetaDataMappingService.map(ds, databaseName);
            tableMetaDataMappingService.map(ds, database);

            if (templateDirectory != null && templateDirectory.exists()) {
                freeMarker.setDirectoryForTemplateLoading(templateDirectory);
            }

            final Template tmp = freeMarker.getTemplate(templateFilename);

            try (final FileWriter fw = new FileWriter(outputFilename); ) {
                tmp.process(freemarkerProcessingService.createRoot(database), fw);
            }

            result = 0;
        } catch (Exception e) {
            log.error("Error: ", e);
        }

        return result;
    }
}
