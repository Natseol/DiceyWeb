package Item;

import Battle.TurnInfo;

public interface ICount {
	public void setCount(int count);
	public int getCount();
	
	default boolean actionCount(int dice, TurnInfo my, int idx) {
		if (my.getCountState(idx)-dice>0) {
			my.setCountState(idx, my.getCountState(idx)-dice);
			return true;
		}
		else {
			my.setCountState(idx, getCount());
			return false;
		}		
	};
}
