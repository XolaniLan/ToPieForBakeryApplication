<%@page import="za.co.bakery.model.Item"%>
<%@page import="za.co.bakery.model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shopping Cart</title>
        <!-- Link to css  -->
        <link rel="stylesheet" href="HP/css/style.css">
        <!-- Link to Box icons -->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>

    </head>

    <body>
        <!-- Nav bar ********************************************************************************************-->
        <header>
            <!-- Nav******************************************************************************************** -->
            <div class="nav container">
                <!-- Logo*************************************************************************************** -->
                <a href="ItemController?act=viewhomeitems" class="logo">To Pie For</a>
                <!-- UserProfle Icons*************************************************************************** -->
                <a href="AccountSettings.jsp">
                    <i class='bx bx-user' id="user-icon"></i>
                </a>

                <form method="POST" action="OrderController" onsubmit="getCart()">


                    <!-- Cart Icons********************************************************************************* -->
                    <i class='bx bx-cart-alt' id="cart-icon" data-quantity="0"></i>
                    <!-- Cart ********************************************************************************* -->
                    <div class="cart">
                        <h2 class="cart-title">Your Cart</h2>
                        <!-- Content -->
                        <div class="cart-content">
                            <!-- <div class="cart-box">
                                   
                            </div> -->
                        </div>
                        <!-- Total -->
                        <div class="total">
                            <div class="total-title">Total</div>
                            <div class="total-price">R0.00</div>
                        </div>
                        <!-- Buy Button  -->
                    
                        <input type="hidden" id='cart' name="cartlist" value="" />
                        <input type="hidden" id='total' name="totalval" value="" />
                        <input type="hidden"  name='act' value="gocheckout"/>
<!--                      <a href="checkout.jsp"><button type="submit" class="btn-buy">Buy</button></a>-->
                        <button type="submit" class="btn-buy">Buy</button>
                        
                   
                        <!-- Close Cart -->
                        <i class='bx bx-x' id="close-cart"></i>
                    </div>
                </form>
            </div>
        </header>

        <!-- Products -->
        <section class="shop container">


            <h2 class="section-title">Shop Products</h2>
            <!-- ------------------------------------------------------------------ -->
            <div class="control">

                <div class="btn-group">
                    <button >
                        <a  href="ItemController?act=viewhomeitems" onclick="submitForm()" >
                            <span class="ml-2"  >All Items</span>
                        </a>
                    </button>
                    <%

                        List<Category> categoryList = (List<Category>) request.getAttribute("allcategories");
                        if (categoryList != null && !categoryList.isEmpty()) {

                            for (Category c : categoryList) {

                    %>
                    <button >
                        <a  href="ItemController?act=itembycategoryid&categoryId=<%= c.getCategoryId()%>" onclick="submitForm()" >
                            <span class="ml-2"  > <%= c.getName()%></span>
                        </a>
                    </button>


                    <%
                            }
                        }
                    %>
                </div>
                <input type="text" placeholder="Search Products" id="search">

            </div>


            <div class="shop-content">

                <%
                    List<Item> itemList = (List<Item>) request.getAttribute("allItems");
                    if (itemList != null && !itemList.isEmpty()) {

                        for (Item i : itemList) {
                %>

                <!-- Box 1  -->
                <div class="product-box" data-item="<%=i.getDescription()%>">
                    <img src="images/<%=i.getImageUrl()%>"
                         alt="" class="product-img" />
                    <input type="hidden" class="product-id" value="<%=i.getItemId()%>" />
                    <h2 class="product-title"><%=i.getItemName()%></h2>
                    <span class="price"><%=i.getPrice()%>0</span>
                    <i class='bx bx-shopping-bag add-cart'></i>
                    <ion-icon name="eye-outline" class="view-info" id="myBtn"></ion-icon>
                </div>

                <div id="myModal" class="modal">

                    <!-- Modal content -->
                    <div class="modal-content">
                        <div class="modal-header">
                            <span class="close" >x</span>
                            <h2>Nutritional Value</h2>
                        </div>
                        <div class="modal-body">
                            <p><%= i.getNutritionalValue()%></p>

                        </div>
                        <div class="modal-footer">
                            <h2>Warnings</h2>
                        </div>
                        <div class="modal-body">
                            <p><%= i.getWarnings()%></p>

                        </div>

                    </div>

                </div>

                <%
                        }
                    }
                %>

            </div>

        </section>

        <script src="HP/js/script.js"></script>
    </body>

</html>
