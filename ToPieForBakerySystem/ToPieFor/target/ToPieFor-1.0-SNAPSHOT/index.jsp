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
        <title>Document</title>
        <!-- CSS -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/HomePageStyle.css">
        <!-- Google fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@400;600;700&family=Poppins:wght@400;500;600;700&display=swap"
            rel="stylesheet">
    </head>

    <body>
        <!--******************************************************* Header Section Start***************************************************************** --><!-- #Header -->
        <header class="header" data-header>
            <div class="overlay" data-overlay></div>
            <div class="header-top">
                <div class="container">
                    <ul class="header-top-list">

                        <li>
                            <a href="mailto:info@homeverse.com" class="header-top-link">
                                <ion-icon name="mail-outline"></ion-icon>

                                <span>info@cleanbuild.com</span>
                            </a>
                        </li>

                        <li>
                            <a href="#" class="header-top-link">
                                <ion-icon name="location-outline"></ion-icon>

                                <address>Midrand</address>
                            </a>
                        </li>

                    </ul>

                    <div class="wrapper">
                        <ul class="header-top-social-list">

                            <li>
                                <a href="#" class="header-top-social-link">
                                    <ion-icon name="logo-facebook"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="header-top-social-link">
                                    <ion-icon name="logo-twitter"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="header-top-social-link">
                                    <ion-icon name="logo-instagram"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="header-top-social-link">
                                    <ion-icon name="logo-pinterest"></ion-icon>
                                </a>
                            </li>

                        </ul>

                        <a href="login.jsp" class="btn btn-sm btn-primary">Sign In</a>
                        <a href="register.jsp" class="btn btn-sm btn-primary">Sign Up</a>
                    </div>

                </div>
            </div>

            <div class="header-bottom">
                <div class="container">

                    <a href="ItemController?act=index" class="logo">
                        <img src="images/logo-no-background.png" alt="topiefor">
                    </a>
                    <nav class="navbar" data-navbar>

                        <div class="navbar-top">

                            <!--                            <a href="#" ">
                                                            <img src="images/logo-no-background.png" alt="ToPieFor logo">
                                                        </a>-->

                            <button class="nav-close-btn" data-nav-close-btn aria-label="Close Menu">
                                <ion-icon name="close-outline"></ion-icon>
                            </button>

                        </div>

                        <div class="navbar-bottom">
                            <ul class="navbar-list">

                                <li>
                                    <a href="#home" class="navbar-link" data-nav-link>Home</a>
                                </li>
                                <li>
                                    <a href="#item" class="navbar-link" data-nav-link>Items</a>
                                </li>

                                <li>
                                    <a href="#about" class="navbar-link" data-nav-link>About</a>
                                </li>
                                <li>
                                    <a href="#contact" class="navbar-link" data-nav-link>Contact</a>
                                </li>



                            </ul>
                        </div>

                    </nav>

                    <div class="header-bottom-actions">



                        <button class="header-bottom-actions-btn" data-nav-open-btn aria-label="Open Menu">
                            <ion-icon name="menu-outline"></ion-icon>

                            <span>Menu</span>
                        </button>

                    </div>

                </div>
            </div>
            <!--******************************************************* Header Section End***************************************************************** -->
        </header>

        <main>
            <article>

                <!--******************************************************* Hero Section Start***************************************************************** -->
                <section class="hero" id="home">
                    <div class="container">

                        <div class="hero-content">

                            <p class="hero-subtitle">
                            <ion-icon name="home-outline"></ion-icon>
                            </p>
                            <h2 class="h1 hero-title">Freshly baked all Day. Every day!</h2>
                            <p>Sweet as mother’s tenderness.</p><br>

                            <a href="login.jsp" class="btn btn-sm btn-primary">Shop Now</a>
                            <!--<button class="btn" >Shop Now</button>-->
                        </div>
                        <figure class="hero-banner">
                            <img src="images/slide3.jpg" alt="Modern house model" class="w-100">
                        </figure>
                    </div>
                </section>
                <!--******************************************************* Hero Section End***************************************************************** -->
                <!-- Products -->
                <section class="shop container"  id="item">


                    <h2 class="section-title">Shop Products</h2>
                    <!-- ------------------------------------------------------------------ -->
                    <div class="control">

                        <div class="btn-group">
                            <button >
                                <a  href="ItemController?act=index" class="vs" onclick="submitForm()" >
                                    <span class="ml-2"  >All Items</span>
                                </a>
                            </button>
                            <%

                                List<Category> categoryList = (List<Category>) request.getAttribute("allcategories");
                                if (categoryList != null && !categoryList.isEmpty()) {

                                    for (Category c : categoryList) {

                            %>
                            <button >
                                <a  href="ItemController?act=indexitembycategoryid&categoryId=<%= c.getCategoryId()%>" onclick="submitForm()" >
                                    <span class="ml-2"  > <%= c.getName()%></span>
                                </a>
                            </button>

                            <%
                                    }
                                }
                            %>
                        </div>
                        <input type="text" id="search-input" placeholder="Search item">
                        <select id="filter-select">
                            <option value="">All</option>
                            <option value="price">Price</option>
                            <option value="name">Name</option>
                        </select>

                        <div id="itemId-list">
                        </div>

                    </div>



                    <div class="shop-content">

                        <%
                            List<Item> itemList = (List<Item>) request.getAttribute("allItems");
                            if (itemList != null && !itemList.isEmpty()) {

                                for (Item i : itemList) {
                        %>

                        <!-- Box 1  -->
                        <div class="product-box" data-item="<%=i.getDescription()%>">
                            <!-- Stripe needs images that are online on local server it will not show in stripe checkout page we have to upload online -->
                            <img src="images/<%=i.getImageUrl()%>"
                                 alt="item image" class="product-img" />
                            <h2 class="product-title"><%=i.getItemName()%></h2>
                            <span class="price">R<%=i.getPrice()%>0</span>

                        </div>


                        <%
                                }
                            }
                        %>


                </section>
                <!--******************************************************* About Section Start***************************************************************** -->
                <section class="about" id="about">
                    <div class="container">

                        <figure class="about-banner">
                            <img src="images/about.jpg" alt="">

                            <img src="images/chef-dusting-table-with-flour-dough-kneading.jpg" alt="" class="abs-img">
                        </figure>

                        <div class="about-content">

                            <p class="section-subtitle">About Us</p>

                            <h2 class="h2 section-title">To Pie For Bakery</h2>

                            <p class="about-text">
                                Over 20-30 customers buy our products Everyday!
                            </p>

                            <ul class="about-list">

                                <li class="about-item">
                                    <div class="about-item-icon">
                                        <ion-icon name="stove-outline"></ion-icon>
                                    </div>

                                    <p class="about-item-text">We specialize in baking </p>
                                </li>



                            </ul>

                            <p class="callout">
                                Let our products fill our heart
                            </p>



                        </div>

                    </div>
                </section>
                <!--******************************************************* About Section End***************************************************************** -->

            </article>
        </main>

        <!-- 
        - #FOOTER
        -->

        <footer class="footer">

            <div class="footer-top">
                <div class="container">

                    <div class="footer-brand">

                        <a href="ItemController?act=viewhomeitems" >
                            <p><span style="font-weight:bold;"> ToPieFor</span>
                            </p>
                            <br>
                        </a>

                        <ul class="contact-list" id="contact">

                            <li>
                                <a href="#" class="contact-link">
                                    <ion-icon name="location-outline"></ion-icon>

                                    <address>16th, Midrand, Johannesburg, South Africa</address>
                                </a>
                            </li>

                            <li>
                                <a href="tel:+0123456789" class="contact-link">
                                    <ion-icon name="call-outline"></ion-icon>

                                    <span>+2711 7576 334</span>
                                </a>
                            </li>

                            <li>
                                <a href="mailto:contact@topiefor.com" class="contact-link">
                                    <ion-icon name="mail-outline"></ion-icon>

                                    <span>topieforbakery2@gmail.com</span>
                                </a>
                            </li>

                        </ul>

                        <ul class="social-list">

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-facebook"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-twitter"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-linkedin"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-youtube"></ion-icon>
                                </a>
                            </li>

                        </ul>

                    </div>

                    <div class="footer-link-box">

                        <ul class="footer-list">

                            <li>
                                <p class="footer-list-title">Company</p>
                            </li>

                            <li>
                                <a href="#" class="footer-link">About</a>
                            </li>

                            <li>
                                <a href="#item" class="footer-link">All Products</a>
                            </li>

                            <li>
                                <a href="mailto:contact@topiefor.com" class="footer-link">Contact us</a>
                            </li>

                        </ul>


                    </div>

                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">

                    <p class="copyright">
                        &copy; 2023 <a href="#"></a>. All Rights Reserved
                    </p>

                </div>
            </div>

        </footer>

        <script src="js/HomePageScript.js"></script>

        <!-- 
        - ionicon link
        -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>
