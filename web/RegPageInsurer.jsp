<%-- 
    Document   : RegPageInsurer
    Created on : 15-Mar-2015, 21:21:16
    Author     : Claudia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Insurer</title>
    </head>
    <body>
        <div id="header"><img src="SIlogo.png" id="logo" alt="logo"></div>
        <div class="left"></div>
        <div class ="main">		 
            <h1>Register Insurer</h1>
            <form method="post" action="RegisterInsurer" id="newUser">
                <p> <label for="username">Username </label><input type="text" name="username" id="username"/> </p>
                <p> <label for="password">Password </label><input type="text" name="password" id="password"/> </p>
                <p><label for="email">Email </label><input type="text" name="email" id="email"/></p>

                <h2> About You </h2>
                <p><label for="title">Title </label>                        
                    <select size="1" name="title" id="title">
                        <option value="Mr">Mr</option>
                        <option value="Miss">Miss</option>
                        <option value="Mrs">Mrs</option>
                        <option value="Ms">Ms</option>
                        <option value="Dr">Dr</option>
                    </select></p>

                <p><label for="firstname"> First Name </label><input type="text" name="firstname" id="firstname"/></p>
                <p><label for="surname"> Surname </label> <input type="text" name="surname" id="surname"/></p>
                <p><label for="address1"> Address Line 1 </label> <input type="text" name="address1" id="address1"/></p>
                <p><label for="street"> Street </label> <input type="text" name="street" id="street"/></p>
                <p><label for="city"> City </label> <input type="text" name="city" id="city"/></p>
                <p><label for="postcode"> Postcode </label> <input type="text" name="postcode" id="postcode"/></p>

                <h2> Insurance Information </h2>
                <p><label for="type"> Gadget Type </label>
                    <select size="1" name="type" id="type">
                        <option value="Smartphone">Smartphone</option>
                        <option value="Tablet">Tablet</option>
                        <option value="Laptop">Laptop</option>
                    </select></p>
                <p><label for="condition"> Condition </label>
                    <select size="1" name="condition" id="condition">
                        <option value="New">New</option>
                        <option value="Refurbished">Refurbished</option>
                        <option value="Used - Like New">Used - Like New</option>
                        <option value="Used - Good">Used - Good</option>
                        <option value="Used - Fair">Used - Fair</option>
                    </select>
                </p>
                <p><label for="minPrice"> Minimum Price </label> <input type="text" name="minPrice" id="minPrice"/></p>
                <p><label for="maxPrice"> Maximum Price </label> <input type="text" name="maxPrice" id="maxPrice"/></p>
                <p><label for="typeOfCover"> Type of Cover </label>
                    <select size="1" name="typeOfCover" id="typeOfCover">
                        <option value="Accidental Damage">Accidental Damage</option>
                        <option value="Theft">Theft</option>
                        <option value="Breakdown">Breakdown</option>
                        <option value="Loss">Loss</option>
                    </select></p>
                <p><label for="coverPeriod"> Maximum Cover Period </label>
                    <select size="1" name="coverPeriod" id="coverPeriod">
                        <option value="6">6 months</option>
                        <option value="12">12 months</option>
                        <option value="18">18 months</option>
                        <option value="24">24 months</option>
                </p>


                <p><input type="submit" value="Create Account"></p>
            </form>

            <div class="right"></div>
            <div id="footer">
                <p>Copyright 2015 SURE-INSURE UEA © </p>
            </div>
    </body>
</html>
