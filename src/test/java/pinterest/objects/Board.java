package pinterest.objects;

public class Board {

    String name;
    boolean isSecret;

    public Board(String name, boolean isSecret){
        this.name = name;
        this.isSecret = isSecret;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setIsSecret(boolean isSecret){
        this.isSecret = isSecret;
    }

    public boolean getIsSecret(){
        return this.isSecret;
    }
}
