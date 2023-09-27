package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Blender extends Item{

	public Blender()	{
		name = "자르기";
		description = "1 눈금을 분리합니다 (최소 2)";
		limit="2 3 4 5 6";
		newDice=" ";
		
		enhName = name+"(강화)";
		enhDescription = "1 눈금을 분리합니다 (최소 2) (턴당 2번)";		
		enhTimes=2;
	}
	
	@Override
	public void actionNewDice(TurnInfo my, int dice) {
		my.getDiceList().add(dice-1);
		my.getDiceList().add(1);		
	}	
}
