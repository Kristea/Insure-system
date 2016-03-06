<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Feedback</title>
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
                    <h1> Feedback </h1>

                    <form  method="post" action="GiveFeedback" id="giveFeedback">
                        <input type="radio" name="rating" value="1" class="star">
                        <input type="radio" name="rating" value="2" class="star">
                        <input type="radio" name="rating" value="3" class="star">
                        <input type="radio" name="rating" value="4" class="star">
                        <input type="radio" name="rating" value="5" class="star">
                    </form>
                        
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
