<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </style>

    <script>
        function validation() {
            var recipeName = document.form.recipeName.value;
            var description = document.form.description.value;

            if (recipeName == null || recipeName === "") {
                alert("recipeName cannot be blank");
                return false;
            } else if (description == null || description === "") {
                alert("description name cannot be blank");
                return false;
            }
        }
    </script>
</head>
<body>
    <div id="container">
        <h1>Recipe Form</h1>
        <form method="POST" action="RecipeController" onsubmit="return validation()">
            <table>
                <tr>
                    <td>Recipe Name</td>
                    <td><input type="text" name="recipeName" /></td>
                </tr>
                <tr>
                    <td>Recipe Description</td>
                    <td><input type="text" name="description" /></td>
                </tr>
            </table>
            <br>
            <center>
                <input type="hidden" id="act" name="act" value="addrecipe">
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
