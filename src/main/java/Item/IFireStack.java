package Item;

import Battle.TurnInfo;
import Character.Status;

public interface IFireStack {
	
	public void setFireStack(int num);
	public int getFireStack();
	
	default void actionFireStack(Status enemy, int dice) {
		enemy.setCondition(0,enemy.getCondition(0)+getFireStack());
		ItemScript.printFire(getFireStack());
	};
}
