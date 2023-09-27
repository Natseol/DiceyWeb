package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Heal extends Item{

	public Heal()	{
		name = "치유";
		description="ㅁ 의 체력을 회복합니다 (최대 3)";
		limit="1 2 3";
		recovery=-1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 체력을 회복합니다 (최대 4)";
		enhLimit="1 2 3 4";
	}	
}
