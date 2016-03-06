<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>HomePpage</title>
    </head>
    <body>
<div class="header">
            <div class="container">
                <a href="index.jsp"><img src="SIlogo.png" id="logo" alt="logo"></a>
                <p>Peer to Peer gadget insurance</p>
                  <div class="search-bar">
                    <div class="sectionheader">
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
                        <h4 class="btn now">Search Insureds By Name</h4>
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
                    <h1> SURE INSURE </h1>

                    <h2 class="intro">Who are we?</h2>
                     <p>We are an up and coming company within AVIVA©.</p>
                    <p>Our website is a comparison and searching engine
                        for anyone looking for a quote regarding insurance for
                        Smartphones, Tablets and other electronic gadgets. 
                    <p> SureInsure caters for both individuals looking for 
                        nsurance company quotes, as well independent insurers 
                        and leading insurance businesses to ensure that we 
                        provide a comprehensive list of insurance sources and 
                        client in the market. </p>
                    <p>We will match your criteria with
                        providers as close as possible to ensure the best 
                        service and then send out instant requests to these
                        partners to enable as easy convenience as possible as
                        well as the most flexibility we can offer, ensuring 
                        that we listen to our partners and customers by 
                        encouraging a feedback service to shape your insurance 
                        cover to suit your needs and requirements.</p>
                </div>
                <div class="aside">
                     <img src="gadgets1.png" id="gadgets1" class="img-responsive" alt="gadgets">
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
        <%--JAVASCRIPT--%>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="sections.js"></script>
    </body>
</html>
