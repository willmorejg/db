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

public enum DatabaseMetadataValues {
    TABLE_CATALOG,
    TABLE_SCHEMA,
    TABLE_NAME;

    // *may* need in future - right now, not
    //  static final String COLUMN_NAME = "COLUMN_NAME";
    //  static final String TYPE_NAME = "TYPE_NAME";
    //  static final String COLUMN_SIZE = "COLUMN_SIZE";
    //  static final String DATA_TYPE = "DATA_TYPE";
    //  static final String NULLABLE = "NULLABLE";
    //  static final String ORDINAL_POSITION = "ORDINAL_POSITION";
    //// --
    //  static final String BUFFER_LENGTH = "BUFFER_LENGTH";
    //  static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    //  static final String NUM_PREC_RADIX = "NUM_PREC_RADIX";
    //  static final String REMARKS = "REMARKS";
    //  static final String COLUMN_DEF = "COLUMN_DEF";
    //  static final String SQL_DATA_TYPE = "SQL_DATA_TYPE";
    //  static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
    //  static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
    //  static final String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";
    //  static final String IS_GENERATEDCOLUMN = "IS_GENERATEDCOLUMN";
    //// ----
    //  static final String SCOPE_CATALOG = "SCOPE_CATALOG";
    //  static final String SCOPE_SCHEMA = "SCOPE_SCHEMA";
    //  static final String SCOPE_TABLE = "SCOPE_TABLE";
    //  static final String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE";
}
