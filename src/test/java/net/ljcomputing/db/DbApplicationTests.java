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
package net.ljcomputing.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import freemarker.template.Configuration;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import net.ljcomputing.db.model.Database;
import net.ljcomputing.db.service.DatabaseMetaDataMappingService;
import net.ljcomputing.db.service.FreemarkerProcessingService;
import net.ljcomputing.db.service.TableMetaDataMappingService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DbApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DbApplicationTests.class);

    @Autowired private DataSource dataSource;

    @Autowired private Configuration freeMarker;

    @Autowired private DatabaseMetaDataMappingService databaseMetaDataMappingService;

    @Autowired private TableMetaDataMappingService tableMetaDataMappingService;

    @Autowired private FreemarkerProcessingService freemarkerProcessingService;

    @Test
    @Order(1)
    void contextLoads() throws Exception {
        assertNotNull(dataSource);
        assertNotNull(freeMarker);
        assertNotNull(freeMarker.getTemplate("json.ftl"));
    }

    @Test
    @Order(10)
    void instaniateDatabaseTest() throws Exception {
        Database database = databaseMetaDataMappingService.map(dataSource, "NJ GIS");
        tableMetaDataMappingService.map(dataSource, database);
        // log.debug("database: {}", database);
        assertNotNull(database.getName());
        assertFalse(database.getTables().isEmpty());
    }

    @Test
    @Order(20)
    void writeDatabaseSchemaTest() throws Exception {
        Database database = databaseMetaDataMappingService.map(dataSource, "NJ GIS");
        tableMetaDataMappingService.map(dataSource, database);
        // log.debug("database: {}", database);
        assertNotNull(database.getName());
        assertFalse(database.getTables().isEmpty());
        Map<String, Object> root = new HashMap<>();
        root.put("database", database);
        StringWriter writer = new StringWriter();
        freeMarker.getTemplate("json.ftl").process(root, writer);
        log.debug("writer: {}", writer.toString());
        FileWriter fw = new FileWriter(database.getName() + ".html");
        fw.write(writer.toString());
        fw.close();
    }

    @Test
    @Order(25)
    void freemarkerProcessTest() throws Exception {
        Database database = databaseMetaDataMappingService.map(dataSource, "NJ GIS");
        tableMetaDataMappingService.map(dataSource, database);
        // log.debug("database: {}", database);
        assertNotNull(database.getName());
        assertFalse(database.getTables().isEmpty());

        freemarkerProcessingService.process(database, "html.ftl", database.getName() + ".htm");
    }

    @Test
    @Order(30)
    void cliTest() throws Exception {
        DbApplication.main(
                "-n",
                "Contacts",
                "-o",
                "cli-tst.html",
                "-d",
                "/home/jim/templates/",
                "-t",
                "html.ftl",
                "-u",
                "jdbc:postgresql://localhost/contacts",
                "-r",
                "jim",
                "-p",
                "password",
                "-c",
                "org.postgresql.Driver");
    }
}
