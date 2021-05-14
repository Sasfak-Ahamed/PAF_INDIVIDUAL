/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ResearcherAPI",
 type : type,
 data : $("#formResearcher").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onResearcherSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidIDSave").val($(this).data("id"));
 $("#ProId").val($(this).closest("tr").find('td:eq(0)').text());
 $("#DeveloperName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#ProDesc").val($(this).closest("tr").find('td:eq(2)').text());

});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "ResearcherAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onResearcherDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateResearcherForm()
{
// ID
if ($("#ProId").val().trim() == "")
 {
 return "Insert Project ID.";
 }
// NAME
if ($("#DeveloperName").val().trim() == "")
 {
 return "Insert Developer Name.";
 } 


// DESCRIPTION------------------------
if ($("#ProDesc").val().trim() == "")
 {
 return "Insert project Description.";
 }
return true;
}

function onResearcherSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidIDSave").val("");
 $("#formResearcher")[0].reset();
}

function onResearcherDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 