package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Reroll extends Item{

	public Reroll()	{
		name = "변경";
		description = "주사위를 다시 굴립니다 (턴당 2번)";		
		times=2;
		newDice="7";
		
		enhName = name+"(강화)";
		enhDescription = "주사위를 다시 굴립니다 (턴당 4번)";		
		enhTimes=4;
	}
}
