public class Weapon{
    //blub blub blub blub blub
    private String type;
    private int minDmg;
    private int maxDmg;
    
    public Weapon(String initType, int initMinDmg, int initMaxDmg){
        type = initType;
        minDmg = initMinDmg;
        maxDmg = initMaxDmg;
    }
    
        
    public String getType(){
        return type;
    }
    
    public int getDmg(){
        return minDmg;
    }
    
    public String toString(){
        return "Weapon type: " + type + "\nWeapon has damage range from " + minDmg + " to " + maxDmg + ".";
    }
}