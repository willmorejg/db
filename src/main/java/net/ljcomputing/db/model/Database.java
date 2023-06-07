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

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/** Database Model. */
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Database {
    /** The name of the Database. */
    @NotNull private final String name;

    private List<Table> tables = new ArrayList<>();

    /**
     * Add the given {@link net.ljcomputing.db.model.Table Table} to the {@link java.util.List List}
     * of {@link net.ljcomputing.db.model.Table Tables} associated with the Database.
     *
     * @param table
     */
    public void addTable(final Table table) {
        tables.add(table);
    }
}
