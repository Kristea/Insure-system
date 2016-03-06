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
    <%@page import ="Model.Insured"%>
    <%@page import ="Model.InsuranceRequirement"%>
    <%@page import ="Model.Request"%>
    <%@page import ="Model.Agreement"%>
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
                                <input type="submit"  value="Search" class="btn"/>
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
                    <h2>Pending Requests</h2>

                    <% Insured insured = (Insured) session.getAttribute("insured");
                        for (Request r : insured.getRequests()) {
                            if (r.getStatus() == Request.Status.PENDING) {%>
                    <div class="top">
                        <p class="before"> Request ID:</p> <p class="after"> <%=r.getAgreement_id()%></p><br/>
                        <p class="before">Date of Request:</p> <p class="after"> <%=r.getThisDay()%>/<%=r.getThisMonth()%>/<%=r.getThisYear()%><br/>
                    </div>

                    <h3> Your Insurance Requirement </h3>
                    <p class="before"> Gadget Type:</p> <p class="after"> <%=r.getInsReq().getGadget().getType()%></p><br/>
                    <p class="before"> Gadget Make:</p> <p class="after"><%=r.getInsReq().getGadget().getMake()%></p><br/>
                    <p class="before"> Gadget Model:</p> <p class="after">  <%=r.getInsReq().getGadget().getModel()%></p><br/>
                    <p class="before">Gadget Price:</p> <p class="after">  £<%=r.getInsReq().getGadget().getPrice()%></p><br/>
                    <p class="before">Type of Cover: </p> <p class="after"> <%=r.getInsReq().getTypeOfCover()%></p><br/>
                    <p class="before">Cover Period:</p> <p class="after"> <%=r.getInsReq().getCoverPeriod()%></p><br/>
                    <p class="before">Gadget Age:</p> <p class="after"> <%=r.getInsReq().getGadget_age()%></p><br/>
                    <% String val = String.format("%.02f", r.getInsReq().getValue());%>
                    <p class="before">Gadget Value:</p> <p class="after"> £<%=val%></p><br/>
                    <p class="before">Environment Used:</p> <p class="after"> <%=r.getInsReq().getEnvironment()%></p><br/>
                    <p class="before">Insurer:</p> <p class="after"> <%=r.getInsurer().getTitle()%> <%=r.getInsurer().getfName()%> <%=r.getInsurer().getsName()%></p> <br/>

                    <h3>Insurer's Risk Threshold</h3>
                    <p class="before">Risk Threshold ID:</p> <p class="after">  <%=r.getRiskT().getRiskT_id()%> </p><br/>
                    <p class="before"> Gadget Type:</p> <p class="after">  <%=r.getRiskT().getGadgetType()%> </p><br/>
                    <p class="before"> Minimum Price Insured:</p> <p class="after"> £<%=r.getRiskT().getMinPrice()%></p><br/>
                    <p class="before">Maximum Price Insured:</p> <p class="after"> £<%=r.getRiskT().getMaxPrice()%></p><br/>
                    <p class="before">Type of Cover Provided:</p> <p class="after">  <%=r.getRiskT().getTypeOfCover()%> </p><br/>
                    <p class="before">Maximum Cover Period:</p> <p class="after">  <%=r.getRiskT().getCoverPeriod()%> months </p> <br/>
                    <p class="before">Conditions Accepted:</p> <p class="after">
                        <% for (String con : r.getRiskT().getGadgetConditions()) {
                        %>
                        <%=con%>
                    </p><br/>
                    <%
                                }
                            }
                        }
                    %>

                    <h2>Agreements Made</h2>
                    <% for (Agreement a : insured.getAgreements()) {%>
                    <h2>Agreed Insurances </h2>
                    <% if (a.getStatus() == Agreement.Status.AGREED) {
                    %>

                    <h3>Agreement ID: <%=a.getAgreement_id()%> </h3>
                    <p class="before"> Agreement With:</p> <p class="after"> <%=a.getInsured().getTitle()%> <%=a.getInsured().getfName()%> <%=a.getInsured().getsName()%> </p><br/>
                    <p class="before">Date of Agreement:</p> <p class="after"> <%=a.getThisDay()%>/<%=a.getThisMonth()%>/<%=a.getThisYear()%></p><br/>
                    <h3> Requested Insurance </h3>
                    <%for (InsuranceRequirement ins : a.getInsured().getInsReq()) {
                            if (ins.getInsReq_id().contains(a.getInsReqID())) {%>
                    <p class="before"> Gadget Type:</p> <p class="after"> <%=ins.getGadget().getType()%></p><br/>
                    <p class="before"> Gadget Make:</p> <p class="after"><%=ins.getGadget().getMake()%></p><br/>
                    <p class="before"> Gadget Model:</p> <p class="after">  <%=ins.getGadget().getModel()%></p><br/>
                    <p class="before">Gadget Price: </p> <p class="after"> £<%=ins.getGadget().getPrice()%></p><br/>
                    <p class="before">Type of Cover:</p> <p class="after">  <%=ins.getTypeOfCover()%></p><br/>
                    <p class="before">Cover </p> <p class="after"> <%=ins.getCoverPeriod()%></p><br/>
                    <p class="before">Gadget Age:</p> <p class="after"> <%=ins.getGadget_age()%></p><br/>
                    <p class="before">Gadget Condition :</p> <p class="after"><%=ins.getCondition()%></p><br/>
                    <% String val = String.format("%.02f", ins.getValue());%>
                    <p class="before">  Gadget Value:</p> <p class="after"> £<%=val%></p><br/>
                    <p class="before"> Environment Used:</p> <p class="after"> <%=ins.getEnvironment()%></p><br/>
                    <%}
                        }%>
                    <h3>Agreed Terms</h3>
                    <p class="before"> Gadget Type: </p> <p class="after"> <%=a.getGadgetType()%> </p><br/>
                    <% String agreedVal = String.format("%.02f", a.getAgreedVal());%>
                    <p class="before"> Value Insured:</p> <p class="after"> £<%=agreedVal%></p><br/>
                    <p class="before"> Type of Cover Provided:</p> <p class="after">  <%=a.getTypeOfCover()%> </p><br/>
                    <p class="before"> Maximum Cover Period : </p> <p class="after"> <%=a.getCoverPeriod()%> months </p><br/>
                    <p class="before"> Condition Accepted:</p> <p class="after"> <%=a.getGadgetCondition()%></p><br/>
                    <% if (a.isInsurerRated() == false) {%>
                    <form method="post" action="RatingInsurer" id="RatingInsurer">
                        <p><input type="hidden" name="reqID" id="reqID" value="<%=a.getAgreement_id()%>"/></p>
                        <label for="insurerRating"> Rate <%=a.getInsurer().getTitle()%> <%=a.getInsurer().getfName()%> <%=a.getInsurer().getsName()%> </label>
                        <select size="1" name="insurerRating" id="insurerRating" value = "">
                            <option value="1">*</option>
                            <option value="2">**</option>
                            <option value="3">***</option>
                            <option value="4">****</option>
                            <option value="5">*****</option>
                        </select>
                        <p><input type="submit" class="btn" value="Rate"></p></form>

                    <form method="post" action="CancelAgreement" id="CancelAgreement">
                        <p><input type="hidden" name="reqID" id="reqID" value="<%=a.getAgreement_id()%>"/></p>
                        <p><input type="submit" class="btn" value="Cancel Agreement"></p></form>


                    <%   } else if (a.isInsurerRated() == true) {%>
                    <p> You Completed The Initial Feedback  </p>
                    <%  } %>

                    <%
                    } else {%>
                    <h3>No Agreed Insurances </h3>         

                    <%
                            }
                        }
                    %>

                    <% for (Agreement a : insured.getAgreements()) {%>
                    <h2> Cancelled Insurances </h2>
                    <% if (a.getStatus() == Agreement.Status.CANCELLED) {
                    %>
                    <h3>Agreement ID: <%=a.getAgreement_id()%> </h3>
                    <div class="top">
                        <p class="before"> Agreement With:</p> <p class="after"> <%=a.getInsured().getTitle()%> <%=a.getInsured().getfName()%> <%=a.getInsured().getsName()%> </p><br/>
                        <p class="before"> Date of Agreement:</p> <p class="after"> <%=a.getThisDay()%>/<%=a.getThisMonth()%>/<%=a.getThisYear()%></p><br/>
                    </div>
                    <h3> Requested Insurance </h3>
                    <%for (InsuranceRequirement ins : a.getInsured().getInsReq()) {
                            if (ins.getInsReq_id().contains(a.getInsReqID())) {%>
                    <p class="before"> Gadget Type:</p> <p class="after"> <%=ins.getGadget().getType()%></p><br/>
                    <p class="before"> Gadget Make:</p> <p class="after"><%=ins.getGadget().getMake()%></p><br/>
                    <p class="before">Gadget Model: </p> <p class="after"> <%=ins.getGadget().getModel()%></p><br/>
                    <p class="before"> Gadget Price: </p> <p class="after"> £<%=ins.getGadget().getPrice()%></p><br/>
                    <p class="before"> Type of Cover: </p> <p class="after"> <%=ins.getTypeOfCover()%></p><br/>
                    <p class="before"> Cover Period:</p> <p class="after"> <%=ins.getCoverPeriod()%></p><br/>
                    <p class="before"> Gadget Age:</p> <p class="after"> <%=ins.getGadget_age()%></p><br/>
                    <p class="before"> Gadget Condition:</p> <p class="after"> <%=ins.getCondition()%></p><br/>
                    <% String val = String.format("%.02f", ins.getValue());%>
                    <p class="before">Gadget Value:</p> <p class="after"> £<%=val%></p><br/>
                    <p class="before">Environment Used:</p> <p class="after"> <%=ins.getEnvironment()%></p><br/>
                    <%}
                        }%>
                    <h3>Agreed Terms</h3>
                    <p class="before">   Gadget Type:</p> <p class="after">  <%=a.getGadgetType()%> </p><br/>
                    <p class="before">  Value Insured: </p> <p class="after">£<%=a.getAgreedVal()%></p><br/>
                    <p class="before"> Type of Cover Provided:</p> <p class="after">  <%=a.getTypeOfCover()%> </p><br/>
                    <p class="before"> Maximum Cover Period Covered: </p> <p class="after"> <%=a.getCoverPeriod()%> months </p><br/>
                    <p class="before"> Condition Accepted:</p> <p class="after"> <%=a.getGadgetCondition()%></p><br/>
                    <%
                    } else {%>
                    <p>No Cancelled Insurance Insurance Agreements </p>         
                    <%
                            }
                        }
                    %>

                    <h2>Declined Requests</h2>         
                    <%  for (Request r : insured.getRequests()) {
                            if (r.getStatus() == Request.Status.CANCELLED) {%>

                    <div class="top">
                        <p class="before">Request ID: </p> <p class="after"><%=r.getAgreement_id()%></p><br/>
                        <p class="before"> Date of Request:</p> <p class="after"> <%=r.getThisDay()%>/<%=r.getThisMonth()%>/<%=r.getThisMonth()%></p><br/>
                        <p class="before">Reasons For Declining This Request:</p> <p class="after"> <%=r.getComment()%></p></br>
                    </div>
                    <h3> Insurance Requirement </h3>
                    <p class="before">  Gadget Type: </p> <p class="after"><%=r.getInsReq().getGadget().getType()%></p><br/>
                    <p class="before">  Gadget Make:</p> <p class="after"><%=r.getInsReq().getGadget().getMake()%></p><br/>
                    <p class="before"> Gadget Model:</p> <p class="after">  <%=r.getInsReq().getGadget().getModel()%></p><br/>
                    <p class="before"> Gadget Price:</p> <p class="after">  £<%=r.getInsReq().getGadget().getPrice()%></p><br/>
                    <p class="before"> Type of Cover:</p> <p class="after">  <%=r.getInsReq().getTypeOfCover()%></p><br/>
                    <p class="before"> Cover Period:</p> <p class="after"> <%=r.getInsReq().getCoverPeriod()%></p><br/>
                    <p class="before"> Gadget Age:</p> <p class="after"> <%=r.getInsReq().getGadget_age()%></p><br/>
                    <% String val = String.format("%.02f", r.getInsReq().getValue());%>
                    <p class="before"> Gadget Value:</p> <p class="after"> £<%=val%></p><br/>
                    <p class="before"> Environment Used:</p> <p class="after"> <%=r.getInsReq().getEnvironment()%></p><br/>
                    <p class="before"> Insurer:</p> <p class="after"> <%=r.getInsurer().getTitle()%> <%=r.getInsurer().getfName()%> <%=r.getInsurer().getsName()%> 

                    <h3> Insurer's Risk Threshold</h3>
                    <p class="before"> Risk Threshold ID:</p> <p class="after">  <%=r.getRiskT().getRiskT_id()%> </p><br/>
                    <p class="before"> Gadget Type:</p> <p class="after">  <%=r.getRiskT().getGadgetType()%> </p><br/>
                    <p class="before"> Minimum Price Insured:</p> <p class="after"> £<%=r.getRiskT().getMinPrice()%></p><br/>
                    <p class="before"> Maximum Price Insured:</p> <p class="after"> £<%=r.getRiskT().getMaxPrice()%></p><br/>
                    <p class="before">Type of Cover Provided:</p> <p class="after">  <%=r.getRiskT().getTypeOfCover()%> </p><br/>
                    <p class="before">Maximum Cover Period Covered:</p> <p class="after">  <%=r.getRiskT().getCoverPeriod()%> months </p><br/>
                    <p class="before">Conditions Accepted:</p> <p class="after">
                        <% for (String con : r.getRiskT().getGadgetConditions()) {
                        %>
                        <%=con%></p>
                    <br/>
                    <%
                        }
                    %>
                    <% if (r.isInsurerRated() == false) {%>
                    <form method="post" action="RatingInsurer" id="RatingInsurer">
                        <div class="rating">
                            <%-- <p><input type="hidden" name="reqID" id="reqID" value="<%=r.getAgreement_id()%>"/></p>
                             <label for="insurerRating"> Rate <%=r.getInsurer().getTitle()%> <%=r.getInsurer().getfName()%> <%=r.getInsurer().getsName()%> </label>
                             <select size="1" name="insurerRating" id="insurerRating" value = "">
                                 <option value="1">*</option>
                                 <option value="2">**</option>
                                 <option value="3">***</option>
                                 <option value="4">****</option>
                                 <option value="5">*****</option>
                             </select>--%>


                            <fieldset class="rating">
                                <legend>Rate:</legend>
                                <input type="radio" id="star5" name="rating" value="5" /><label for="star5" >5 stars</label>
                                <input type="radio" id="star4" name="rating" value="4" /><label for="star4" >4 stars</label>
                                <input type="radio" id="star3" name="rating" value="3" /><label for="star3" >3 stars</label>
                                <input type="radio" id="star2" name="rating" value="2" /><label for="star2" >2 stars</label>
                                <input type="radio" id="star1" name="rating" value="1" /><label for="star1" >1 star</label>
                            </fieldset>


                        </div>
                        <p><input type="submit" class="btn" value="Rate"></p>

                    </form>

                    <%   } else if (r.isInsurerRated() == true) {%>
                    <p> You Completed The Initial Feedback  </p>
                    <%  } %>
                    <%}
                        }
                    %>
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
        <!-- The JavaScript -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="sections.js"></script>
        <script type="text/javascript" src="rating.js"></script>
    </body>
</html>
