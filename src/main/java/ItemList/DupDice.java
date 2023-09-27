package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class DupDice extends Item{

	public DupDice()	{
		name = "두사위";
		description = "눈금을 2배로 만듭니다 (최대 3)";
		limit="1 2 3";
		newDice=" ";
		
		enhName = name+"(강화)";
		enhDescription = "눈금을 2배로 만듭니다 (최대 3) (턴당 2번)";		
		enhTimes=2;
	}
	
	@Override
	public void actionNewDice(TurnInfo my, int dice) {
		my.getDiceList().add(dice*2);
	}	
}
