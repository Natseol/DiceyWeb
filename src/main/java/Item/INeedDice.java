package Item;

import Battle.TurnInfo;

public interface INeedDice {
	public void setNeedDice(int needDice);	
	public int getNeedDice();	

	default boolean actionNeedDice(TurnInfo my, int idx, int dice) {
		if (my.getNeedDiceState(idx)==0) {
			my.setNeedDiceState(idx, dice);
			return true;
		}
		else {
			my.setNeedDiceState(idx, 0);
			return false;
		}	
	}
}