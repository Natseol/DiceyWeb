package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Staff extends Item{

	public Staff()	{
		name = "스태프";
		description = "ㅁ 만큼 피해를 줍니다 (짝수) (4:주사위를 돌려받습니다)";
		limit="-2";
		attack=-1;
		activeLimit="4";
		newDice="4";
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 만큼 피해를 줍니다 (4:주사위를 돌려받습니다)";
		enhLimit="";
	}
}
