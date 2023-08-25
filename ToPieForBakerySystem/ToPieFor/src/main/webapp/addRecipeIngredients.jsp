<%@page import="za.co.bakery.model.Recipe"%>
<%@page import="za.co.bakery.model.Ingredient"%>
<%@page import="java.util.List"%>
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

            .nav-link {
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                text-decoration: none;
                display: inline-block;
                margin-top: 20px;
            }

            .nav-link:hover {
                background-color: #0056b3;
            }
        </style>

        <script>
            function validation() {
                var recipeName = document.form.recipeName.value;
                var description = document.form.description.value;

                if (recipeName == null || recipeName === "") {
                    alert("Name of Recipe cannot be blank");
                    return false;
                } else if (description == null || description === "") {
                    alert("Description cannot be blank");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div id="container">
            <h1>Recipe Edit Form</h1>
            <form method="POST" action="RecipeController" onsubmit="return validation()">
                <div class="row">
                    <div class="col-12 col-xl-8 mb-4 mb-lg-0">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th scope="col">Recipe Id</th>
                                                <th scope="col">Recipe Name</th>
                                                <th scope="col">Description</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% Recipe recipe = (Recipe) request.getAttribute("recipe");
                                                if (recipe != null) {%>
                                            <tr>
                                                <th scope="row">rec<%= recipe.getRecipeId()%></th>
                                                <td><%= recipe.getRecipeName()%></td>
                                                <td><%= recipe.getDescription()%></td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <table>
                    <tr>
                        <td>Ingredients</td>
                        <td>
                            <select id="unitDropdown" name="ingredientId">
                                <% List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("allIngredients");
                                    if (ingredients != null && !ingredients.isEmpty()) {
                                        for (Ingredient i : ingredients) {%>
                                <option value="<%= i.getIngredientId()%>"><%= i.getName()%></option>
                                <% }
                                    }%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" required/></td>
                    </tr>
                    <tr>
                        <td>Recipe Ingredients</td>
                        <td> <ul>
                                <%
                                    List<String> recipeIngredients = (List<String>) request.getAttribute("allRecipeIngredients");
                                    for (String ingredient : recipeIngredients) {
                                %>
                                <li><%= ingredient%></li>
                                    <%
                                        }
                                    %>
                            </ul></td>
                    </tr>
                </table>
                <br>
                <br>
                <center>
                    <input type="hidden" id="act" name="act" value="addingredients">
                    <input type="hidden" id="recipeId" name="recipeId" value="<%= recipe.getRecipeId()%>">
                    <input type="submit" value="Submit" />
                </center>
                <br>
                <br>
            </form>
            <center>
                <a class="nav-link" href="RecipeController?act=allrecipes">Back</a>
            </center>
            <br>
            <br>
        </div>
    </body>
</html>
