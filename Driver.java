import java.io.*;
import java.util.*;
public class Driver{
    public static void main(String args[]){
        //blub blub
        Hero hiro = new Hero();
        //creates map with 10 rows and 10 cols
        Object[][] map = new Object[10][10];
        //fill map with objects
        
        for(int i = 0; i < 6; i++){
            Monster monster = new Monster();
            boolean monsterCreated = false;
            while(!monsterCreated){
                if(map[monster.getX()][monster.getY()] == null){
                    map[monster.getX()][monster.getY()] = monster;
                    monsterCreated = true;
                }else{
                    monster = new Monster();
                }
            }
        }
        
        for(int i = 0; i < 2; i++){
            Potion potion = new Potion();
            boolean potionCreated = false;
            while(!potionCreated){
                if(map[potion.getX()][potion.getY()] == null){
                    map[potion.getX()][potion.getY()] = potion;
                    potionCreated = true;
                }else{
                    potion = new Potion();
                }
            }
        }
        
        for(int i = 0; i < 2; i++){
            Farmer farmer = new Farmer(new Weapon("Broad Sword", 10, 10));
            boolean farmerCreated = false;
            while(!farmerCreated){
                if(map[farmer.getX()][farmer.getY()] == null){
                    map[farmer.getX()][farmer.getY()] = farmer;
                    farmerCreated = true;
                }else{
                    farmer = new Farmer(new Weapon("Broad Sword", 10, 10));
                }
            }
        }
        
        while(hiro.getHealth() > 0){
            System.out.println("\nPress w, a, s, d to move. Case sensitive!");
            Scanner kbr = new Scanner(System.in);
            String dir = kbr.next();
            if(dir.equals("x")){
                break;
            }
            if(!dir.equals("w") && !dir.equals("a") && !dir.equals("s") && !dir.equals("d")){
                System.out.println("\nPlease only enter w, a, s, or d. Case sensitive!\n");
            }else{
                if(hiro.checkOverlap(map, dir) == false){
                    hiro.move(dir, map);
                    map[hiro.getX()][hiro.getY()] = hiro;
                    printMap(map);
                    /*
                    for(int i = 0; i < map.length; i++){
                        for(int j = 0; j < map[i].length; j++){
                            if(map[i][j] != null){
                                System.out.println(map[i][j]);
                            }
                        }
                    }*/
                }
            }
        }
    }
    
    public static void printMap(Object map[][]){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[j][i] == null){
                    System.out.print("0\t");
                }
                else if(map[j][i].getClass().equals(Potion.class)){
                    System.out.print("Potion\t");
                }
                else if(map[j][i].getClass().equals(Monster.class)){
                    System.out.print("Monster\t");
                }
                else if(map[j][i].getClass().equals(Farmer.class)){
                    System.out.print("Farmer\t");
                }
                else if(map[j][i].getClass().equals(Hero.class)){
                    System.out.print("Hero\t");
                }
            }
            System.out.println("");
        }
    }
}