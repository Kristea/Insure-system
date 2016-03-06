<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Viewing ${insurer.fName} ${insurer.sName}</title>
    </head>
    <%@page import ="java.util.*"%>
    <%@page import ="Model.User"%>
    <%@page import ="Model.Insurer"%>
    <%@page import ="Model.RiskThreshold"%>
    <%@page import ="Model.Gadget"%>
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
                   
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">

                    <h1>${insurer.title} ${insurer.fName} ${insurer.sName} !</h1>

                    <h3> Risk Thresholds </h3> 
                    <%
                        List<RiskThreshold> riskThresholds = (List) session.getAttribute("rList");
                       Insurer insurer = (Insurer) session.getAttribute("insurer");
                        Iterator it = riskThresholds.iterator();

                        while (it.hasNext()) {
                            RiskThreshold rt = (RiskThreshold) it.next();

                    %>
                    <table class="table table-striped"> 
                        <tr>
                            <th> 
                        <h4> Threshold ID:  <%=rt.getRiskT_id()%> </h4>
                        </th>
                        </tr>
                        <tr>
                            <td> 
                                <div class="container">   
                                    <div class="sectionheader">
                                        <h4> Risk Threshold</h4> 
                                        <div>
                                            <p class="before"> Gadget Type: </p><p class="after"> <%=rt.getGadgetType()%> </p><br/>
                                            <% String min = String.format("%.02f", rt.getMinPrice());
                                    String max = String.format("%.02f", rt.getMaxPrice());%>
                                            <p class="before">  Minimum Price Insured:</p><p class="after">  £<%=min%></p><br/>
                                            <p class="before">  Maximum Price Insured:</p><p class="after">  £<%=max%></p><br/>
                                            <p class="before"> Type of Cover Provided:</p><p class="after">  <%=rt.getTypeOfCover()%> </p><br/>
                                            <p class="before"> Maximum Cover Period:</p><p class="after">  <%=rt.getCoverPeriod()%> months </p><br/>
                                            <p class="before"> Conditions Accepted:</p><p class="after">
                                                <% for (String con : rt.getGadgetConditions()) {
                                                %>
                                                <%=con%>
                                                <br/>
                                                <%
                                                        }
                                                    }%>  </p><br/>
                                        </div>
                                    </div>
                                </div>   
                            </td>
                        </tr>
                    </table>
                     

                </div>
                <div class="aside">
                    <p> <img src="default_profile_image.png"  id="profile_pic" alt="profile picture"><p>
                    <p class="before"> Role: </p><p class="after">${insurer.role} </p> <br/>
                    <p class="before"> Email: </p><p class="after">${insurer.email} </p><br/>
                    <p class="before"> Address Line 1:</p><p class="after"> ${insurer.addressLine1} </p><br/>
                    <p class="before"> Street:</p><p class="after">  ${insurer.street} </p><br/>
                    <p class="before"> City:</p><p class="after"> ${insurer.city} </p><br/>      
                    <p class="before"> Postcode:</p><p class="after"> ${insurer.postcode} </p>  <br/>

                    <h3>Rating</h3>
                    <%--<% String feed = String.format("%.01f", insured.getRating());%>--%>
                    <% int feed = (int) Math.ceil(insurer.getRating());%>
                    <p><span class="stars"><%=feed%></span></p>
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
        <script type="text/javascript" src="sections.js"></script>
    </body>
</html>
