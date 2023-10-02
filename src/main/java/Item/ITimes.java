package Item;

import Battle.TurnInfo;

public interface ITimes {
	public void setTimes(int times);
	public int getTimes();
	
	default void actionTimes(TurnInfo my, int idx) {
		my.setTimesState(idx, my.getTimesState(idx)-1);
	};
}
