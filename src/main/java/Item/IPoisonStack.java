package Item;

import Battle.TurnInfo;
import Character.Status;

public interface IPoisonStack {
	
	public void setPoisonStack(int num);
	public int getPoisonStack();
	
	default void actionPoisonStack(Status enemy, int dice) {
		enemy.setCondition(3,enemy.getCondition(3)+getPoisonStack());
		ItemScript.printPoisoned(getPoisonStack());
	};
}
