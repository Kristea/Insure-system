<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Add Insurance Requirement</title>
    </head>
    <%@page import ="java.util.*"%>
    <%@page import ="Model.User"%>
    <%@page import ="Model.Insured"%>
    <%@page import ="Model.InsuranceRequirement"%>
    <body>
        <div class="header">
            <div class="container">
                <a href="index.jsp"><img src="SIlogo.png" id="logo" alt="logo"></a>
                <p>Peer to Peer gadget insurance</p>
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
                    <li><a href="ProfilePageInsured.jsp">Home</a></li>
                    <li><a href="OverviewPage_Insured.jsp">Overview</a></li>
                    <li><a href="Logout.jsp">Logout</a></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">
                    <h1>Add Insurance Requirement</h1>
                    <div class="reqWrap">
                        <%Insured ins = (Insured) session.getAttribute("insured");%>
                        <form method="post" action="AddInsuranceRequirement" name="addInsReq" id="addInsReq">
                            <div class="container">
                                <input type="hidden" name="insuredUsername" id="insuredUsername" value="${insured.username}"/>
                                <h2> About Your Gadget</h2>
                                <label>Type</label>
                                <select name="type" id="type" onchange="javascript: listboxchange1(this.options[this.selectedIndex].value);">
                                    <option value="" selected="selected" disabled="disabled">Select Category</option>
                                    <option value="Smartphone">Smartphone</option>
                                    <option value="Tablet">Tablet</option>
                                    <option value="Laptop">Laptop</option>
                                </select><br/>
                                <label>Make</label>
                                <select name="make" id="make" onChange="javascript: listboxchange(this.options[this.selectedIndex].value);">
                                    <option value="" selected="selected" disabled="disabled">Select Make</option>
                                </select><br/>
                                <label>Model</label>
                                <select name="model" id ="model">
                                    <option value="" selected="selected" disabled="disabled">Select Model</option>
                                </select><br/>
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
                                <p><label for="env">Environment </label>
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
                            </div>
                            <p><input type="submit" value="Add Requirement" class="btn"></p>
                        </form>
                    </div>
                    <div class="aside">

                    </div>
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