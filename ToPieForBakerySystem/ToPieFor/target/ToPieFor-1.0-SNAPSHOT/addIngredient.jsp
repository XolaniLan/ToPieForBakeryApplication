<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="za.co.bakery.model.Unit"%>
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

        input[type="text"], select {
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
    </style>

    <script>
        function validation() {
            var ingredientId = document.form.ingredientId.value;
            var unitId = document.form.unitId.value;
            var quantityinStock = document.form.quantityinStock.value;
            var name = document.form.name.value;
            var minimumstockLevel = document.form.minimumstockLevel.value;
            var isActive = document.form.isActive.value;
            if (ingredientId == null || ingredientId === "") {
                alert("ingredientId cannot be blank");
                return false;
            } else if (unitId == null || unitId === "") {
                alert("unitId cannot be Blank");
                return false;
            } else if (quantityinStock == null || quantityinStock === "") {
                alert("quantityinStock cannot be Blank");
                return false;
            } else if (name == null || name === "") {
                alert("name cannot be Blank");
                return false;
            } else if (minimumstockLevel == null || minimumstockLevel === "") {
                alert("minimumstockLevel cannot be Blank");
                return false;
            } else if (isActive == null || isActive === "") {
                alert("isActive cannot be Blank");
                return false;
            }
        }
    </script>
</head>
<body>
    <div id="container">
        <h1>Ingredient Form</h1>
        <form method="POST" action="IngredientController" onsubmit="return validation()">
            <table>
                <tr>
                    <td>Ingredient Name</td>
                    <td><input type="text" name="name"required /></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantityinStock" required/></td>
                </tr>
                <tr>
                    <td>Minimum Stock Level</td>
                    <td><input type="text" name="minimumstockLevel" required/></td>
                </tr>
                <tr>
                    <td>Units</td>
                    <td>
                        <select id="unitDropdown" name="unitId">
                            <%
                                List<Unit> units = (List<Unit>) request.getAttribute("allUnits");
                                if (units != null && !units.isEmpty()) {
                                    for (Unit u : units) {
                            %>
                            <option value="<%= u.getUnitId() %>"><%= u.getUnitDescription() %></option>
                            <% }
                                } %>
                        </select>
                    </td>
                </tr>
            </table>
            <br>
            <center>
                <input type="hidden" id="act" name="act" value="addingredient">
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
