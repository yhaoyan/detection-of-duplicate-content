package ReadFromDB;

//∂¡»°÷∏Œ∆
public class GetFingerPrint {
    private String[] array;
    private String name;
    private int length;
    public GetFingerPrint(){	
    }
    public GetFingerPrint(String name,String s){
    	this.name=name;
    	array=s.split(",");
    	length=array.length;
    }
    public int getlength(){
    	return length;
    }
    public String[] getString(){
    	return array;
    }
    public String getname(){
    	return name;
    }
}
