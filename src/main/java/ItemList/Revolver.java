package ItemList;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Revolver extends Item{

	public Revolver()	{
		name = "리볼버";
		description = "[6] 의 피해를 줍니다 (6만 가능) (턴당 3번)";
		limit = "6";
		attack = 6;
		times=3;
		
		enhName = name+"(강화)";
		enhDescription = "[7] 의 피해를 줍니다 (6만 가능) (턴당 3번)";		
		enhAddAttack=1;
	}	
}