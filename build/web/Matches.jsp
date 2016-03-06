<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Matches</title>
    </head>
    <%@page import ="java.util.*"%>
    <%@page import ="Model.User"%>
    <%@page import ="Model.Insurer"%>
    <%@page import ="Model.Insured"%>
    <%@page import ="Model.InsuranceRequirement"%>
    <%@page import ="Model.RiskThreshold"%>
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
                    <li><a href="ProfilePageInsured.jsp">Home</a></li>
                    <li><a href="Logout">Logout</a></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">
                    <h1>Matches</h1>
                    <%
                        List<Insurer> perfectMatches = (List) session.getAttribute("perfectMatches");
                        List<String> perfectMatching_rtid = (List) session.getAttribute("perfectMatching_RTID");
                        List<Insurer> closestMatches = (List) session.getAttribute("closestMatches");
                        List<String> closestMatching_rtid = (List) session.getAttribute("closestMatching_rtid");
                        List<Insurer> closeMatches = (List) session.getAttribute("closeMatches");
                        List<String> closeMatching_rtid = (List) session.getAttribute("closeMatching_rtid");

                        Iterator it = perfectMatches.iterator();
                        if (perfectMatches.isEmpty() == false) { %>
                    <h2> Perfect Matches </h2>
                    <% while (it.hasNext()) {
                            Insurer insurer = (Insurer) it.next();
                            String request_rtid = "";
                    %>    
                    <h3>  <a href="ViewProfiles?username=<%=insurer.getUsername()%>"><%=insurer.getTitle()%> <%=insurer.getfName()%> <%=insurer.getsName()%> </a></h3>

                    <% for (RiskThreshold r : insurer.getRiskThresholds()) {
                            for (String rtid : perfectMatching_rtid) {
                                if (r.getRiskT_id().contains(rtid)) {
                                    request_rtid = rtid;
                    %>


                    <p class="before"> Risk Threshold ID: </p><p class="after"> <%=r.getRiskT_id()%> <br/>
                    <p class="before"> Gadget Type:</p><p class="after">  <%=r.getGadgetType()%> <br/>
                        <% String min = String.format("%.02f", r.getMinPrice());
                        String max = String.format("%.02f", r.getMaxPrice());%>
                    <p class="before">  Minimum Price Insured:</p><p class="after"> £<%=min%></p><br/>
                    <p class="before">  Maximum Price Insured:</p><p class="after"> £<%=max%></p><br/>
                    <p class="before">  Type of Cover Provided:</p><p class="after">  <%=r.getTypeOfCover()%> <br/>
                    <p class="before"> Maximum Cover Period Covered:</p><p class="after">  <%=r.getCoverPeriod()%> months <br/>
                    <p class="before"> Conditions Accepted:</p><p class="after">
                        <% for (String con : r.getGadgetConditions()) {%>
                        <%=con%>
                        <br/>
                        <%                 }%><br/> </p>
                        <%  }
                                      }
                                  }%>
                    <form method="post" action="SendRequest" id="sendRequest">
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="${insured.username}"/></p>
                        <p><input type ="hidden" name="perfect_match" id="perfect_match" value="true"/></p>
                        <p><input type ="hidden" name="insurerEmail" id="insurerEmail" value="${insured.email}"/></p>
                            <% String insReqID = (String) session.getAttribute("insReqID");%>
                        <p><input type="hidden" name="insReqID" id="insReqID" value="<%=insReqID%>"/></p>
                        <p><input type="hidden" name="request_rtid" id="request_rtid" value="<%=request_rtid%>"/></p>
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insurerUsername" id="insurerUsername" value="<%=insurer.getUsername()%>"/></p>

                        <p><input type="submit" class="btn" value="Request" onclick="<%insurer.setNotification("Y");%>"></p></form>
                            <% }
                    } else if (closestMatches.isEmpty() == false) { %>
                    <h3> There are no Perfect Matches... </h3>
                    <h2> Closest Matches </h2
                    <%    Iterator it2 = closestMatches.iterator();
                        while (it2.hasNext()) {
                            Insurer insurer = (Insurer) it2.next();
                            String request_rtid = "";
                    %>   

                    <h3>  <a href="ViewProfiles?username=<%=insurer.getUsername()%>"><%=insurer.getTitle()%> <%=insurer.getfName()%> <%=insurer.getsName()%> </a> </h3>
                    <br/>
                    <% for (RiskThreshold r : insurer.getRiskThresholds()) {
                            for (String rtid : closestMatching_rtid) {
                                if (r.getRiskT_id().contains(rtid)) {
                                    request_rtid = rtid;
                    %>

                    <p class="before"> Risk Threshold ID:</p><p class="after">  <%=r.getRiskT_id()%><br/>
                    <p class="before">  Gadget Type: </p><p class="after"> <%=r.getGadgetType()%> <br/>
                        <% String min = String.format("%.02f", r.getMinPrice());
                        String max = String.format("%.02f", r.getMaxPrice());%>
                    <p class="before">  Minimum Price Insured:</p><p class="after"> £<%=min%><br/>
                    <p class="before">  Maximum Price Insured:</p><p class="after"> £<%=max%><br/>
                    <p class="before"> Type of Cover Provided:</p><p class="after">  <%=r.getTypeOfCover()%> <br/>
                    <p class="before"> Maximum Cover Period Covered:</p><p class="after">  <%=r.getCoverPeriod()%> months<br/>
                    <p class="before"> Conditions Accepted:</p><p class="after">
                        <% for (String con : r.getGadgetConditions()) {%>
                        <%=con%>
                        <%
                        } %> <br/> <br/>
                        <%}
                            }
                        }%>
                    <form method="post" action="SendRequest" id="sendRequest">
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="${insured.username}"/></p>
                        <p><input type ="hidden" name="perfect_match" id="perfect_match" value="false"/></p>
                            <% String insReqID = (String) session.getAttribute("insReqID");%>

                        <p><input type="hidden" name="insReqID" id="insReqID" value="<%=insReqID%>"/></p>
                        <p><input type="hidden" name="request_rtid" id="request_rtid" value="<%=request_rtid%>"/></p>
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insurerUsername" id="insurerUsername" value="<%=insurer.getUsername()%>"/></p>

                        <p><input type="submit" value="Request" onclick="<%insurer.setNotification("Y");%>"></p></form>
                            <%}
                            } else if (closeMatches.isEmpty() == false) { %>
                    <h3> There are no Perfect Matches... </h3>
                    <h2> Close Matches </h2>
                    <%   Iterator it3 = closeMatches.iterator();
                        while (it3.hasNext()) {
                            Insurer insurer = (Insurer) it3.next();
                            String request_rtid = "";
                    %>  
                    <h3><a href="ViewProfiles?username=<%=insurer.getUsername()%>"> See Profile </a></h3>

                    <% for (RiskThreshold r : insurer.getRiskThresholds()) {
                            for (String rtid : closeMatching_rtid) {
                                if (r.getRiskT_id().contains(rtid)) {
                                    request_rtid = rtid;
                    %>
                    <p class="before"> Risk Threshold ID:</p><p class="after">  <%=r.getRiskT_id()%> <br/>
                    <p class="before">  Gadget Type:</p><p class="after">  <%=r.getGadgetType()%> <br/>
                    <p class="before"> Minimum Price Insured:</p><p class="after"> £<%=r.getMinPrice()%><br/>
                    <p class="before"> Maximum Price Insured:</p><p class="after"> £<%=r.getMaxPrice()%><br/>
                    <p class="before">  Type of Cover Provided:</p><p class="after">  <%=r.getTypeOfCover()%> <br/>
                    <p class="before">  Maximum Cover Period Covered:</p><p class="after">  <%=r.getCoverPeriod()%> months <br/>
                    <p class="before"> Conditions Accepted:</p><p class="after">
                        <% for (String con : r.getGadgetConditions()) {%>
                        <%=con%></p>
                    <%
                        }%><br/> <br/>
                    <%  }
                                      }
                                  }%>
                    <form method="post" action="SendRequest" id="sendRequest">
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="${insured.username}"/></p>
                        <p><input type ="hidden" name="perfect_match" id="perfect_match" value="false"/></p>
                            <% String insReqID = (String) session.getAttribute("insReqID");%>
                        <p class="before">  Insurance Requirement : <%=insReqID%>
                        <p><input type="hidden" name="insReqID" id="insReqID" value="<%=insReqID%>"/></p>
                        <p><input type="hidden" name="request_rtid" id="request_rtid" value="<%=request_rtid%>"/></p>
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insurerUsername" id="insurerUsername" value="<%=insurer.getUsername()%>"/></p>
                            <% String message = "sample message...";%> <%--TODO: make a form of info and convert to string--%>
                        <p><label for="message"><%=message%><input type="hidden" name="message" id="message"/></p>
                        <p><input type="submit" class="btn" value="Request" onclick="<%insurer.setNotification("Y");%>"></p></form>
                            <%}
                                }%>
                </div>
                <div class="aside">
                    <img src="hands.png" id="hands" class="img-responsive" alt="hands">
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
