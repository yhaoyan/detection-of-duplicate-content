<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业查重系统</title>
</head>
<body>
  <h3>用户登陆</h3>
  <hr/>
  <!-- s:filderror标签用于提示报错信息 -->
      <s:fielderror/>  
  
  <form action="login" method="post">
    <table>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="userName"></td>
      </tr>
      <tr>
        <td>密  码：</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="radio" name="status" value="0" checked="checked">学生&nbsp;&nbsp;
          <input type="radio" name="status" value="1">教师
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="登录">&nbsp;&nbsp;
          <input type="reset" value="重置">
        </td>
      </tr>
    </table>
  </form>
  
</body>
</html>