public class Trigger
{
    private String id;
    private String type;
    private int status;

    public Trigger(String id, String type, int status)
    {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public String getId()
    {
        return this.id;
    }

    public String getType()
    {
        return this.type;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public String toString(){
    	return "id: " + this.id + ", type: " + this.type + ", status: " + this.status;
    }

}
