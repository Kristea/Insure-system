<%-- 
    Document   : RegPageInsured
    Created on : 11-Mar-2015, 20:06:22
    Author     : Claudia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Insured</title>
    </head>
    <body>
        <div id="header"><img src="SIlogo.png" id="logo" alt="logo"></div>
        <div class="left"></div>
        <div class ="main">		
            <h1>Register Insured</h1>
            <div class="form">   
                <form method="post" action="RegisterInsured" id="newUser">
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
                    <p><label for="dayDOB">Date of Birth </label> 
                        <input type="text" name="dayDOB" id="dayDOB"/>
                        <input type="text" name="monthDOB" id="monthDOB"/>
                        <input type="text" name="yearDOB" id="yearDOB"/>
                    </p>          
                    <h2> About Your Gadget</h2>
                    <p><label for="type"> Type </label>
                        <select size="1" name="type" id="type">
                            <option value="Smartphone">Smartphone</option>
                            <option value="Tablet">Tablet</option>
                            <option value="Laptop">Laptop</option>
                        </select></p>
                    <p><label for="make">Make </label>
                        <select size="1" name="make" id="make">
                            <option value="Apple iPhone">Apple iPhone</option>
                            <option value="HTC">HTC</option>
                            <option value="Samsung">Samsung</option>
                            <option value="Sony">Sony</option>
                        </select>
                    </p>
                    <p><label for="model">Model </label>
                        <select size="1" name="model" id="model">
                            <option value="Galaxy S5">Galaxy S5</option>
                            <option value="Galaxy S6">Galaxy S6</option>
                            <option value="Galaxy Note 6">Galaxy Note 6</option>
                            <option value="6">6</option>
                            <option value="5s">5s</option>
                            <option value="One M8">One M8</option>
                            <option value="Desire 620">Desire 620</option>
                            <option value="Xperia Z2">Xperia Z2</option>
                            <option value="Xperia Z3">Xperia Z3</option>
                        </select>
                    </p>
                    <h3> About Your Insurance </h3>
                    <p><label for="age">Gadget Age </label>
                        <select size="1" name="age" id="age">
                            <option value="3">Under 3 Months</option>
                            <option value="6">Under 6 Months</option>
                            <option value="12">Under 12 Months</option>
                            <option value="18">Under 18 Months</option>
                            <option value="19">More than 18 Months</option>
                        </select>
                    </p>
                    <p><label for="condition"> Condition </label>
                        <select size="1" name="condition" id="condition">
                            <option value="New">New</option>
                            <option value="Refurbished">Refurbished</option>
                            <option value="Used - Like New">Used - Like New</option>
                            <option value="Used - Good">Used - Good</option>
                            <option value="Used - Fair">Used - Fair</option>
                        </select>
                    </p>
                    <p><label for="env">Environment You Use Gadget in </label>
                        <select size="1" name="env" id="env">
                            <option value="Indoor">Indoor</option>
                            <option value="Outdoor">Outdoor</option>
                        </select>
                    </p>
                    <p><label for="typeOfCover"> Type of Cover </label>
                        <select size="1" name="typeOfCover" id="typeOfCover">
                            <option value="Accidental Damage">Accidental Damage</option>
                            <option value="Theft">Theft</option>
                            <option value="Breakdown">Breakdown</option>
                            <option value="Loss">Loss</option>
                        </select></p>
                    <p><label for="coverPeriod"> Cover Period </label>
                        <select size="1" name="coverPeriod" id="coverPeriod">
                            <option value="6">6 months</option>
                            <option value="12">12 months</option>
                            <option value="18">18 months</option>
                            <option value="24">24 months</option></select></p>

                       <p><label for="showRequirement"> Make Requirement Public </label>
                        <select size="1" name="showRequirement" id="showRequirement">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                           </select></p>


                    <p><input type="submit" value="Create Account"></p>
                </form></div>


            <div class="right"></div>
            <div id="footer">
                <p>Copyright 2015 SURE-INSURE UEA © </p>
            </div>
    </body>
</html>
