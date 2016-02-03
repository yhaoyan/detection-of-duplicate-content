package WriteToDB;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//代码预处理，删除空格，换行，和注释

public class PreHandle {
	private String text;
	private String filename;
	PreHandle(){
		
	}
	PreHandle(String name) throws IOException{
		filename=name;
		readfile();
	}
    public void readfile() throws IOException{
    	FileInputStream fi=new FileInputStream(new File(filename));
    	
        byte[] b=new byte[fi.available()];  //available()返回能从输入流读到的字节数
        fi.read(b);  //将文件读进字节数组里
        text=new String(b);//再将字节数组中的内容转化成字符串形式输出
        //去注释
    	text = text.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/", "");
    	//System.out.println(text);
    	text=text.replace("\t",""); 
    	text=text.replace("\n","");  
    	text=text.replace("\r","");
    	text=text.replace(" ","");
    	//System.out.println(text);
    	fi.close();

	 }
    public String getContent(){
    	return text;
    }
    
}
