<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOTEL JSP Page</title>
    </head>
    <body>
        <h2>Hotel database application</h2>
<%@ page import="
	java.util.List,
    ppss.businessobjects.Hotel,
    ppss.model.HotelModel"
%>
<%
	HotelModel model = new HotelModel();
	String[] cityList = model.findAvailableCities();

	String selectedCity = request.getParameter("city");
	List<Hotel> hotelList = model.findHotelsByCity(selectedCity);
%>

   <h3>Choose a destination</h3>
   <form action="index.jsp" method="get">
	  Please choose a city:
      <SELECT name="city">
         <OPTION value="">---Any city---</OPTION>
      	<%
      	for(String cityName : cityList){
      	%>
      	 <OPTION value="<%=cityName%>"><%=cityName%></OPTION>
      	<%
      	}
      	%>
      </SELECT>
      <BUTTON type="submit">GO</BUTTON>
   </form>
   <% if (hotelList.size() > 0) { %>
	   <h3>Available hotels in <%=selectedCity%> </h3>
	   <table border="1">
	        <tr>
	          <th>Name</th>
	          <th>Address</th>
	          <th>City</th>
	          <th>Stars</th>
	      	</tr>
	      <%
	      for(Hotel hotel : hotelList){
	      %>
	        <tr>
	          <td><%=hotel.getName()%></td>
	          <td><%=hotel.getAddress()%></td>
	          <td><%=hotel.getCity()%></td>
	          <td><%=hotel.getStars()%> stars</td>
	      	</tr>
	      <%
	      }
	      %>
	    </table>
	 <%}%>

    </body>
</html>
