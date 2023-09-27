package Item;

import Battle.TurnInfo;

public interface ITimes {
	public void setTimes(int times);
	public int getTimes();
	
	default void actionTimes(TurnInfo my, int idx) {
		my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
	};
}
