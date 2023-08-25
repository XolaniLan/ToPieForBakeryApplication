<%-- 
    Document   : login
    Created on : May 30, 2023, 8:41:42 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="LoginAndRegistration/fonts/material-icon/css/material-design-iconic-font.min.css">
        <!-- Main css -->
        <link rel="stylesheet" href="LoginAndRegistration/css/style.css">
    </head>

    <body>
        <div class="main">
            <!-- Sign in  Form -->
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure>
                                <img src="LoginAndRegistration/images/signin.jpg" alt="sing up image">
                            </figure>
                            <a href="register.jsp" class="signup-image-link">Create an account</a>
                        </div>
                        <div class="signin-form">
                            <h2 class="form-title">Sign In</h2>
                            <form method="" action="UserController" class="register-form" id="login-form">
                                
                                 <p>Role:
                                    <select name ="role" size='1'>
                                        <option value='2'>user</option>
                                        <option value='1'>admin<option>                            
                                    </select>
                                 </p><!-- comment -->
                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Userame"/>
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-lock"></i></label> 
                                    <input type="password" name="password" id="password" placeholder="Password"/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" /> 
                                    <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                                </div>
                                <div class="form-group form-button">
                                    
                                    <input type="hidden" id="act" name="act" value="login">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>