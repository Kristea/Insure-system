<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="testnew.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="shortcut icon" href="favicon-16x16.png"/>
        <title>Search All-Insured</title>
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
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="LoginPage.jsp">Login</a></li>
                    <li><a href="Register.jsp">Register</a></li>
                </ul>
            </div>
        </div>

        <div class="content">
            <div class="container">
                <div class ="main">
                    <h1>Search Result - All Insured</h1>
                    
                    <%  List<Insured> insureds = (List) session.getAttribute("insureds");
                        if (insureds.isEmpty() != true) {
                            for (Insured ins : insureds) {%>

                    <table class="table table-striped">
                        <tr>
                            <th> 
                                <a href="ViewProfiles?username=<%=ins.getUsername()%>"> 
                                    <h3><%=ins.getTitle()%> <%=ins.getfName()%> <%=ins.getsName()%> </h3></a>
                            </th>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="container">  
                                    <div class="sectionheader">
                                        <h4>User Details </h4> 
                                        <div class="section ">
                                            <p class="before"> Email: </p><p><%=ins.getEmail()%> </p><br/>
                                            <p class="before"> Address Line 1:</p><p> <%=ins.getAddressLine1()%> </p><br/>
                                            <p class="before"> Street:</p><p> <%=ins.getStreet()%> </p><br/>
                                            <p class="before"> City: </p><p><%=ins.getCity()%> </p>      <br/>
                                            <p class="before"> Postcode: </p><p> <%=ins.getPostcode()%> </p><br/>  
                                        </div>
                                    </div>
                                </div>

                                <div class="container">   
                                    <div class="sectionheader">
                                        <h4> Insurance Requirement </h4> 
                                        <div class="section ">
                                            <% for (InsuranceRequirement ir : ins.getInsReq()) {
                                                    if (ir.isShow() == true) {%>
                                           <p class="before">  Gadget Type:</p><p><%=ir.getGadget().getType()%></p><br/>
                                           <p class="before">  Gadget Make:</p><p> <%=ir.getGadget().getMake()%></p><br/>
                                            <p class="before"> Gadget Model:</p><p>  <%=ir.getGadget().getModel()%></p><br/>
                                             <% String val = String.format("%.02f", ir.getValue());%>
                                            <p class="before"> Gadget Value:</p><p> £<%=val%></p><br/>
                                            <% }%>
                                           
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </table>
                    <% }
                        }
                    } else {%>
                    <h3>No Results Found...</h3>
                    <%}%>
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
    </body>
</html>