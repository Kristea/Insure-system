<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Profile Page</title>
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
                    <li><a href="OverviewPage_Insured.jsp">Overview</a></li>
                    <li><a href="AddInsuranceRequirement.jsp">Add Requirement</a></li>              
                    <li><a href="Logout"> Logout</a></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">

                    <h1>Welcome ${insured.title} ${insured.fName} ${insured.sName} </h1>
                    <h3> Insurance Requirements</h3>
                    <%
                        List<InsuranceRequirement> insReq = (List) session.getAttribute("irList");
                        Insured insured = (Insured) session.getAttribute("insured");
                        Iterator it = insReq.iterator();
                        while (it.hasNext()) {
                            InsuranceRequirement ir = (InsuranceRequirement) it.next();
                            if ((ir.isIsSelected() == false) && (ir.isShow() == true)) {
                    %>

                    <form method="post" action="FindMatches" id="findMatches">
                        <table class="table table-striped"> 
                            <tr>
                                <th> 
                                    <input type="hidden" name="insReqID" id="insReqID" value="<%=ir.getInsReq_id()%>"/>
                                    <h4>Requirement ID: <%=ir.getInsReq_id()%></h4>
                            </th>
                            </tr>
                            <tr>
                                <td>
                                    <div class="container">   
                                        <div class="sectionheader">
                                            <h4> Insurance Requirement</h4> 
                                            <div >
                                                <p class="before"> Gadget Type: </p><p class="after"><input  id="type" name="type" type ="hidden"  value="<%=ir.getGadget().getType()%>"/> <%=ir.getGadget().getType()%></p><br/>
                                                <p class="before"> Gadget Make: </p><p class="after"><%=ir.getGadget().getMake()%></p><br/>
                                                <p class="before"> Gadget Model:</p><p class="after">  <%=ir.getGadget().getModel()%></p><br/>
                                                <% String price = String.format("%.02f", ir.getGadget().getPrice());%>
                                                <p class="before"> Gadget Price:</p><p class="after">  £<%=price%></p><br/>
                                                <p class="before"> Type of Cover:</p><p class="after">  <input  id="typeOfCover" name="typeOfCover" type ="hidden" value="<%=ir.getTypeOfCover()%>"/><%=ir.getTypeOfCover()%></p> <br/>
                                                <p class="before"> Cover Period:</p><p class="after"> <input  id="coverPeriod" name="coverPeriod"  type ="hidden" value="<%=ir.getCoverPeriod()%>"/> <%=ir.getCoverPeriod()%> months</p><br/>
                                                <p class="before"> Gadget Age:</p><p class="after"> <%=ir.getGadget_age()%></p><br/>
                                                <p class="before"> Has it been Matched:</p><p class="after"> <%=ir.isIsSelected()%></p><br/>
                                                <% String val = String.format("%.02f", ir.getValue());%>
                                                <p class="before"> Gadget Value:</p><p class="after"> £<input  id="value" name="value" type ="hidden" value="<%=ir.getValue()%>"/><%=val%></p><br/>
                                                <p class="before"> Environment Used:</p><p class="after"> <%=ir.getEnvironment()%></p><br/>
                                                <p class="before">Requirement Public:</p><p class="after"> <%if (ir.isShow() == true) {%>
                                                    Yes <%} else if (ir.isShow() == false) {%></p><br/>
                                                No <%}%> <br/>
                                                <p><input type="submit" class ="btn" value="Get Matches"></p>

                                                <%  }
                                                    }%>
                                            </div>
                                        </div>
                                    </div>   
                                </td>

                            </tr>
                        </table>
                    </form>    

                </div>
                <div class="aside">

                    <p> <img src="default_profile_image.png"  id="profile_pic" alt="profile picture"><p>
                    <p class="before"> Role: </p><p class="after">${insured.role} </p> <br/>
                    <p class="before"> Email: </p><p class="after">${insured.email} </p><br/>
                    <p class="before"> Address Line 1:</p><p class="after"> ${insured.addressLine1} </p><br/>
                    <p class="before"> Street:</p><p class="after">  ${insured.street} </p><br/>
                    <p class="before"> City:</p><p class="after">  ${insured.city} </p><br/>      
                    <p class="before"> Postcode:</p><p class="after">  ${insured.postcode} </p>  <br/>
                    <p class="before"> DOB:</p><p class="after"> ${insured.dayDOB}/${insured.monthDOB}/${insured.yearDOB} </p>  <br/>

                    <h3>Rating</h3>
                    <%--<% String feed = String.format("%.01f", insured.getRating());%>--%>
                    <% int feed = (int) Math.ceil(insured.getRating());%>
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
         <script type="text/javascript" src="stars.js"></script>
    </body>
</html>