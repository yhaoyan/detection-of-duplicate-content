package doc_algorithm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChaChong {
	//编辑距离算法求相似度
	public Object[] EDcalculate(ArrayList text,ArrayList name)
	{
		EditDistance ed=new EditDistance();//编辑距离算法
		// 定义集合的二维数组储存相似的学生信息
		ArrayList TotalNameList = new ArrayList(new ArrayList()),  
				TotalSimilarity = new ArrayList(new ArrayList());
		for(int i=0;i<text.size();i++)
		{
			for(int j=1;j<text.size();j++)
			{
				if (i >= j)
					continue;
				char a[], b[];
				a = new char[((String) text.get(i)).length()];
				b = new char[((String) text.get(j)).length()];
				((String) text.get(i)).getChars(0, ((String) text.get(i)).length(), a, 0);
				((String) text.get(j)).getChars(0, ((String) text.get(j)).length(), b, 0);
				int result = ed.edit(a, b, a.length, b.length);
				float maxLength = ((String) text.get(i)).length() > ((String) text.get(j)).length() ?((String) text.get(i)).length() : ((String) text.get(j)).length();
						float similarity = 1 - (result / maxLength);
						// JOptionPane.showMessageDialog(null, "编辑距离:" + result, "结果",
						// JOptionPane.INFORMATION_MESSAGE);
//						System.out.println(text[i] + "和" + text[j] + "两串的相似度是："
//							+ similarity);
						if (similarity > 0.50) // 输出相似度超过%50的学生
						{
							java.text.NumberFormat percentFormat = java.text.NumberFormat
									.getPercentInstance();// 将相似度转化为百分比显示
							percentFormat.setMaximumFractionDigits(3);
							String pesimilarity = percentFormat.format(similarity);
//							System.out.println(pesimilarity);
							TotalNameList.add(name.get(i));
							TotalNameList.add(name.get(j));
							TotalSimilarity.add(pesimilarity);
						}
			}
		}
		Object[] o = {  TotalNameList,TotalSimilarity };// 将存储相似学生信息的集合存储到Object中
		return o;
	}
	//余弦定理求相似度
	public Object[] CalculateSimilarityAlgrithm(String text[],String name[]) throws IOException
	 {
		 CosineSimilarAlgorithm csa=new  CosineSimilarAlgorithm(); //余弦定理算法
		// 定义集合的二维数组储存动态相似的学生信息
		ArrayList TotalNameList = new ArrayList(new ArrayList()), 
		TotalSimilaity = new ArrayList(new ArrayList());
		for (int i = 0; i < text.length; i++) // 计算相似度
		{
			for (int j = 1; j <text.length; j++) {
				if (i >= j)
					continue;
				double result= csa.getSimilarity(text[i], text[j]);
//						System.out.println(text[i] + "和" + text[j] + "两串的相似度是："
//								+ result);
						if (result > 0.50) // 输出相似度超过%50的学生
						{
							java.text.NumberFormat percentFormat = java.text.NumberFormat
									.getPercentInstance();// 将相似度转化为百分比显示
							percentFormat.setMaximumFractionDigits(3);
							String pesimilarity = percentFormat.format(result);
//							System.out.println(pesimilarity);
							 // 将相似的学生的信息加入到集合
							TotalNameList.add(name[i]);
							TotalNameList.add(name[j]);
							TotalSimilaity.add(pesimilarity);//相似度
						}
			}
		}
		Object[] o = { TotalNameList,TotalSimilaity };// 将存储相似学生信息的集合存储到Object中
		return o;
	}
		public void savePersimilaity(Object[] object) {
			// TODO Auto-generated method stub
			ArrayList TotalNameList = (ArrayList) object[0];
			ArrayList TotalSimilarity = (ArrayList) object[1];
			String stu_id=null;
			String stu_name=null;
			String stu_similarity=null;
			String sql2="UPDATE similarity SET similarity = '"+stu_similarity+" ' WHERE id= '"+stu_id+"'";
			String sql1="select id from stu_login"; //  where name= '"+"小红"+"'
			ResultSet rs1;
			ResultSet rs2;
			DBConnection dbConnection=new DBConnection();
			Statement sta=dbConnection.getStatement();
			for(int i=0;i<TotalNameList.size();i++){
				stu_name=(String) TotalNameList.get(i);
				System.out.println(stu_name);
				try {
					rs1=sta.executeQuery(sql1);	
					System.out.println(rs1.getString(1));
					if(rs1.next()){
						System.out.println("3");
						stu_id=rs1.getString("id");
					stu_similarity=(String) TotalSimilarity.get(i);
					System.out.println(stu_similarity);
					System.out.println(stu_id);
					}
					stu_similarity=(String) TotalSimilarity.get(i);
					sta.executeUpdate(sql2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
