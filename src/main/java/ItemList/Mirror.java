package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Mirror extends Item{

	public Mirror()	{
		name = "거울";
		description = "이 전투에서 주사위 갯수 +1 (눈금 6만 가능)";
		limit="6";
		
		enhName = name;
		enhDescription = description;
	}
	
	@Override
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		my.setAddDice(my.getAddDice()+1);
		my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
	}
}
