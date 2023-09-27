package Item;

import Character.Status;

public interface IDamage {
	public void setDamage(int damage);	
	public int getDamage();	
	
	default void actionDamage(Status player, int dice) {//음수면 dice값으로 계산
		if (getDamage() < 0) {
			player.setHp(player.getHp()-(dice*getDamage()*-1));
			player.setSp(player.getSp()+(dice*getDamage()*-1));
			ItemScript.printTakedDamage(dice*getDamage()*-1);
		}
		else if (getDamage() > 0) {
			player.setHp(player.getHp()-getDamage());
			player.setSp(player.getSp()+getDamage());
			ItemScript.printTakedDamage(getDamage());
		}	
	}	
}
