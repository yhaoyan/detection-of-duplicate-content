<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    
    <title>学生上传页面</title>
    <script language="javascript">
      function addComponent(){
         var tmpFiles = document.getElementById("files");
       
         var newNode2 = document.createElement("span");
         newNode2.innerHTML = "选择文件：";
         tmpFiles.appendChild(newNode2);
         
         var uploadFile = document.createElement("input");
         uploadFile.type = "file";
         uploadFile.name = "upload";
         tmpFiles.appendChild(uploadFile);
         
         var button = document.createElement("input");
         button.type = "button";
         button.value = "删除";
         button.onclick = function(){
        
            tmpFiles.removeChild(newNode2);
            tmpFiles.removeChild(uploadFile);
            tmpFiles.removeChild(button);
            tmpFiles.removeChild(br2);
            tmpFiles.removeChild(br3);
         }
         tmpFiles.appendChild(button);
         
         var br2 = document.createElement("br");
         tmpFiles.appendChild(br2);
         
         var br3 = document.createElement("br");
         tmpFiles.appendChild(br3);     
      }
    </script>  
  </head>
  
  <body>
     <h3>上传多个文件</h3>
     <input type="button" onclick="addComponent();" value="添加文件"/>
     <br/>
     <form action="uploadMul" method="post" enctype="multipart/form-data" >         
        <span id="files">          
                                         选择文件 ：<input type="file" name="upload"/><br/>           
            <br/><br/>                            
        </span>   
      <input type="submit" value="上传"/>
     </form>
  </body>
</html>
