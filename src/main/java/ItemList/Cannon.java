package ItemList;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Battle.TurnInfo;
import Character.*;
import Item.Item;

public class Cannon extends Item{
	
	public Cannon()	{
		name = "캐논";
		description="[10] 의 피해를 줍니다";
		attack=10;
		count=15;
		
		enhName = name+"(강화)";
		enhDescription = "[16] 의 피해를 줍니다";		
		enhAddAttack=6;
	}	
}
