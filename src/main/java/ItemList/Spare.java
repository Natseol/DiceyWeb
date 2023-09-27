package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class Spare extends Item{

	public Spare()	{
		name = "예비4";
		description = "눈금 4 주사위를 획득합니다";
		newDice="4";
		
		enhName = name+"(강화)";
		enhDescription = "눈금 4 주사위를 획득합니다 (턴당 2번)";		
		enhTimes=2;
	}	
}
