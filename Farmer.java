public class Farmer{
    private int xPos;
    private int yPos;
    private Weapon weapon;
    
    public Farmer(Weapon initWeapon){
        xPos = (int)(Math.random()*10);
        yPos = (int)(Math.random()*10);
        weapon = initWeapon;
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public String toString(){
        return "The farmer is at point (" + xPos + "," + yPos + ") and has a " + weapon.getType() + ".";
    }

    public String getName(){
        return "Farmer";
    }
}