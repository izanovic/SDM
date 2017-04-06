public class Stoplicht {
	private String id;
	private int status;
	
	public Stoplicht(String id, int status){
		this.id = id;
		this.status = status;
	}
	
	public String getId(){
		return this.id;
	}
	
	public int getStatus(){
		return this.status;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
			
}
