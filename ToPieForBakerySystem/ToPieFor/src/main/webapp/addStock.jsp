<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.bakery.model.Ingredient"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>To Pie For</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        #container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th, table td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        input[type="submit"], button {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover, button:hover {
            background-color: #45a049;
        }

        button[name="back"] {
            background-color: #808080;
        }

        button[name="back"]:hover {
            background-color: #5a5a5a;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 20px;
        }

        .table {
            width: 100%;
            margin-bottom: 0;
        }

        .table th, .table td {
            border: none;
        }
    </style>

    <script>
        function validation() {
            var quantityinStock = document.form.quantityinStock.value;

            if (quantityinStock == null || quantityinStock === "") {
                alert("quantityinStock cannot be blank");
                return false;
            }
        }
    </script>
</head>
<body>
    <div id="container">
        <h1>Ingredient Form</h1>
        <form method="POST" action="IngredientController" onsubmit="return validation()">
            <div class="row">
                <div class="col-12 col-xl-8 mb-4 mb-lg-0">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Ingredient</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Min Quantity</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            Ingredient ingredient = (Ingredient) request.getAttribute("ingredient");
                                            if (ingredient != null) {
                                        %>
                                        <tr>
                                            <th scope='row'><%=ingredient.getName()%></th>
                                            <td><%=ingredient.getQuantityInStock()%></td>
                                            <td><%= ingredient.getMinimumStockLevel()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <table>
                <tr>
                    <td>Quantity to add to Stock</td>
                    <td><input type="text" name="newStock" /></td>
                </tr>
            </table>

            <br>
            <center>
                <input type="hidden" id="act" name="act" value="addstock">
                <input type="hidden" id="ingredientId" name="ingredientId" value="<%= ingredient.getIngredientId() %>">
                <input type="hidden" id="quantityinStock" name="quantityinStock" value="<%= ingredient.getQuantityInStock() %>">
                <input type="submit" value="Submit">
            </center>
            <br>
            <center>
                <button type="button" name="back" onclick="history.back()">Back</button>
            </center>
        </form>
    </div>
</body>
</html>
