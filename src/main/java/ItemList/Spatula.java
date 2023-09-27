package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Spatula extends Item{

	public Spatula()	{
		name = "주걱";
		description = "눈금을 뒤집습니다";
		newDice=" ";
		
		enhName = name+"(강화)";
		enhDescription = "눈금을 뒤집습니다 (턴당 2번)";		
		enhTimes=2;
	}	
	
	@Override
	public void actionNewDice(TurnInfo my, int dice) {
		my.getDiceList().add(7-dice);
	}	
}
