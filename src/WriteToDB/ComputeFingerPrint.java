package WriteToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

//计算指纹值，并存进数据库
public class ComputeFingerPrint {
	private static int[] hash;
	private static int[] minHash;
	private static String bigstring;
	private static int number;
	private String fileName;
	private static int length;
	PreHandle pre;
	public ComputeFingerPrint(){
		
	}
	public ComputeFingerPrint(String filename) throws IOException{
		fileName=filename;
		pre=new PreHandle(fileName);
		bigstring=pre.getContent();
		
	}

	public static void compute(){
		int size=bigstring.length();
		int kk=4,ss=3;
		number=size-kk+1;
		hash=new int[number];
		for(int i=0;i<number;i++)
			hash[i]=0;
		for(int j=0,k=3;j<4;j++,k--)
		    hash[0]=hash[0]+(int) ((int)bigstring.toCharArray()[j]*Math.pow(ss, k));
		for(int i=1;i<number;i++)
		{
			hash[i]=(hash[i-1]-(int) ((int)bigstring.toCharArray()[i-1]*Math.pow(ss, 3)))*ss+
			    	(int)bigstring.toCharArray()[i+3];
	    }
		group();
	   /* for(int i=0;i<number;i++)
			System.out.println(hash[i]);*/
	}
	public static void group(){
		int w=4;
		int[] maxHash=new int[number-w+1];
		minHash=new int[number-w+1];
		for(int i=0;i<number-w+1;i++)
		{
			int k=findMin(i,w);
			maxHash[i]=hash[k];
		}
		
		/*for(int i=0;i<number-w+1;i++)
		{
			System.out.println("*********");
		    System.out.println(maxHash[i]+"   ");
		}*/
		minHash[0]=maxHash[0];
		int j=1;
		for(int i=1;i<number-w+1;i++)
		{
			if(maxHash[i]==maxHash[i-1])
				continue;
			else
			{
				minHash[j]=maxHash[i];
				j++;
			}
		}
		length=j;
		/*for(int i=0;i<length;i++)
		{
		    System.out.print(minHash[i]+"   ");
		    if((i+1)%10==0)
		    	System.out.println();
		}*/
	}
	public static int findMin(int begLoc,int w){
		int min=1000000;
		int loc=0;
		for(int i=begLoc;i<begLoc+w;i++)
			if(hash[i]<min)
			{
				min=hash[i];
				loc=i;
			}
	    return loc;
	}
	public String transtoString(){
		String zhiwenstring=String.valueOf(minHash[0])+",";
		for(int i=1;i<length;i++)
			zhiwenstring=zhiwenstring+minHash[i]+",";
		return zhiwenstring;
	}
	public int returnLength(){
		return length;
	}
	public void writeinDB(String name){
		String url="jdbc:mysql://localhost:3306/homework_detecting_system";
		String username="root";
		String password="";
		String sql="INSERT INTO zhiwen_table VALUES(?,?)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2,transtoString());
			pstmt.executeUpdate();	
			ResultSet rs=pstmt.executeQuery("SELECT * FROM zhiwen_table");
			/*while(rs.next())
			{
			System.out.println("student_name:"+rs.getString(1)+"  zhiwen:"+rs.getString(2));
			}*/
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
