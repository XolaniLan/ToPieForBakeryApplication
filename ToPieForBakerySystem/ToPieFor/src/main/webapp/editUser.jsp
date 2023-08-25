<%--
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.co.bakery.model.User"%>
--%>
<%@page import="za.co.bakery.model.User"%>
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
</head>
<body>
    <div id="container">
        <h1>User Edit Form</h1>
        <form method="POST" action="UserController">
            <div class="row">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Username</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Surname</th>
                                        <th scope="col">Email</th>             
                                        <th scope="col">Contact Number</th>
                                        <th scope="col">Role</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% User user = (User) request.getAttribute("user");
                                    if (user != null) { 
                                    
                                    String role = "";
                                                        if (user.getRoleId() == 1) {
                                                            role = "Admin";
                                                        } else {
                                                            role = "User";
                                                        }
                                    %>
                                    <tr>
                                        <th scope='row'><%= user.getUsername()%></th>
                                        <td><%=user.getName()%></td>
                                        <td><%=user.getSurname()%></td>
                                        <td><%=user.getEmail()%></td>
                                        <td><%=user.getContactNumber()%></td>
                                        <td><%=role%></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="<%=user.getName()%>"/></td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td><input type="text" name="surname" value="<%=user.getSurname()%>" /></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" value="<%=user.getEmail()%>" /></td>
                </tr>
                <tr>
                    <td>Contact Number</td>
                    <td><input type="text" name="contactNumber" value="<%=user.getContactNumber()%>" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="<%=user.getPassword()%>" /></td>
                </tr>
            </table>
            <br>
            <center>
                <input type="hidden" id="act" name="act" value="edit">
                <input type="hidden" id="username" name="username" value="<%= user.getUsername() %>">
                <input type="submit" value="Submit" />
            </center>
        </form>
        <center>
            <button type="button" name="back" onclick="history.back()">Back</button>
        </center>
    </div>
</body>
</html>
