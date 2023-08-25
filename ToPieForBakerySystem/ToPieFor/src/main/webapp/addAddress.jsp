
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title></title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="LoginAndRegistration/fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="LoginAndRegistration/css/style.css">
    </head>

    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Address</h2>

                            <form method="POST" action="AddressController" class="register-form" >
                                <div class="form-group">
                                    <input type="text" name="address1" placeholder="Address 1" />
                                </div>
                                <div class="form-group">
                                    <input type="text" name="address2" placeholder="Address 2" />
                                </div>
                                <div class="form-group">
                                    <input type="text" name="province" placeholder="Province" />
                                </div>
                           
                                <div class="form-group">
                                    <input type="text" name="city" placeholder="City" />
                                </div>
                                <div class="form-group">
                                    <input type="number" name="code" placeholder="Code" />
                                </div>
                                
                                <div class="form-group form-button">
                                    <input type="hidden" id="act" name="act" value="addaddress">
                                    <input type="submit" name="signup" id="signup" class="form-submit" />
                                </div>
                                <br>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure>
                                <img src="LoginAndRegistration/images/signup.jpg" alt="sing up image">
                            </figure>

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