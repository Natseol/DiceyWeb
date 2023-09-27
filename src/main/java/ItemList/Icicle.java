package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class Icicle extends Item{

	public Icicle()	{
		name = "고드름";
		description = "냉기 +1 을 주고 눈금 1 주사위를 얻는다";
		attack=1;
		iceStack=1;
		newDice="1";
		
		enhName = name+"(강화)";
		enhDescription = "냉기 +1 을 주고 눈금 1 주사위를 얻는다 (턴당 2번)";		
		enhTimes=2;
	}	
}