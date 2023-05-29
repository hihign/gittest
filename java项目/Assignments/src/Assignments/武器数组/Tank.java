package Assignments.武器数组;

public class Tank extends Weapon implements Shootalbe,Movable {
    public void shoot(){
        System.out.println("坦克开炮！");
    }
    public void move(){
        System.out.println("坦克疾行！");
    }
}
