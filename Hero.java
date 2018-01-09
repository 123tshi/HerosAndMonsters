import java.io.*;
import java.util.*;
public class Hero{
    private int xPos;
    private int yPos;
    private int health;
    private Weapon weapon;
    private boolean hasArmor;
    private boolean hasSword;
    private int farmerCount;
    
    public Hero(){
        xPos = 0;
        yPos = 9;
        farmerCount = 0;
        health = 100;
        hasArmor = false;
        hasSword = false;
        weapon = new Weapon("dagger", 10, 30);
    }
        
    public int getX(){
        return xPos;
    }
    
    public int getY(){
        return yPos;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void move(String dir, Object map[][]){
        if(dir.equals("w") && getY() != 0){
            map[getX()][getY()] = null;
            yPos -= 1;
        }else if(dir.equals("w") && getY() == 0){
            System.out.println("Hero cannot move up. Hero is at the edge of the screen.");
        }
        if(dir.equals("s") && getY() != 9){
            map[getX()][getY()] = null;
            yPos += 1;
        }else if(dir.equals("s") && getY() == 9){
            System.out.println("Hero cannot move down. Hero is at the edge of the screen.");
        }
        if(dir.equals("a") && getX() != 0){
            map[getX()][getY()] = null;
            xPos -= 1;
        }else if(dir.equals("a") && getX() == 0){
            System.out.println("Hero cannot move left. Hero is at the edge of the screen.");
        }
        if(dir.equals("d") && getX() != 9){
            map[getX()][getY()] = null;
            xPos += 1;
        }else if(dir.equals("d") && getX() == 9){
            System.out.println("Hero cannot move right. Hero is at the edge of the screen.");
        }
        System.out.println("(" + xPos + "," + yPos + ")");
    }
    
    public boolean checkOverlap(Object map[][], String dir){
        int newX = getX(), newY = getY();
        if(getX() == 0 || getX() == 9 || getY() == 0 || getY() == 9){
            return false;
        }
        if(dir.equals("w") && getY() != 0){
            newY -= 1;
        }
        else if(dir.equals("a") && getX() != 0){
            newX -= 1;
        }
        else if(dir.equals("s") && getY() != 9){
            newY += 1;
        }
        else if(dir.equals("d") && getX() != 9){
            newX += 1;
        }
        
        if(map[newX][newY] != null){
            if(map[newX][newY].getClass().equals(Monster.class)){
                System.out.println("Hero encounters a monster!");
                Monster monster = (Monster)map[newX][newY];
                boolean isAlive = fightOrFlight(monster);
                if(isAlive){
                    map[newX][newY] = null;
                    return false;
                }
            }
            else if(map[newX][newY].getClass().equals(Potion.class)){
                Potion potion = (Potion)map[newX][newY];
                health = 100;
                System.out.println("Hero encounters a potion! Hero's health goes up to " + getHealth() + ".");
                map[newX][newY] = null;
                return false;
            }
            else if(map[newX][newY].getClass().equals(Farmer.class)){
                System.out.println("Hero encounters a Farmer!\nFarmer says:");
                Farmer farmer = (Farmer)map[newX][newY];
                int monsterCount = 0;
                farmerCount++;
                for(int i = 0; i < map.length; i++){
                    for(int j = 0; j < map[i].length; j++){
                        if(map[i][j] != null){
                            if(map[newX][newY].getClass().equals(Monster.class)){
                                monsterCount++;
                            }
                        }
                    }
                }
                if(farmerCount == 1){
                    System.out.println("My sheep have been taken… \nMy family is gone and I have nothing left except for bronze armor I have buried… \nOnly a true hero will receive this.");
                    if(monsterCount <= 4){
                        System.out.println("You are a true hero. You will receive my armor");
                        hasArmor = true;
                        map[newX][newY] = null;
                    }
                }else if(farmerCount == 2){
                    System.out.println("My sheep have been taken… \nMy family is gone and I have nothing left except for broad sword I have buried… \nOnly a true hero will receive this.");    
                    if(monsterCount <= 2){
                        System.out.println("You are a true hero. You will receive my sword");
                        hasSword = true;
                        map[newX][newY] = null;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean fightOrFlight(Monster monster){
        System.out.println(monster.toString());
        boolean answerCheck = false;
        String fightOrFlight = "";
        boolean willRun = false;
        boolean isDead = false;
        if(hasSword){
            weapon = new Weapon("broad sword", 20, 50);
        }
        do{
            Scanner kbr = new Scanner(System.in);
            System.out.println("What will Hero do? (attack/run)");
            fightOrFlight = kbr.next();
            double chanceRun = 0;
            if(monster.getSpeed() == 0){
                chanceRun = .75;
            }else if(monster.getSpeed() == 1){
                chanceRun = .5;
            }else if(monster.getSpeed() == 2){
                chanceRun = .25;
            }else if(monster.getSpeed() == 3){
                chanceRun = 0;
            }
            int damage = monster.getAttack();
            if(hasArmor){
                damage = damage*2/3;
            }
            int monsterInitHealth = monster.getHealth();
            if(fightOrFlight.equals("run")){
                double runOrNot = Math.random();
                if(runOrNot <= chanceRun){
                    System.out.println("Hero outran the monster!");
                    willRun = true;
                    break;
                }else{
                    System.out.println("Hero tries to run. The monster is too fast!");
                    health -= damage;
                    if(health <= 0){
                        health = 0;
                        isDead = true;
                    }
                    System.out.println("The monster attacks! Hero's energy goes down to " + health + "/100.");
                }
            }else if(fightOrFlight.equals("attack")){
                System.out.println("Hero attacks!");
                monster.attacked(weapon.getDmg());
                System.out.println("The monster's health goes down to " + monster.getHealth() + "/" + monsterInitHealth + ".");
                health -= damage;
                if(health <= 0){
                    health = 0;
                    isDead = true;
                }
                System.out.println("The monster attacks! Hero's energy goes down to " + health + "/100.");
            }else{
                System.out.println("Please only enter attack or run.");
            }
        }while(monster.getHealth() > 0 && getHealth() > 0);
        if(getHealth() == 0){
            System.out.println("Hero's energy has gone down to 0. Game over.");
        }
        if(monster.getHealth() == 0){
            System.out.println("You killed the monster!");
            return true;
        }
        return false;
    }
    
    public String toString(){
        return "The hero has " + health + " health remaining and is at position (" + xPos + ", " + yPos + ").";
    }
    
    public String getName(){
        return "Hero";
    }
}