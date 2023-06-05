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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import net.ljcomputing.db.model.Database;
import net.ljcomputing.db.model.DatabaseMetadataValues;
import net.ljcomputing.db.model.Table;
import net.ljcomputing.db.service.DatabaseMetaDataMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatabaseMetaDataMappingServiceImpl implements DatabaseMetaDataMappingService {
    @Autowired DataSourceProperties dataSourceProperties;

    /** {@inheritDoc} */
    @Override
    public Database map(final DataSource dataSource, final String dbName) throws Exception {
        final Database database = new Database(dbName);

        try (Connection conn = dataSource.getConnection(); ) {
            final DatabaseMetaData databaseMetaData = conn.getMetaData();

            try (ResultSet rs =
                    databaseMetaData.getTables(null, "public", "%", new String[] {"TABLE"})) {
                while (rs.next()) {
                    final Table table =
                            new Table(rs.getString(DatabaseMetadataValues.TABLE_NAME.toString()));
                    database.addTable(table);
                }
            }
        } catch (Exception e) {
            log.error("Error: ", e);
        }

        return database;
    }
}
