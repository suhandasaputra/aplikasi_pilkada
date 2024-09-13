<%-- 
    Document   : tes
    Created on : 03-Dec-2020, 15:13:02
    Author     : matajari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jquery-3.4.1.min.js"></script>



    <!DOCTYPE html> 
<html> 
  
<head> 
    <title>Refresh Image</title> 
</head> 
  
<body> 
    <!-- Display the image --> 
    <img id="gfgimage" src="http://103.41.204.105/pilkada/candidate/benyamin-pilar13.png" 
        height="500" width="700" /> 
  
    <script> 
        // Create a timestamp 
        var timestamp = new Date().getTime(); 
  
        // Get the image element  
        var image = document.getElementById("gfgimage"); 
  
        // Adding the timestamp parameter to image src 
        image.src = "http://103.41.204.105/pilkada/candidate/benyamin-pilar13.png?t=" + timestamp; 
        console.log(image.src); 
    </script> 
</body> 
  
</html> 


