[
<#list database.tables as table>
    {
        "tableName" : "${table.name}", 
        "columns" : [
            <#list table.columns as column>
            {
                "name" : "${column.name}", 
                "type" : "${(column.typeName)!}",
                "size" : "${(column.size)!}"
            }<#if column?has_next>,</#if>
            </#list>
        ]
    }<#if table?has_next>,</#if>
</#list>
]