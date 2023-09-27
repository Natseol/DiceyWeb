package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Clone extends Item{
	
	public Clone()	{
		name = "위조";
		description = "주사위를 복제합니다 (최대 5)";
		limit="1 2 3 4 5";
		newDice="-1 -1";
		
		enhName = name+"(강화)";
		enhDescription = "주사위를 복제합니다";
		enhLimit="";
	}
}
