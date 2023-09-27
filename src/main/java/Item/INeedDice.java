package Item;

import Battle.TurnInfo;

public interface INeedDice {
	public void setNeedDice(int needDice);	
	public int getNeedDice();	

	default boolean actionNeedDice(TurnInfo my, int idx, int dice) {
		if (my.getNeedDice(idx)==0) {
			my.setNeedDice(idx, dice);
			return true;
		}
		else {
			my.setNeedDice(idx, 0);
			return false;
		}	
	}
}