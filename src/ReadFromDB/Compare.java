package ReadFromDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//比较大家的指纹

import com.opensymphony.xwork2.ActionContext;


public class Compare {
	private ArrayList<GetFingerPrint> object;
	//private int samenum;
	private String[] array1,array2;
	private double[] rate;
    public Compare(){
    	object=new ArrayList<GetFingerPrint>();        
        String url="jdbc:mysql://localhost:3306/homework_detecting_system";
	    String username="root";
	    String password="";
	    String sql="SELECT * FROM zhiwen_table";
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url, username, password);
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			GetFingerPrint a=new GetFingerPrint(rs.getString(1),rs.getString(2));
		    object.add(a);
		}
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
    public void compareWith(){
        array1=new String[object.size()*2];
        array2=new String[object.size()*2];
        rate=new double[object.size()*2];

    	int loc=0;
    	int max=0;
    	for(int i=0;i<object.size();i++){
    		for(int j=i+1;j<object.size();j++)
    		{
    			if(object.get(i).getlength()>object.get(j).getlength())
    				max=object.get(i).getlength();
    			else
    				max=object.get(j).getlength();
    			int samenum=0;
    			for(int k=0;k<object.get(i).getlength();k++)
    			{
    				
    				for(int l=0;l<object.get(j).getlength();l++)
    					if(object.get(i).getString()[k].equals(object.get(j).getString()[l]))
    						samenum++;}
    			   if(samenum/max>0.7)
    			   {
    				   array1[loc]=object.get(i).getname();
    				   array2[loc]=object.get(j).getname();
    				   rate[loc]=samenum/max;
    				   loc++;
    			   }
    			
    		}
    	}
    }
    public void print(){
    	String codeStr = "";
    	for(int i=0;i<object.size()*2&&rate[i]!=0;i++)
    		codeStr = codeStr.concat(array1[i]+" "+array2[i]+" "+rate[i]);
    	ActionContext.getContext().getSession().put("codeStr",codeStr);
 //   	System.out.println(object.size()+" "+object.get(0).getlength()+" "+object.get(0).getString()[10]);
    }
    }

