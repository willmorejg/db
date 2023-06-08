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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.sql.DataSource;
import net.ljcomputing.db.model.Column;
import net.ljcomputing.db.model.Database;
import net.ljcomputing.db.model.Table;
import net.ljcomputing.db.service.TableMetaDataMappingService;
import org.springframework.stereotype.Service;

/** Implementation of a Table MetaData Mapping Service. */
@Service
public class TableMetaDataMappingServiceImpl implements TableMetaDataMappingService {

    /** {@inheritDoc} */
    @Override
    public void map(final DataSource dataSource, final Database database) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            for (final Table table : database.getTables()) {
                mapTableColumns(conn, table);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void mapTableColumns(final Connection conn, final Table table) throws Exception {
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(table.noRecordsSql()); ) {
            final ResultSetMetaData rsmd = rs.getMetaData();
            final int columnCount = rsmd.getColumnCount();

            for (int c = 1; c <= columnCount; c++) {
                table.addColumn(new Column(rsmd, c));
            }
        }
    }
}
