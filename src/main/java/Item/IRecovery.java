package Item;

import Character.Status;

public interface IRecovery {
	public void setRecovery(int recovery);	
	public int getRecovery();	
	
	default void actionRecovery(Status player, int dice) {//음수면 dice값으로 계산
		if (getRecovery() < 0) {
			player.addHp(dice*getRecovery()*-1);
			ItemScript.printRecovery(dice*getRecovery()*-1);
		}
		else if (getRecovery() > 0) {
			player.addHp(getRecovery());
			ItemScript.printRecovery(getRecovery());
		}	
	}	
}