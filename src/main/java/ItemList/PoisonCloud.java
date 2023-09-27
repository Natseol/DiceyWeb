package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class PoisonCloud extends Item{

	public PoisonCloud()	{
		name = "독구름";		
		description="독을 겁니다 독+2 (짝수)";
		limit="-2";
		poisonStack=2;
		
		enhName = name+"(강화)";
		enhDescription = "독을 겁니다 독+3 (짝수)";		
		enhPoisonStack=3;
	}	
}
