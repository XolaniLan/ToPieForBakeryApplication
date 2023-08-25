<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="za.co.bakery.model.Recipe"%>
<%@page import="za.co.bakery.model.Category"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>To Pie For - Item Form</title>
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

        label {
            font-weight: bold;
        }

        select, input[type="text"], input[type="number"], input[type="file"] {
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
</head>
<body>
    <div id="container">
        <h1>Item Form</h1>
        <form method="POST" action="ItemController" onsubmit="return validation()">
            <label for="categoryDropdown">Category</label><br>
            <select id="categoryDropdown" name="categoryId">
                <%
                List<Category> categories = (List<Category>) request.getAttribute("allCategories");
                if (categories != null && !categories.isEmpty()) {
                    for (Category c : categories) {
                %>
                <option value='<%= c.getCategoryId()%>'><%= c.getName()%></option>
                <%}
                }%>
            </select>
            <br>

            <label for="recipeDropdown">Recipe</label><br>
            <select id="recipeDropdown" name="recipeId">
                <%
                List<Recipe> recipes = (List<Recipe>) request.getAttribute("allRecipes");
                if (recipes != null && !recipes.isEmpty()) {
                    for (Recipe r : recipes) {
                %>
                <option value='<%= r.getRecipeId()%>'><%=r.getRecipeName()%></option>
                <%}
                }%>
            </select>
            <br>
            <br>

            <label for="itemName">Item Name</label>
            <input type="text" id="itemName" name="itemName" required><br>

            <label for="description">Item Description</label>
            <input type="text" id="description" name="description" required><br>

            <label for="price">Item Price</label>
            <input type="number" id="price" name="price" required><br>

            <label for="nutritionalValue">Item Nutritional Value</label>
            <input type="text" id="nutritionalValue" name="nutritionalValue"><br>

            <label for="warnings">Item Warnings</label>
            <input type="text" id="warnings" name="warnings"><br>

            <label for="imageUrl">Item Image</label>
            <input type="file" id="imageUrl" name="imageUrl"><br>

            <br>
            <center>
                <input type="hidden" id="act" name="act" value="additem">
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
