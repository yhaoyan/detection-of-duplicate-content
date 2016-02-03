package login;

import user.User;
import user.UserDAO;
import user.UserDAOFactory;
import util.DBConnection;

import java.util.*;
import java.sql.*;
//import java.io.IOException;
import java.lang.Exception;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String userName;
	private String password;
	private String status;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
		
	public String execute() throws Exception{
		UserDAO userDAO = UserDAOFactory.getUserDAOInstance();
		User user = new User();
		user.setId(userName);
		user.setPassword(password);
		user.setStatus(status);
		
		User u = userDAO.login(user);
		
		ActionContext.getContext().getSession().put("user",u);
		
		if(u != null)
			if(u.getStatus().equals("0"))
				return "student";
			else
				return "teacher";
		else
			return "error";			
		
	}
	
	public void validate(){
		if(userName == null || userName.equals(""))
			this.addFieldError("userName", "用户名不能为空！");
		if(password == null || password.equals(""))
			this.addFieldError("password", "密码不能为空！");
	}
	

}
