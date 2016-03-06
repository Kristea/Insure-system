<%-- 
    Document   : test
    Created on : 07-Apr-2015, 09:16:38
    Author     : Claudia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function checkType(value) {
                var t = document.getElementById("type").value;
                if (t == "Smartphone") {
                  document.getElementById('make').style.display = "block";
                  document.getElementById('model').style.display = "block";
                } else if (t == "Tablet") {
                    alert("name2 was selected");
                }

            }
        </script>
    </head>

    <body>
        <p><label for="type"> Type </label>
            <select size="1" name="type" id="type"  onmousedown="this.value = '';" onchange="checkType(this.value);">
                <option value="Smartphone">Smartphone</option>
                <option value="Tablet">Tablet</option>
                <option value="Laptop">Laptop</option>
            </select></p>


        <p><label for="make">Make </label>
            <select size="1" name="make" id="make" style="display:none">
                <option value="Apple iPhone">Apple iPhone</option>
                <option value="HTC">HTC</option>
                <option value="Samsung">Samsung</option>
                <option value="Sony">Sony</option>
            </select>
        </p>
        <p><label for="model">Model </label>
            <select size="1" name="model" id="model" style="display:none">
                <option value="Galaxy S5">Galaxy S5</option>
                <option value="Galaxy S6">Galaxy S6</option>
                <option value="Galaxy Note 6">Galaxy Note 6</option>
                <option value="6">6</option>
                <option value="5s">5s</option>
                <option value="One M8">One M8</option>
                <option value="Desire 620">Desire 620</option>
                <option value="Xperia Z2">Xperia Z2</option>
                <option value="Xperia Z3">Xperia Z3</option>
            </select>
        </p>





    </body>
</html>
