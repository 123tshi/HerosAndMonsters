public class Potion{
    private int healAmt;
    private int xPos, yPos;
    
    public Potion(){
        xPos = (int)(Math.random()*10);
        yPos = (int)(Math.random()*10);
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public String toString(){
        return "Potion heal capacity: " + healAmt + " XP.\nLocation: " + "(" + xPos + "," + yPos + ").";
    }
    
    public String getName(){
        return "Hero";
    }
}