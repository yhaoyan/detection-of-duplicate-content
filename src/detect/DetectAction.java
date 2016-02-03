package detect;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DetectAction extends ActionSupport{
	
	private static String filestr = "";
	
 
    
    public void readfile(String filepath) throws FileNotFoundException{
    	
    	try{
    		File file = new File(filepath);
    		if(!file.isDirectory()){
    			filestr = filestr.concat("&nbsp;&nbsp;&nbsp" + file.getName()+"<br>");
    		}
    		else if(file.isDirectory()){
    			String[] filelist = file.list();
    			for(int i=0; i<filelist.length; i++){
    				File readfile = new File(filepath + "\\" +filelist[i]);
    				if(!readfile.isDirectory())
    					filestr = filestr.concat("&nbsp;&nbsp;&nbsp" + readfile.getName() + "<br>");		
    				else if(readfile.isDirectory()){
    					filestr = filestr.concat(readfile.getName() + ":" + "<br>");
    					readfile(filepath + "\\" +filelist[i]);
    				}    				
   			    }
    		}
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    	
 /*  	File file = new File(filepath);
    	if(file.isDirectory()){
			String[] filelist = file.list();
			//for(int i=0; i<filelist.length; i++){
				File readfile = new File(filepath + "\\" +filelist[0]);
				
					filestr=filestr.concat(readfile.getName()+"<br>"+filelist[1]+filelist[2]+filelist[3]);	
					
				
			}
    							
    }*/
    public String execute() throws Exception{
    	String str = "d:\\homework";
    	readfile(str);
//    	filestr=filestr.concat("fadf");
    	ActionContext.getContext().getSession().put("filestr",filestr);
    	return "success";
    }
 
}

