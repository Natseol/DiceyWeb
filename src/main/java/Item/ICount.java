package Item;

import Battle.TurnInfo;

public interface ICount {
	public void setCount(int count);
	public int getCount();
	
	default boolean actionCount(int dice, TurnInfo my, int idx) {
		if (my.getTurnCount(idx)-dice>0) {
			my.setTurnCount(idx, my.getTurnCount(idx)-dice);
			return true;
		}
		else {
			my.setTurnCount(idx, getCount());
			return false;
		}		
	};
}
