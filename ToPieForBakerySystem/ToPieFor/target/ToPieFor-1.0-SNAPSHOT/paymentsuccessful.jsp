<%-- 
    Document   : paymentsuccessful
    Created on : Jun 22, 2023, 9:27:13 AM
    Author     : STUDIO 18
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Successful Payment</title>
        <!-- Link to css  -->
        <link rel="stylesheet" href="css/stylesSuccessAndError.css">
    </head>

    <body>
        <div class="sc-container">
            <h1>Payment Successful!</h1>
            <p>Your order will arrive in 2 - 3 business days.</p>
            <img src="img/success.png" alt="">
            <form onsubmit="clearCart()">
                <button type="submit">
                    <a href="ItemController?act=viewhomeitems" class="sc-btn">Continue Shopping</a>
                </button>
            </form>
        </div>
        <script src="HP/js/script.js"></script>
    </body>

</html>