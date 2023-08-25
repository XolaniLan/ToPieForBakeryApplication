<%-- 
    Document   : AccountSettings
    Created on : Jun 22, 2023, 9:24:07 AM
    Author     : STUDIO 18
--%>

<%@page import="za.co.bakery.model.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Account Settings</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- <link rel="stylesheet" type="text/css"
                href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/AccountSettingStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <section class="py-5 my-5">
            <div class="container">
                <h1 class="mb-5">Account Settings</h1>
                <div class="bg-white shadow rounded-lg d-block d-sm-flex">
                    <div class="profile-tab-nav border-right">
                        <div class="p-4">
                            <h4 class="text-center">User(name)</h4>
                        </div>
                        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                            <a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab"
                               aria-controls="account" aria-selected="true">
                                <i class="fa fa-home text-center mr-1"></i>
                                Personal Details
                            </a>
                            <a class="nav-link" id="password-tab" data-toggle="pill" href="#address" role="tab"
                               aria-controls="password" aria-selected="false">
                                <i class="fa fa-address-card-o mr-1"></i>
                                Address Book
                            </a>
                            <a class="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab"
                               aria-controls="password" aria-selected="false">
                                <i class="fa fa-key text-center mr-1"></i>
                                Password
                            </a>
                            <a class="nav-link" id="password-tab" data-toggle="pill" href="#logout" role="tab"
                               aria-controls="password" aria-selected="false">
                                <i class="fa fa-sign-out mr-1" ></i>
                                Logout
                            </a>
                        </div>
                    </div>
                    <div class="tab-content p-4 p-md-5" id="v-pills-tabContent">



                        <!--------------------------- Personal Details------------------------------- -->

                        <div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
                            <h3 class="mb-4">Personal Details</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>First Name</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Last Name</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="email" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Contact Number</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button class="btn btn-primary">Update</button>
                                <button class="btn btn-light">Cancel</button>
                            </div>
                        </div>


                        <!---------------------------------------- Address Book ------------------------------------->
                        <div class="tab-pane fade " id="address" role="tabpanel" aria-labelledby="address-tab">
                            <h3 class="mb-4">Address Book</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Address Line 1</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Address Line 2</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="country">Provinces</label><span style="color: red !important; display: inline; float: none;">*</span>      
                                        <select id="country" name="country" class="form-control">
                                            <option value="KZN">KwaZulu-Natal</option>
                                            <option value="limpopo">Limpopo</option>
                                            <option value="gauteng" selected>Gauteng</option>
                                            <option value="freestate">Free State</option>
                                            <option value="easterncape">Eastern Cape</option>
                                            <option value="mpumalanga">Mpumalanga</option>
                                            <option value="northerncape">Northern Cape</option>
                                            <option value="northwest">North West </option>
                                            <option value="westerncape">Western Cape</option>	
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Country</label>
                                        <input type="text" class="form-control" value="South Africa">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>City</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Zip Code</label>
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button class="btn btn-primary">Update</button>
                                <button class="btn btn-light">Cancel</button>
                            </div>
                        </div>


                        <!-------------------------------- Update Password ------------------------------------->
                        <div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                            <h3 class="mb-4">Update Password</h3>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Old password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>New Password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Confirm New Password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button class="btn btn-primary">Update</button>
                                <button class="btn btn-light">Cancel</button>
                            </div>
                        </div>
                        <!-- ----------------------------------------------------------------------------------- -->
                        <div class="tab-pane fade" id="logout" role="tabpanel" aria-labelledby="password-tab">
                            <h3 class="mb-44">Are you sure you want to logout?</h3>
                            <div class="logout">
                                <a href="ItemController?act=index" >
                                    <button 
                                        class="btn btn-primary">Yes</button>
                                </a>
                                <a href="ItemController?act=viewhomeitems" >
                                <button class="btn btn-light">No</button>
                                </a>
                            </div>
                        </div>
                        <!-- ----------------------------------------------------------------------------------- -->
                    </div>
                </div>
            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="js/AccountSettingJs.js"></script>
</body>

</html>

