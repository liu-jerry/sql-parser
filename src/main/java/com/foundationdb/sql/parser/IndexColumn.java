/**
 * Copyright 2011-2013 FoundationDB, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foundationdb.sql.parser;

import com.foundationdb.sql.StandardException;

/**
 * An IndexColumn is the element of an index definition.
 */
public class IndexColumn extends QueryTreeNode
{
    private TableName tableName;
    private String columnName;
    private boolean ascending = true;

    /**
     * Initializer.
     *
     * @param columnName Name of the column
     * @param ascending Whether index is ascending
     */
    public void init(Object columnName,
                     Object ascending) {
        this.tableName = null;
        this.columnName = (String)columnName;
        this.ascending = ((Boolean)ascending).booleanValue();
    }

    /**
     * Initializer.
     *
     * @param tableName Table holding indexed column
     * @param columnName Name of the column
     * @param ascending Whether index is ascending
     */
    public void init(Object tableName,
                     Object columnName,
                     Object ascending) {
        this.tableName = (TableName)tableName;
        this.columnName = (String)columnName;
        this.ascending = ((Boolean)ascending).booleanValue();
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        IndexColumn other = (IndexColumn)node;
        this.tableName = (TableName)getNodeFactory().copyNode(other.tableName, 
                                                              getParserContext());
        this.columnName = other.columnName;
        this.ascending = other.ascending;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */
    public String toString() {
        return "columnName: " + columnName + "\n" +
            "tableName: " + ((tableName != null) ? tableName.toString() : "null") + "\n" +
            (ascending ? "ascending" : "descending") + "\n" +
            super.toString();
    }

    public TableName getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public boolean isPartOfGroupIndex() {
        return tableName != null;
    }

    /**
     * @return true if ascending, false if descending
     */
    public boolean isAscending() {
        return ascending;
    }

}
