package Item;

import Battle.TurnInfo;
import Character.Status;

public interface IIceStack {
	
	public void setIceStack(int num);
	public int getIceStack();
	
	default void actionIceStack(Status enemy, int dice) {
		enemy.setCondition(1,enemy.getCondition(1)+getIceStack());
		ItemScript.printIced(getIceStack());
	};
}
