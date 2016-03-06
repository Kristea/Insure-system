
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Overview Page</title>
    </head>
    <%@page import ="java.util.*"%>
    <%@page import ="Model.User"%>
    <%@page import ="Model.Insurer"%>
    <%@page import ="Model.RiskThreshold"%>
    <%@page import ="Model.Gadget"%>
    <%@page import ="Model.Request"%>
    <%@page import ="Model.Agreement"%>
    <%@page import ="Model.InsuranceRequirement"%>
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
                    <li><a href="ProfilePageInsurer.jsp">Home</a></li>
                    <li><a href="Logout"> Logout</a></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">

                    <h2>Pending Requests</h2>
                    <% Insurer insurer = (Insurer) session.getAttribute("insurer");
                        for (Request r : insurer.getRequests()) {
                            if (r.getStatus() == Request.Status.PENDING) {%>

                    <div class="top">
                        <h3>Request By: <%=r.getInsured().getTitle()%> <%=r.getInsured().getfName()%> <%=r.getInsured().getsName()%> </h3>
                    </div>
                    <p class="before">Request ID: </p><p class="after"> <%=r.getAgreement_id()%></p><br/>
                    <p class="before">Date of Request: </p><p class="after"> <%=r.getThisDay()%>/<%=r.getThisMonth()%>/<%=r.getThisMonth()%></p><br/>


                    <h3> Insurance Requirement </h3>
                    <p class="before">Gadget Type: </p><p class="after"> <%=r.getInsReq().getGadget().getType()%></p><br/>
                    <p class="before">Gadget Make: </p><p class="after"><%=r.getInsReq().getGadget().getMake()%></p><br/>
                    <p class="before">Gadget Model: </p><p class="after">  <%=r.getInsReq().getGadget().getModel()%></p><br/>
                    <% String price = String.format("%.02f", r.getInsReq().getGadget().getPrice());%>
                    <p class="before"> Gadget Price: </p><p class="after">  £<%=price%></p><br/>
                    <p class="before">Type of Cover: </p><p class="after">  <%=r.getInsReq().getTypeOfCover()%></p><br/>
                    <p class="before">Cover Period: </p><p class="after"> <%=r.getInsReq().getCoverPeriod()%> months</p><br/>
                    <p class="before">Gadget Age: </p><p class="after"> <%=r.getInsReq().getGadget_age()%></p><br/>
                    <% String val = String.format("%.02f", r.getInsReq().getValue());%>
                    <p class="before"> Gadget Value: </p><p class="after"> £<%=val%></p><br/>
                    <p class="before"> Environment Used: </p><p class="after"> <%=r.getInsReq().getEnvironment()%></p><br/>

                    <h3>Your Risk Threshold</h3>
                    <%=r.isPerfect_match()%>
                    <%if (r.isPerfect_match() != true) {%>
                    <form method="post" action="ConfirmAgreement" id="ConfirmAgreement">
                        <p><input type="hidden" name="insuredEmail" id="insuredEmail" value="<%=r.getInsured().getEmail()%>"/></p>
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="<%=r.getInsured().getUsername()%>"/></p>
                        <p><input type="hidden" name="insReq" id="insReq" value="<%=r.getInsReq().getInsReq_id()%>"/></p>
                        <input type="hidden" name="reqID" id="reqID" value="<%=r.getAgreement_id()%>"/> <br/>
                        <p class="before">Risk Threshold ID: </p><p class="after">  <%=r.getRiskT().getRiskT_id()%> </p><br/>
                        <label for="gadgetType">  Gadget Type: </label>
                        <select size="1" name="gadgetType" id="gadgetType" value="<%=r.getRiskT().getGadgetType()%>">
                            <option value="<%=r.getRiskT().getGadgetType()%>"><%=r.getRiskT().getGadgetType()%></option>
                            <option value="Smartphone">Smartphone</option>
                            <option value="Tablet">Tablet</option>
                            <option value="Laptop">Laptop</option>
                        </select><br/>
                        <% String min = String.format("%.02f", r.getRiskT().getMinPrice());
                                String max = String.format("%.02f", r.getRiskT().getMaxPrice());%>
                        <p class="before"> Minimum Price Insured £<%=min%></p><br/>
                        <p class="before"> Maximum Price Insured £<%=max%></p><br/>
                        <p class="before"> Agreed Value £<input name="agreedVal" id="agreedVal" /></p><br/>

                        <label for="typeOfCover"> Type of Cover Provided: </label>
                        <select size="1" name="typeOfCover" id="typeOfCover" value="<%=r.getRiskT().getTypeOfCover()%>">
                            <option value="<%=r.getRiskT().getTypeOfCover()%>"><%=r.getRiskT().getTypeOfCover()%></option>
                            <option value="Accidental Damage">Accidental Damage</option>
                            <option value="Theft">Theft</option>
                            <option value="Breakdown">Breakdown</option>
                            <option value="Loss">Loss</option>
                        </select><br/>
                        <label for="coverPeriod"> Maximum Cover Period Covered: </label>
                        <select size="1" name="coverPeriod" id="coverPeriod" value="<%=r.getRiskT().getCoverPeriod()%>">
                            <option value ="<%=r.getRiskT().getCoverPeriod()%>"> <%=r.getRiskT().getCoverPeriod()%> months</option>
                            <option value="6">6 months</option>
                            <option value="12">12 months</option>
                            <option value="18">18 months</option>
                            <option value="24">24 months</option></select><br/>
                        <p class="before"> Conditions Accepted: </p><p class="after">
                            <% for (String con : r.getRiskT().getGadgetConditions()) {%>
                            <%=con%>   </p><br/> <%}%>

                        <label for="condition"> Add Conditions </label>
                        <select size="1" name="condition" id="condition" value = "">
                            <option value="New">New</option>
                            <option value="Refurbished">Refurbished</option>
                            <option value="Used - Like New">Used - Like New</option>
                            <option value="Used - Good">Used - Good</option>
                            <option value="Used - Fair">Used - Fair</option>
                        </select>

                        <h4>Only Change Your Risk Threshold to Accommodate Requirements Upon Negotiations with <%=r.getInsured().getTitle()%> <%=r.getInsured().getfName()%> <%=r.getInsured().getsName()%> </h4><br/>
                        <p><input type="submit" class="btn" value="Confirm Agreement"></p></form>

                    <%  } else if (r.isPerfect_match() == true) {%>
                    <p class="before"> Gadget Type: </p><p class="after"> <%=r.getRiskT().getGadgetType()%></p><br/>
                    <% String min = String.format("%.02f", r.getRiskT().getMinPrice());
                            String max = String.format("%.02f", r.getRiskT().getMaxPrice());%>
                    <p class="before"> Minimum Price Insured £<%=min%></p><br/>
                    <p class="before"> Maximum Price Insured £<%=max%></p><br/>
                    <p class="before">Type Of Cover: </p><p class="after"> <%=r.getRiskT().getTypeOfCover()%></p><br/>
                    <p class="before"> Cover Period: </p><p class="after"> <%=r.getRiskT().getCoverPeriod()%>  months </p><br/>

                    <form method="post" action="ConfirmAgreement" id="ConfirmAgreement">
                        <p><input type="hidden" name="insuredEmail" id="insuredEmail" value="<%=r.getInsured().getEmail()%>"/></p>
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="<%=r.getInsured().getUsername()%>"/></p>
                        <p><input type="hidden" name="insReq" id="insReq" value="<%=r.getInsReq().getInsReq_id()%>"/></p>
                        <input type="hidden" name="reqID" id="reqID" value="<%=r.getAgreement_id()%>"/>
                        <p><input type="hidden" name="gadgetType" id="gadgetType" value="<%=r.getInsReq().getGadget().getType()%>"/></p>
                        <p><input type="hidden" name="typeOfCover" id="typeOfCover" value="<%=r.getInsReq().getTypeOfCover()%>"/></p>
                        <p><input type="hidden" name="coverPeriod" id="coverPeriod" value="<%=r.getInsReq().getCoverPeriod()%>"/></p>
                        <p><input type="hidden" name="condition" id="condition" value="<%=r.getInsReq().getCondition()%>"/></p>
                        <p><input type="hidden" name="agreedVal" id="agreedVal" value="<%=r.getInsReq().getValue()%>"/></p>
                        <p><input type="submit" class="btn" value="Confirm Agreement"></p></form>
                            <%  }%>
                    <h3>Decline</h3>
                    <form method="post" action="DeclineRequest" id="DeclineRequest">
                        <p><input type="hidden" name="insurerEmail" id="insurerEmail" value="<%=insurer.getEmail()%>"/></p>
                        <p><input type="hidden" name="insuredEmail" id="insuredEmail" value="<%=r.getInsured().getEmail()%>"/></p>
                        <p><input type="hidden" name="insuredUsername" id="insuredUsername" value="<%=r.getInsured().getUsername()%>"/></p>
                        <p><input type="hidden" name="reqID" id="reqID" value="<%=r.getAgreement_id()%>"/></p>
                        <p class="before">Reasons for Declining Request: </p><p class="after"> <input type="text" name="comment" id="comment"/></p>
                        <p><input type="submit" class="btn" value="Decline Request"></p></form>
                            <%}
                                }%>


                    <h2>Agreements Made</h2>
                    <% for (Agreement a : insurer.getAgreements()) {%>
                   
                    <h2>Agreed Insurances </h2>
                    <% if (a.getStatus() == Agreement.Status.AGREED) {
                    %>
                   
                    <h3>Agreement ID: <%=a.getAgreement_id()%> </h3>
                    <p class="before"> Agreement With: </p><p class="after"> <%=a.getInsured().getTitle()%> <%=a.getInsured().getfName()%> <%=a.getInsured().getsName()%> </p><br/>
                    <p class="before">Date of Agreement:</p><p class="after">  <%=a.getThisDay()%>/<%=a.getThisMonth()%>/<%=a.getThisYear()%></p><br/>
                    
                    <h3> Requested Insurance </h3>
                    <%for (InsuranceRequirement ins : a.getInsured().getInsReq()) {
                            if (ins.getInsReq_id().contains(a.getInsReqID())) {%>
                    <p class="before"> Gadget Type: </p><p class="after"> <%=ins.getGadget().getType()%></p><br/>
                    <p class="before"> Gadget Make: </p><p class="after"><%=ins.getGadget().getMake()%></p><br/>
                    <p class="before"> Gadget Model: </p><p class="after">  <%=ins.getGadget().getModel()%></p><br/>
                    <% String price = String.format("%.02f", ins.getGadget().getPrice());%>
                    <p class="before"> Gadget Price: </p><p class="after">  £<%=price%></p><br/>
                    <p class="before"> Type of Cover: </p><p class="after">  <%=ins.getTypeOfCover()%></p><br/>
                    <p class="before"> Cover Period: </p><p class="after"> <%=ins.getCoverPeriod()%></p><br/>
                    <p class="before"> Gadget Age: </p><p class="after"> <%=ins.getGadget_age()%></p><br/>
                    <p class="before"> Gadget Condition: </p><p class="after"> <%=ins.getCondition()%></p><br/>
                    <% String val = String.format("%.02f", ins.getValue());%>
                    <p class="before">Gadget Value: </p><p class="after"> £<%=val%></p><br/>
                    <p class="before"> Environment Used: </p><p class="after"> <%=ins.getEnvironment()%></p><br/>
                    <%}
                            }%>

                    <h3>Agreed Terms</h3>
                    <p class="before"> Gadget Type: </p><p class="after">  <%=a.getGadgetType()%> </p><br/>
                    <% String valInsured = String.format("%.02f", a.getAgreedVal());%>
                    <p class="before">Value Insured: </p><p class="after"> £<%=valInsured%></p><br/>
                    <p class="before">Type of Cover Provided: </p><p class="after">  <%=a.getTypeOfCover()%> </p><br/>
                    <p class="before">Maximum Cover Period : </p><p class="after">  <%=a.getCoverPeriod()%> months </p><br/>
                    <p class="before"> Condition Accepted: </p><p class="after"> <%=a.getGadgetCondition()%></p><br/>
                    <% if (a.isInsuredRated() == false) {%>

                    <form method="post" action="RatingInsured" id="RatingInsured">
                        <p><input type="hidden" name="reqID" id="reqID" value="<%=a.getAgreement_id()%>"/></p>
                        <label for="insuredRating"> Rate <%=a.getInsured().getTitle()%> <%=a.getInsured().getfName()%> <%=a.getInsured().getsName()%> </label>
                        <select size="1" name="insuredRating" id="insuredRating" value = "">
                            <option value="1">*</option>
                            <option value="2">**</option>
                            <option value="3">***</option>
                            <option value="4">****</option>
                            <option value="5">*****</option>
                        </select>
                        <p><input type="submit" class="btn" value="Rate"></p></form>

                    <%   } else if (a.isInsuredRated() == true) {%>
                    <h3> You Completed The Initial Feedback  </h3>
                    <%  }
                    } else {%>
                    <h3>No Agreed Insurances </h3>         
                    <%
                            }
                        }
                    %>

                    <h2> Cancelled Insurances </h2>
                    <% for (Agreement a : insurer.getAgreements()) {%>

                    <h2>Agreed Insurances </h2>
                    <% if (a.getStatus() == Agreement.Status.CANCELLED) {
                    %>
                    <h3>Agreement ID: <%=a.getAgreement_id()%> </h3>
                    <p class="before">Agreement With: </p><p class="after"> <%=a.getInsured().getTitle()%> <%=a.getInsured().getfName()%> <%=a.getInsured().getsName()%> </p><br/>
                    <p class="before">Date of Agreement:</p><p class="after"> <%=a.getThisDay()%>/<%=a.getThisMonth()%>/<%=a.getThisYear()%></p><br/>

                    <h3> Requested Insurance </h3>
                    <%for (InsuranceRequirement ins : a.getInsured().getInsReq()) {
                            if (ins.getInsReq_id().contains(a.getInsReqID())) {%>
                    <p class="before"> Gadget Type:</p><p class="after"> <%=ins.getGadget().getType()%></p><br/>
                    <p class="before"> Gadget Make: </p><p class="after"><%=ins.getGadget().getMake()%></p><br/>
                    <p class="before"> Gadget Model: </p><p class="after">  <%=ins.getGadget().getModel()%></p><br/>
                    <% String price = String.format("%.02f", ins.getGadget().getPrice());%>
                    <p class="before"> Gadget Price: </p><p class="after">  £<%=price%></p><br/>
                    <p class="before"> Type of Cover: </p><p class="after">  <%=ins.getTypeOfCover()%></p><br/>
                    <p class="before">Cover Period: </p><p class="after"> <%=ins.getCoverPeriod()%></p><br/>
                    <p class="before"> Gadget Age: </p><p class="after"> <%=ins.getGadget_age()%></p><br/>
                    <p class="before">Gadget Condition:: </p><p class="after"> <%=ins.getCondition()%></p><br/>
                    <% String val = String.format("%.02f", ins.getValue());%>
                    <p class="before">Gadget Value: </p><p class="after"> £<%=val%></p><br/>
                    <p class="before"> Environment Used: </p><p class="after"> <%=ins.getEnvironment()%></p><br/>
                    <%}
                            }%>

                    <h3>Agreed Terms</h3>
                    <p class="before"> Gadget Type: </p><p class="after">  <%=a.getGadgetType()%> </p><br/>
                    <% String valInsured = String.format("%.02f", a.getAgreedVal());%>
                    <p class="before">Value Insured:</p><p class="after"> £<%=valInsured%></p><br/>
                    <p class="before"> Type of Cover Provided: </p><p class="after">  <%=a.getTypeOfCover()%> </p><br/>
                    <p class="before">Maximum Cover Period Covered: </p><p class="after">  <%=a.getCoverPeriod()%> months </p><br/>
                    <p class="before"> Condition Accepted: </p><p class="after"> <%=a.getGadgetCondition()%></p><br/>
                    <%
                        } else {%>
                    <h3>No Cancelled Insurances </h3>         
                    <%
                            }
                        }
                    %>

                    <h2>Requests You Have Declined</h2>         
                    <%  for (Request r : insurer.getRequests()) {
                            if (r.getStatus() == Request.Status.CANCELLED) {%>
                   
                   <h3>Request By: <%=r.getInsured().getTitle()%> <%=r.getInsured().getfName()%> <%=r.getInsured().getsName()%> </h3>
                    <p class="before">Request ID: </p><p class="after"> <%=r.getAgreement_id()%></p><br/>
                    <p class="before"> Date of Request: </p><p class="after"> <%=r.getThisDay()%>/<%=r.getThisMonth()%>/<%=r.getThisMonth()%></p><br/>

                    <h3> Insurance Requirement Requested </h3>
                    <p class="before">Gadget Type: </p><p class="after"> <%=r.getInsReq().getGadget().getType()%></p><br/>
                    <p class="before">Gadget Make: </p><p class="after"><%=r.getInsReq().getGadget().getMake()%></p><br/>
                    <p class="before">Gadget Model: </p><p class="after">  <%=r.getInsReq().getGadget().getModel()%></p><br/>
                    <% String price = String.format("%.02f", r.getInsReq().getGadget().getPrice());%>
                    <p class="before"> Gadget Price: </p><p class="after">  £<%=price%></p><br/>
                    <p class="before"> Type of Cover: </p><p class="after">  <%=r.getInsReq().getTypeOfCover()%></p><br/>
                    <p class="before"> Cover Period: </p><p class="after"> <%=r.getInsReq().getCoverPeriod()%></p><br/>
                    <p class="before">Gadget Age: </p><p class="after"> <%=r.getInsReq().getGadget_age()%></p><br/>
                    <% String val = String.format("%.02f", r.getInsReq().getValue());%>
                    <p class="before">Gadget Value: </p><p class="after"> £<%=val%></p><br/>
                    <p class="before">Environment Used: </p><p class="after"> <%=r.getInsReq().getEnvironment()%></p><br/>
                    <%if (r.isInsuredRated() == false) {%>
                    <form method="post" action="RatingInsured" id="RatingInsured">
                        <p><input type="hidden" name="reqID" id="reqID" value="<%=r.getAgreement_id()%>"/></p>
                        <label for="insuredRating"> Rate <%=r.getInsured().getTitle()%> <%=r.getInsured().getfName()%> <%=r.getInsured().getsName()%> </label>
                        <select size="1" name="insuredRating" id="insuredRating" value = "">
                            <option value="1">*</option>
                            <option value="2">**</option>
                            <option value="3">***</option>
                            <option value="4">****</option>
                            <option value="5">*****</option>
                        </select>
                        <p><input type="submit" class="btn" value="Rate"></p></form>

                    <%   } else if (r.isInsuredRated() == true) {%>
                    <h3> You Completed The Initial Feedback  </h3>
                    <%  }
                            }
                        }
                    %>
                </div>
                <div class="aside">

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
        </div>  <!-- The JavaScript -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="sections.js"></script>

    </body>
</html>
