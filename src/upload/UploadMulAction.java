package upload;

import upload.UploadMulAction;
import user.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

public class UploadMulAction extends ActionSupport{
	
	private static final int BUFFER_SIZE = 16*1024;
	//保存上传文件
	private File[] upload;
	//保存上传文件名
	private String[] uploadFileName;
	
	//保存文件地址
//	private String savePath;
	
    public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

/*	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
*/
	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String execute() throws Exception{
		User user = new User();
		user = (User)ActionContext.getContext().getSession().get("user");
    	File[] srcFiles = this.getUpload();
    	//澶勭悊姣忎釜瑕佷笂浼犵殑鏂囦欢
    	for(int i=0; i<srcFiles.length; i++){
    		//鏍规嵁鏈嶅姟鍣ㄧ殑鏂囦欢淇濆瓨鍦板潃鍜屽師鏂囦欢鍚嶅垱寤虹洰褰曟枃浠跺叏璺緞
//    		String dstPath = ServletActionContext.getServletContext().getRealPath(this.getSavePath())
// 				+ "\\" + this.getUploadFileName()[i];
    		String dstPath = "d:\\homework\\" + user.getName() + "\\" + this.getUploadFileName()[i];
    		File dstFile = new File(dstPath);
    		String dir = "d:\\homework\\" + user.getName();
    		makeDirs(dir);
    		this.copy(srcFiles[i],dstFile);
    	}
//    	return "success";
    	if(user.getName() != null){
    		
    		return "success";
    	}
    	else
    		return "error";
/*    	if(ServletActionContext.getServletContext().getRealPath(this.getSavePath()) != null){
    		ActionContext.getContext().getSession().put("path", ServletActionContext.getServletContext().getRealPath(this.getSavePath()));
    		ActionContext.getContext().getSession().put("AAA",savePath);
    		return "success";
    	}
    	else
    		return "error";
*/   		    
    }
	public static boolean makeDirs(String filePath) {
//		String folderName = getFolderName(filePath);
		if (filePath == null || filePath.isEmpty()) {
			return false;
		}
		File folder = new File(filePath);
	        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
	}

	private void copy(File src, File dst){
		InputStream in = null;
		OutputStream out = null;
		try{
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while((len=in.read(buffer))>0){
				out.write(buffer, 0, len);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in != null)
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			if(out != null)
				try{
					out.close();
				}catch(IOException e){
					e.printStackTrace();
				}
		}
		
	}
/*
	public String execute() throws Exception{
		if(upload != null){
			for(int i=0; i<upload.size(); i++){
				InputStream is = new FileInputStream(upload.get(i));
				OutputStream os = new FileOutputStream("d:"+"\\"+"upload"+"\\" + getUploadFileName().get(i));
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while((len=is.read(buffer))>0){
					os.write(buffer, 0, len);
				}
				os.close();
				is.close();
			}
		}
		if(upload==null){return "error";}
		else
		return "success";
		
	}
*/
}

