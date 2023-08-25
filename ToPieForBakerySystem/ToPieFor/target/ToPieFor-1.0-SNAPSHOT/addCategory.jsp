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
        <h1 align="center">Category Form</h1>
        <form method="POST" action="CategoryController" onsubmit="return validation()">
            <table>
                <tr>
                    <td>Category Name</td>
                    <td><input type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>Category Description</td>
                    <td><input type="text" name="description" /></td>
                </tr>
            </table>
            <br>
            <div align="center">
                <input type="hidden" id="act" name="act" value="addcategory">
                <input type="submit">        
            </div>
            <br>
            <div align="center">
                <button type="button" name="back" onclick="history.back()">Back</button>     
            </div>
        </form>
    </div>
</body>
</html>
