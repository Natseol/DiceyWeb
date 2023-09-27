package Item;

import Battle.TurnInfo;

public interface IUse {

	public void setUse(boolean use);
	public boolean getUse();
	
	default void actionUse(TurnInfo my, int idx) {
		my.setTurnUse(idx, 1);
	};
}
