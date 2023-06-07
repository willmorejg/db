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
package net.ljcomputing.db.model;

import java.sql.ResultSetMetaData;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/** Database Column Model. */
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Column {
    /** The name. */
    @NotNull private final String name;

    /** The type name. */
    private String typeName;

    /** The size. */
    private Integer size;

    /** The class name. */
    private String className;

    /** indicates if the column is nullable. */
    private Boolean nullable;

    /** The data type. */
    private Integer dataType;

    /** The foreign key. */
    private boolean foreignKey;

    /**
     * Constructor using {@link java.sql.ResultSetMetaData} and column index.
     *
     * @param rsmd
     * @param columnIdx
     * @throws Exception
     */
    public Column(final ResultSetMetaData rsmd, final int columnIdx) throws Exception {
        final String name = rsmd.getColumnName(columnIdx);
        final String typeName = rsmd.getColumnTypeName(columnIdx);
        final int size = rsmd.getColumnDisplaySize(columnIdx);
        final Boolean nullable = rsmd.isNullable(columnIdx) == ResultSetMetaData.columnNullable;
        final int dataType = rsmd.getColumnType(columnIdx);
        final String className = SqlTypeMapping.toSimpleClassName(dataType);

        this.name = name;
        this.typeName = typeName;
        this.size = size;
        this.nullable = nullable;
        this.dataType = dataType;
        this.className = className;
    }
}
