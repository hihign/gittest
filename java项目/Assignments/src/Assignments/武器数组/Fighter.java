package Assignments.武器数组;

public class Fighter extends Weapon implements Shootalbe,Movable {
    public void shoot(){
        System.out.println("战斗机开炮！");
    }
    public void move(){
        System.out.println("战斗机起飞！");
    }
}
