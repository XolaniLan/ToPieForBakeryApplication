<%@page import="za.co.bakery.model.Category"%>
<!DOCTYPE html>
<html>
    <head>
        <title>To Pie For User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
        <style>
            body {
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-color: #f2f2f2;
            }
            .form-container {
                width: 80%;
                padding: 20px;
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-container h1 {
                margin-top: 0;
            }
            .form-container table {
                width: 100%;
            }
            .form-container input[type="text"] {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .form-container input[type="submit"],
            .form-container button {
                padding: 8px 16px;
                margin-top: 10px;
                background-color: #4caf50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .form-container button {
                background-color: #ccc;
                margin-left: 10px;
            }
            .form-container button:hover,
            .form-container input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1 align="center">Category Edit Form</h1>

            <form method="POST" action="CategoryController" onsubmit="return validation()">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Category Id</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Description</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% Category category = (Category) request.getAttribute("category");
                                        if (category != null) {%>
                                    <tr>
                                        <th scope="row">cat<%= category.getCategoryId()%></th>
                                        <td><%=category.getName()%></td>
                                        <td><%=category.getDescription()%></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <br>
                <table style="width: 100%;">
                    <tr>
                        <td>Category Name</td>
                        <td><input type="text" name="name" value="<%=category.getName()%>"/></td>
                    </tr>
                    <tr>
                        <td>Category Description</td>
                        <td><input type="text" name="description"  value="<%=category.getDescription()%>"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                    <center>
                        <input type="hidden" id="act" name="act" value="edit">
                        <input type="hidden" id="categoryId" name="categoryId" value=<%= category.getCategoryId()%>>
                        <input type ='submit'>   
                        <button type="button" name="back" onclick="history.back()">back</button>  
                    </center> </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
