public class Monster{
    //blub blub
    
    private int attack;
    private int health;
    private int speed;
    private int xPos;
    private int yPos;
    
    public Monster(){
        //blublbubblu
        attack = (int)(Math.random()*29) + 1;
        health = (int)(Math.random()*100) + 1;
        speed = (int)(Math.random()*4);
        xPos = (int)(Math.random()*10);
        yPos = (int)(Math.random()*10);
    }
    
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getAttack(){
        return attack;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void attacked(int dmg){
        health -= dmg;
        if(health <= 0){
            health = 0;
        }
    }
    
    public String toString(){
        return "This monster has " + attack + " attack, " + health + " health, " + speed + " speed, and position (" + xPos + "," + yPos + ").";
    }
    
    public String getName(){
        return "Monster";
    }
}