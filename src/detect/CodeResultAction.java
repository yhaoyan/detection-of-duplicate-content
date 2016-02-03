package detect;

import ReadFromDB.Compare;
import WriteToDB.ComputeFingerPrint;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import com.opensymphony.xwork2.ActionContext;

public class CodeResultAction extends ActionSupport{
	
	private static final int BUFFER_SIZE = 16*1024;
	public static String parentpath = "d:";
	public static String path = "d:\\homework";
//	static String codestr = "";

//	public String name = "";
	
    public String execute() throws Exception{
    	
    	detCode(parentpath, path);
    	Compare bbb=new Compare();  //比较大家的指纹
    	bbb.compareWith(); 
    	bbb.print();  //打印有抄袭嫌疑的人
//    	ActionContext.getContext().getSession().put("name",name);
//     	ActionContext.getContext().getSession().put("codestr",codestr);
//    	ActionContext.getContext().getSession().put("codestr",docresulter);
    	return "success";
    }
    
    public void detCode(String parentpath, String path) throws Exception{
   	
    	try{
    		File file = new File(path);
    		if(!file.isDirectory()){
    			String fileName=file.getName();
    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    		    if(prefix.equals("cpp") || prefix.equals("java"))
    				copy(parentpath, fileName);
    		}
    		else if(file.isDirectory()){
    			String[] filelist = file.list();
    			for(int i=0; i<filelist.length; i++){
    				File readfile = new File(path + "\\" + filelist[i]);
    				if(!readfile.isDirectory()){
    					String fileName=readfile.getName();
    	    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    	    			if(prefix.equals("cpp") || prefix.equals("java"))
    	    				copy(path, fileName);	
    				}
    				else if(readfile.isDirectory()){
    					detCode(path, path + "\\" + filelist[i]);
    				}    				
   			    }
    		}
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}

	}
    
    public void copy(String parentpath,String fileName) throws IOException{
//     	InputStream in = null;
        File file = new File(parentpath);
        
       
/*    	try{
    		in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
    	
    		byte[] buffer = new byte[BUFFER_SIZE];
    		String strread;
    		int len = 0;
    	   	while((len=in.read(buffer))>0){
    	   		strread = new String(buffer);
//    			strread = String.copyValueOf(strread.toCharArray(), 0, len); 
    			codestr = codestr.concat(strread);
    		}
*/    	
    	
    	ComputeFingerPrint aa=new ComputeFingerPrint(parentpath+"\\"+fileName);//读取文件
    	ComputeFingerPrint.compute(); //计算指纹
    	aa.writeinDB(file.getName());  //将指纹写进数据库
 //       aa.writeinDB(file.getName());  	
   		

/*    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(in != null)
    			try{
    				in.close();
    			}catch(IOException e){
    				e.printStackTrace();
    			}
    		
    	}
    }*/
    }
 
}

