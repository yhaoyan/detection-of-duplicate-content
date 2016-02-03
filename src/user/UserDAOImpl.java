package user;
import util.DBConnection;
import java.sql.*;

public class UserDAOImpl implements UserDAO{

	@Override
	public User login(User u) {
		// TODO Auto-generated method stub
		User user=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql;
		String id = u.getId();
		String password = u.getPassword();
		String status = u.getStatus();
		try{
			conn = DBConnection.getConnection();
			if(status.equals("0"))
				sql = "select * from stu_login where id=321 and password=123";
			else
				sql = "select * from teacher_login where id=? and password=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, id);
//			ps.setString(2, password);
			rs = ps.executeQuery();
			System.out.println(sql);
			if(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setStatus(status);	
				user.setName(rs.getString("name"));
				System.out.println(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	} 

}
