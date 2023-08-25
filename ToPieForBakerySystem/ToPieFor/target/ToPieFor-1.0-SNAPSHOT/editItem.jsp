<%@page import="za.co.bakery.model.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>To Pie For User</title>
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

        input[type="text"], input[type="file"] {
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
            var itemName = document.forms.itemName.value;
            var price = document.forms.price.value;
            var description = document.forms.description.value;
            var nutritionalValue = document.forms.nutritionalValue.value;
            var warnings = document.forms.warnings.value;
            if (itemName == null || itemName === "") {
                alert("Name of Category cannot be blank");
                return false;
            } else if (price == 0 || price === "") {
                alert("price cannot be Blank");
                return false;
            } else if (description == null || description === "") {
                alert("description cannot be Blank");
                return false;
            } else if (nutritionalValue == null || nutritionalValue === "") {
                alert("nutritionalValue cannot be Blank");
                return false;
            } else if (warnings == 0 || warnings === "") {
                alert("warnings cannot be Blank");
                return false;
            }
        }
    </script>
</head>
<body>
    <div id="container">
        <h1>Item Edit Form</h1>
        <% Item item = (Item) request.getAttribute("item");%>
        <form method="POST" action='ItemController?act=edit&itemId=<%=item.getItemId()%>' onsubmit="return validation()">
            <table>
                <thead>
                    <tr>
                        <th scope="col">Item Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Description</th>
                        <th scope="col">Nutritional Value</th>
                        <th scope="col">Warnings</th>
                        <th scope="col">Image</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <% if (item != null) { %>
                    <tr>
                        <td><%= item.getItemName() %></td>
                        <td><%= item.getPrice() %></td>
                        <td><%= item.getDescription() %></td>
                        <td><%= item.getNutritionalValue() %></td>
                        <td><%= item.getWarnings() %></td>
                        <td><%= item.getImageUrl() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <br>
            <input type="hidden" name="categoryId" value="<%= item.getCategoryId() %>">
            <input type="hidden" name="recipeId" value="<%= item.getRecipeId() %>">
            <br>
            <label for="itemName">Item Name</label>
            <input type="text" id="itemName" name="itemName" value="<%= item.getItemName() %>">

            <label for="price">Item Price</label>
            <input type="text" id="price" name="price" value="<%= item.getPrice() %>">

            <label for="description">Item Description</label>
            <input type="text" id="description" name="description" value="<%= item.getDescription() %>">

            <label for="nutritionalValue">Nutritional Value</label>
            <input type="text" id="nutritionalValue" name="nutritionalValue" value="<%= item.getNutritionalValue() %>">

            <label for="warnings">Warnings</label>
            <input type="text" id="warnings" name="warnings" value="<%= item.getWarnings() %>">

            <label for="imageUrl">Image</label>
            <input type="file" id="imageUrl" name="imageUrl" value="<%= item.getImageUrl() %>">

            <br>
            <center>
                <input type="hidden" id="act" name="act" value="edit">
                <input type="hidden" id="itemId" name="itemId" value="<%= item.getItemId() %>">
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
