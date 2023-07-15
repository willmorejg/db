<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Table ${table.name} Definition</title>
</head>

<body>
    <div id="container" class="container">
        <h1>Table ${table.name} Definition</h1>
            <table class="table table-md caption-top table-hover table-striped">
                <caption>
                    <h3>Table: ${table.name}
                        <br />Columns:
                    </h3>
                </caption>
                <thead>
                    <th scope="col">Name</th>
                    <th scope="col">Type Name</th>
                    <th scope="col">Size</th>
                    <th scope="col">Class Name</th>
                    <th scope="col">Nulls allowed</th>
                    <th scope="col">Data Type</th>
                </thead>
                <tbody>
                    <#list table.columns as column>
                        <tr>
                            <td>
                                ${column.name}
                            </td>
                            <td>
                                ${column.typeName}
                            </td>
                            <td>
                                ${column.size}
                            </td>
                            <td>
                                ${column.className}
                            </td>
                            <td>
                                ${column.nullable?string('yes', 'no')}
                            </td>
                            <td>
                                ${column.dataType}
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>

</html>