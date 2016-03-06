<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Register</title>
    </head>
    <body>

        <div class="header">
            <div class="container">
                <a href="index.jsp"><img src="SIlogo.png" id="logo" alt="logo"></a>

                <div class="search-bar">
                    <div class="sectionheader current">
                        <h4 class="btn now">Search Insurers By Name</h4>
                        <div class="section">
                            <form method="post" action="SearchInsurer_ByName" id="SearchInsurer_ByName" >
                                <input type="text" name="name" id="name" placeholder="Name" required/>
                                <input type="submit" value="Search" class="btn"/>
                                <a href="SearchInsurer_All" class="btn"> Search All</a>
                            </form>
                        </div>
                    </div>

                    <div class="sectionheader">
                        <h4 class="btn">Search Insureds By Name</h4>
                        <div class="section">
                            <form method="post" action="SearchInsured_ByName" id="SearchInsured_ByName" >
                                <input type="text" name="name" id="name" placeholder="Name" required/>
                                <input type="submit" value="Search" class="btn"/> 
                                <a href="SearchInsured_All" class="btn"> Search All</a>
                            </form>

                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="nav-bar">
            <div class="container">
                <ul class="nav">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="LoginPage.jsp">Login</a></li>
                    <li><a href="Register.jsp">Register</a></li>
                </ul>
            </div>
        </div>


        <div class="content">
            <div class="container">
                <div class ="main">
                    <div id="formWrap" class="formWrap">

                        <%--buttons to link to insurer/insured forms go at the top of each form--%>
                        <form class="registerInsured active" method="post" action="RegisterInsured" name ="newUser" id="newUser">
                            <a href="RegisterInsured.jsp" rel="registerInsured" class="linkform btn now">Insured</a>
                            <a href="RegisterInsurer.jsp" rel="registerInsurer" class="linkform btn">Insurer</a>

                            <h1>Register as Insured</h1>
                            <div class="formcontainer">
                                <div class="sectionheader">
                                    <h3>Account Details</h3>
                                    <div class ="section">                                
                                        <p> <label for="username">Username </label><input type="text" name="username" id="username" size ="10" required/> </p>
                                        <p> <label for="password">Password </label><input type="password" name="password" id="password" required/> </p>
                                        <p><label for="email">Email </label><input type="email" name="email" id="email" required/></p>
                                    </div>
                                </div>
                                <div class="sectionheader"><h3> About You </h3>
                                    <div class ="section">
                                        <p><label for="title">Title </label>                        
                                            <select size="1" name="title" id="title" required>
                                                <option selected="selected" disabled="disabled">Select a title</option>
                                                <option value="Mr">Mr</option>
                                                <option value="Miss">Miss</option>
                                                <option value="Mrs">Mrs</option>
                                                <option value="Ms">Ms</option>
                                                <option value="Dr">Dr</option>
                                            </select>
                                        </p>
                                        <p><label for="firstname"> First Name </label><input type="text" name="firstname" id="firstname" required/></p>
                                        <p><label for="surname"> Surname </label> <input type="text" name="surname" id="surname"  required/></p>
                                        <p><label for="address1"> House name/no </label> <input type="text" name="address1" id="address1" required/></p>
                                        <p><label for="street"> Street </label> <input type="text" name="street" id="street" required/></p>
                                        <p><label for="city"> City </label> <input type="text" name="city" id="city" required/></p>
                                        <p><label for="postcode"> Postcode </label> <input type="text" name="postcode" id="postcode" required/></p>
                                        <p><label for="DOB">Date of Birth </label> 
                                            <input type="date" name="dob" id="dob" placeholder="dd/mm/yyyy"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="sectionheader">
                                    <h3> About Your Gadget</h3>
                                    <div class ="section">
                                        <label>Type</label>
                                        <select name="type" id="type" onchange="javascript: listboxchange1(this.options[this.selectedIndex].value);">
                                            <option value="" selected="selected" disabled="disabled">Select Category</option>
                                            <option value="Smartphone">Smartphone</option>
                                            <option value="Tablet">Tablet</option>
                                            <option value="Laptop">Laptop</option>
                                        </select>
                                        <label>Make</label>
                                        <select name="make" id="make" onChange="javascript: listboxchange(this.options[this.selectedIndex].value);">
                                            <option value="" selected="selected" disabled="disabled">Select Make</option>
                                        </select>
                                        <label>Model</label>
                                        <select name="model" id ="model">
                                            <option value="" selected="selected" disabled="disabled">Select Model</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="sectionheader">
                                    <h3> About Your Insurance </h3>
                                    <div class="section">
                                        <p><label for="age">Gadget Age </label>
                                            <select size="1" name="age" id="age" required>
                                                <option selected="selected" disabled="disabled">Select age</option>
                                                <option value="1">Under 3 Months</option>
                                                <option value="2">Under 6 Months</option>
                                                <option value="3">Under 12 Months</option>
                                                <option value="4">Under 18 Months</option>
                                                <option value="5">More than 18 Months</option>
                                            </select>
                                        </p>
                                        <p><label for="condition"> Condition </label>
                                            <select size="1" name="condition" id="condition" required>
                                                <option selected="selected" disabled="disabled">Select condition</option>
                                                <option value="New">New</option>
                                                <option value="Refurbished">Refurbished</option>
                                                <option value="Used - Like New">Used - Like New</option>
                                                <option value="Used - Good">Used - Good</option>
                                                <option value="Used - Fair">Used - Fair</option>
                                            </select>
                                        </p>
                                        <p><label for="env">Environment</label>
                                            <select size="1" name="env" id="env" required>
                                                <option selected="selected" disabled="disabled">Select usage environment</option>
                                                <option value="Indoor">Indoor</option>
                                                <option value="Outdoor">Outdoor</option>
                                            </select>
                                        </p>
                                        <p><label for="typeOfCover"> Type of Cover </label>
                                            <select size="1" name="typeOfCover" id="typeOfCover" required>
                                                <option selected="selected" disabled="disabled">Select cover type</option>
                                                <option value="Accidental Damage">Accidental Damage</option>
                                                <option value="Theft">Theft</option>
                                                <option value="Breakdown">Breakdown</option>
                                                <option value="Loss">Loss</option>
                                            </select>
                                        </p>
                                        <p><label for="coverPeriod"> Cover Period </label>
                                            <select size="1" name="coverPeriod" id="typeOfCover" required>
                                                <option selected="selected" disabled="disabled">Select a title</option>
                                                <option value="6">6 months</option>
                                                <option value="12">12 months</option>
                                                <option value="18">18 months</option>
                                                <option value="24">24 months</option>
                                            </select>
                                        </p>


                                        <p><label for="showRequirement">Requirement Public?</label>
                                            <select size="1" name="showRequirement" id="showRequirement" required>
                                                <option selected="selected" disabled="disabled">Select option</option>
                                                <option value="true">Yes</option>
                                                <option value="false">No</option>
                                            </select>
                                        </p>
                                    </div>
                                </div>
                                <p><input type="submit" value="Create Account" class="btn"/></p>
                            </div>
                        </form>

                        <%--buttons to link to insurer/insured forms go at the top of each form--%>
                        <form class="registerInsurer" method="post" action="RegisterInsurer" id="newUser">
                            <a href="RegisterInsured.jsp" rel="registerInsured" class="linkform btn">Insured</a>
                            <a href="RegisterInsurer.jsp" rel="registerInsurer" class="linkform btn now">Insurer</a>

                            <h1>Register as Insurer</h1>
                            <div class="formcontainer">
                                <div class="sectionheader">
                                    <h3>Account Details</h3>
                                    <div class="section">
                                        <p> <label for="username">Username </label><input type="text" name="username" id="username" required/> </p>
                                        <p> <label for="password">Password </label><input type="password" name="password" id="password" required/> </p>
                                        <p><label for="email">Email </label><input type="type" name="email" id="email" required/></p>
                                    </div></div>
                                <div class="sectionheader">
                                    <h3> About You </h3>
                                    <div class="section">
                                        <p><label for="title">Title </label>                        
                                            <select size="1" name="title" id="title" required>
                                                <option selected="selected" disabled="disabled">Select a title</option>
                                                <option value="Mr">Mr</option>
                                                <option value="Miss">Miss</option>
                                                <option value="Mrs">Mrs</option>
                                                <option value="Ms">Ms</option>
                                                <option value="Dr">Dr</option>
                                            </select></p>

                                        <p><label for="firstname"> First Name </label><input type="text" name="firstname" id="firstname" required/></p>
                                        <p><label for="surname"> Surname </label> <input type="text" name="surname" id="surname" required/></p>
                                        <p><label for="address1"> Address Line 1 </label> <input type="text" name="address1" id="address1" required/></p>
                                        <p><label for="street"> Street </label> <input type="text" name="street" id="street" required/></p>
                                        <p><label for="city"> City </label> <input type="text" name="city" id="city" required/></p>
                                        <p><label for="postcode"> Postcode </label> <input type="text" name="postcode" id="postcode" required/></p>
                                    </div>
                                </div>
                                <div class="sectionheader">
                                    <h3> Insurance Information </h3>
                                    <div class="section">
                                        <p><label for="type"> Gadget Type </label>
                                            <select size="1" name="type" id="type" required>
                                                <option selected="selected" disabled="disabled">Select a gadget</option>
                                                <option value="Smartphone">Smartphone</option>
                                                <option value="Tablet">Tablet</option>
                                                <option value="Laptop">Laptop</option>
                                            </select></p>
                                        <p><label for="condition"> Condition </label>
                                            <select size="1" name="condition" id="condition" required>
                                                <option selected="selected" disabled="disabled">Select condition</option>
                                                <option value="New">New</option>
                                                <option value="Refurbished">Refurbished</option>
                                                <option value="Used - Like New">Used - Like New</option>
                                                <option value="Used - Good">Used - Good</option>
                                                <option value="Used - Fair">Used - Fair</option>
                                            </select>
                                        </p>
                                        <p><label for="minPrice"> Minimum Price </label> 
                                            <input type="text" name="minPrice" id="minPrice" required/>
                                        </p>
                                        <p><label for="maxPrice"> Maximum Price </label> 
                                            <input type="text" name="maxPrice" id="maxPrice" required/>
                                        </p>
                                        <p><label for="typeOfCover"> Type of Cover </label>
                                            <select size="1" name="typeOfCover" id="typeOfCover" required>
                                                <option selected="selected" disabled="disabled">Select cover</option>
                                                <option value="Accidental Damage">Accidental Damage</option>
                                                <option value="Theft">Theft</option>
                                                <option value="Breakdown">Breakdown</option>
                                                <option value="Loss">Loss</option>
                                            </select></p>
                                        <p><label for="coverPeriod"> Max Cover Period </label>
                                            <select size="1" name="coverPeriod" id="coverPeriod" required>
                                                <option selected="selected" disabled="disabled">Select cover period</option>
                                                <option value="6">6 months</option>
                                                <option value="12">12 months</option>
                                                <option value="18">18 months</option>
                                                <option value="24">24 months</option>
                                            </select>
                                        </p>
                                    </div>
                                </div>
                                <p><input type="submit" value="Create Account" class="btn"/></p>
                            </div>
                        </form>
                    </div>

                    <%--
                    <form class="forgot_password">
                        <h3>Forgot Password</h3>
                        <div>
                            <label>Username or Email:</label>
                            <input type="text" />
                            <span class="error">This is an error</span>
                        </div>
                        <div class="bottom">
                            <input type="submit" value="Send reminder"></input>
                            <a href="index.html" rel="login" class="linkform">Suddenly remebered? Log in here</a>
                            <a href="register.html" rel="register" class="linkform">You don't have an account? Register here</a>
                            <div class="clear"></div>
                        </div>
                    </form>--%>
                 </div>
                <div class="aside">
                    <img src="hands.png" id="hands" alt="hands">
                </div>
            </div>
        </div>

        <div class="footer">
            <div class="container">
                <hr>
                <p>Copyright 2015 SURE-INSURE UEA ©
                    Disclaimer: This application is not a commercial application and does not provide
                    insurance. This is a study project that is part of a Computing Science module taught at the
                    University of East Anglia, Norwich, UK. If you have any questions, please contact the
                    module coordinator, Joost Noppen, at j.noppen@uea.ac.uk</p>
            </div> 
        </div>
        <!-- The JavaScript -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src ="register.js"></script>
        <script type="text/javascript" src ="sections.js"></script>
    </body>
</html>