<%-- 
    Document   : checkout
    Created on : 21 Jun 2023, 9:39:16 AM
    Author     : Train
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Form</title>
        <link rel="stylesheet" href="css/CheckoutPageStyle.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    </head>

    <body>
        <div class="wrapper">
            <h2>Payment Form</h2>
            <form method="POST" action="OrderController" onsubmit="getCart()">
                <!--Account Information Start-->
                <h4>Account</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" placeholder="Full Name" required class="name">
                        <i class="fa fa-user icon"></i>
                    </div>
                    <div class="input_box">
                        <input type="text" placeholder="Name on Card" required class="name">
                        <i class="fa fa-user icon"></i>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <input type="email" placeholder="Email Address" name="username" required class="name">
                        <i class="fa fa-envelope icon"></i>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" placeholder="Address" name="deliveryAddress" required class="name">
                        <i class="fa fa-map-marker icon" aria-hidden="true"></i>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" placeholder="City" required class="name">
                        <i class="fa fa-institution icon"></i>
                    </div>
                </div>
                <!--Account Information End-->
                <br>
                <!--Payment Details Start-->
                <div class="input_group">
                    <div class="input_box">
                        <h4>Payment Details</h4>
                        <input type="radio" name="pay" class="radio" id="bc1" checked>
                        <label for="bc1"><span>
                                <i class="fa fa-cc-visa"></i>Credit Card</span></label>
                        <input type="radio" name="pay" class="radio" id="bc2">
                        <label for="bc2"><span>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <!--                        <input type="tel" name="" class="name" placeholder="Card Number" required>-->
                        <input id="num-input" type="text" required pattern="\d{16}|\d{16}" name="" class="name" placeholder="Card Number" >

                               <i class="fa fa-credit-card icon"></i>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" pattern="\d{3}|\d{4}" name="" class="name" placeholder="Card CVC" required>
                        <i class="fa fa-user icon"></i>
                    </div>
                </div>
                <div class="input_group">
                    <div class="input_box">
                        <div class="input_box">
                            <label class="name"> Exp Date </label>
                            <input type="date" placeholder="Exp Month" required class="name">
                            <i class="fa fa-calendar icon" aria-hidden="false"></i>
                        </div>
                    </div>
<!--                    <div class="input_box">
                        <input type="number" max="2023" min="2099" placeholder="Exp Year" required class="name">
                        <i class="fa fa-calendar-o icon" aria-hidden="true"></i>
                    </div>-->
                </div>

                <br>
                <!--Payment Details End-->
                <div class="input_group">
                    <div class="input_box">
                        <div class="input_box">
                            <input type="hidden" id='cart' name="cartlist" value="" />
                            <input type="hidden" id='total' name="totalval" value="" />
                            <input type="hidden"  name='act' value="add"/>
                            <button type="submit" >Pay Now</button>
                        </div>
                    </div>
                    <div class="input_box">
                        <button type="submit">
                            <a href="ItemController?act=viewhomeitems">Cancel</a>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <script src="HP/js/script.js"></script>
    </body>
</html>