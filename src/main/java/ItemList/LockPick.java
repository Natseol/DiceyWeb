package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class LockPick extends Item{
		
	public LockPick()	{
		name = "락픽";
		description = "주사위를 둘로 나눕니다 (최소 2) (턴당 2번)";
		limit="2 3 4 5 6";
		newDice="1";
		times=2;
		
		enhName = name+"(강화)";
		enhDescription = "주사위를 둘로 나눕니다 (최소 2) (턴당 3번)";		
		enhTimes=3;
	}
	
	@Override
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		switch (dice) {
		case 6:
			my.getDiceList().add(3);
			my.getDiceList().add(3);
			my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
			break;
		case 5:
			my.getDiceList().add(3);
			my.getDiceList().add(2);
			my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
			break;
		case 4:
			my.getDiceList().add(2);
			my.getDiceList().add(2);
			my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
			break;
		case 3:
			my.getDiceList().add(2);
			my.getDiceList().add(1);
			my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
			break;
		case 2:
			my.getDiceList().add(1);
			my.getDiceList().add(1);
			my.setTurnTimes(idx, my.getTurnTimes(idx)-1);
			break;		
		}
	}
}
