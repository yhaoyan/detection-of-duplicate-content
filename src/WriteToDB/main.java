package WriteToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ReadFromDB.Compare;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ComputeFingerPrint aa=new ComputeFingerPrint("file1.txt");//读取文件
		ComputeFingerPrint.compute(); //计算指纹
		aa.writeinDB();  //将指纹写进数据库
		Compare bbb=new Compare();  //比较大家的指纹
		bbb.compareWith();  
		bbb.print();  //打印有抄袭嫌疑的人
    }
}

