package Item;

import Character.Status;

public interface IAttack {
	public void setAttack(int attack);	
	public int getAttack();
	public void setAddAttack(int addAttack);	
	public int getAddAttack();	
	
	default void actionAttack(Status player, Status enemy, int dice) {//음수면 dice값으로 계산
		if (getAttack() < 0) {
			switch (getAttack()) {
			case -2:
				ItemScript.printDamage2((dice*getAttack()*-1)/2+getAddAttack());
				break;
			case -3:
				ItemScript.printDamage3((dice*getAttack()*-1)/3+getAddAttack());
				break;
			default:
				ItemScript.printDamage(dice*getAttack()*-1+getAddAttack());
				break;
			}
			enemy.subtractHp(dice*getAttack()*-1+getAddAttack());
		}
		else if (getAttack() > 0) {
			enemy.subtractHp(getAttack()+getAddAttack());
			ItemScript.printDamage(getAttack()+getAddAttack());
		}	
	}	
}
