<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="${pageContext.request.contextPath }/admin/category/insert" method="post" enctype="multipart/form-data" >
	<label for="categoryname">Category Name:</label><br> 
	<input type="text" id="categoryname" name="categoryname"><br> 
	<label for="images">Images:</label><br> 
	<img id="imagess" src="" width="150px" height="150px"  />
	<input type="file" onchange="chooseFile(this)" id="images" name="images"><br>
	<label for="status">Status:</label><br> 
	<input type="text" id="status" name="status"><br>  
	<br> <input type="submit" value="Submit">
	
</form>
