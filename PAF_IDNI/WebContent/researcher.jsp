<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Researcher"%>
<%
//Save---------------------------------
if (request.getParameter("ProId") != null)
{
Researcher resObj = new Researcher();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidIDSave") == "")
{
stsMsg = resObj.insertResearcher(request.getParameter("ProId"),
request.getParameter("DeveloperName"),
request.getParameter("ProDesc"));
}
else//Update----------------------
{
stsMsg = resObj.updateResearcher(request.getParameter("hidIDSave"),
request.getParameter("ProId"),
request.getParameter("DeveloperName"),
request.getParameter("ProDesc"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidIDDelete") != null)
{
Researcher resObj = new Researcher();
String stsMsg =
resObj.deleteResearcher(request.getParameter("hidIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/researcher.js"></script>
<title>Researcher Management</title>
</head>
<body>
<h1>Researcher Management</h1>

<form id="formResearcher" name="formResearcher" method="post" action="items.jsp">
 Researcher ID:
<input id="ProId" name="ProId" type="text"
 class="form-control form-control-sm">
<br> Developer name:
<input id="DeveloperName" name="DeveloperName" type="text"
 class="form-control form-control-sm">

<br> Project description:
<input id="ProDesc" name="ProDesc" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divItemsGrid">
<%

Researcher itemObj = new Researcher();
 out.print(itemObj.readResearchers());
%>
</div>
</body>
</html>