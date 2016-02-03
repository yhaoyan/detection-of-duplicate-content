package doc_algorithm;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList text=new ArrayList();
		ArrayList name=new ArrayList();
		name.add("小红");
		name.add("王五");
		name.add("李四");
		text.add("全国计算机等级考试由教育部考试中心主办，面向全社会，用于考查应试人员计算机应用知识与技能的全国性计算机水平考试体系。成绩合格者，可获得教育部考试中心颁发的");
		text.add("全国计算机等级考试由教育部考试中心主办，面向全社会，用于考查应试人员计算机应用知识与技能的全国性计算机水平考试体系。成绩合格者，可获得教育部考试中心颁发的"
+"达芙妮赶");
text.add("全国计算机等级考试由教育部考试中心主办，面向全社会，用于考查应试人");
		// 第一步，建立对象
		ChaChong chaCong=new ChaChong();
		// 第二步，调用算法，得到结果对象。
//		Object[] o=chaCong.CalculateSimilarityAlgrithm(text, name); // 算法一
		Object[] o=chaCong.EDcalculate(text, name); // 算法二，就用这个吧
		// 第三步，建立名字，相似度，获取得到的对象中的结果
		ArrayList outname= (ArrayList) o[0];
		ArrayList outsimilarity=(ArrayList) o[1];
		// 第四步，循环取出数据，显示到网页上，这里以显示到控制台作为例子。
		for(int i=0;i<outsimilarity.size();i++){
			System.out.println(outname.get(i+i)); // 甲同学
			System.out.println(outname.get(i+i+1)); // 乙同学
		    System.out.println(outsimilarity.get(i)); // 上述两个同学的相似度
		}
		// 存到数据库的，等再说
//		chaCong.savePersimilaity(o);
	}

}
