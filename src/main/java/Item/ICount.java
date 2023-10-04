package Item;

import Battle.TurnInfo;

public interface ICount {
	public void setCount(int count);
	public int getCount();
	
	default boolean actionCount(int dice, TurnInfo my, int idx) {
		int beforeNum = my.getCountState(idx);
		if (my.getCountState(idx)-dice>0) {
			my.setCountState(idx, my.getCountState(idx)-dice);
			int num = my.getCountState(idx);
			ItemScript.printCount(beforeNum, num);
			return true;
		}
		else {
			my.setCountState(idx, getCount());
			return false;
		}		
	};
}
