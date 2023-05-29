package Assignments.武器数组;

public class Army {
    Weapon[] weapons;
    public Army(){

    }

    public Army(int count){
       weapons = new Weapon[count];
    }
    public void addWeapon(Weapon weapon) throws AddWeaponException{
        for (int i = 0; i < weapons.length; i++){
            if (weapons[i] == null){
                weapons[i] = weapon;
                System.out.println("添加武器成功！");
                return;
            }
        }
        throw new AddWeaponException("添加武器失败！");
    }

    public void attackable(){
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] instanceof Shootalbe) {
                Shootalbe st = (Shootalbe) weapons[i];
                st.shoot();
            }
        }
    }
    public void movable(){
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] instanceof Movable) {
                Movable mv = (Movable) weapons[i];
                mv.move();
            }
        }
    }
}
