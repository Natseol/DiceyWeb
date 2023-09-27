package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class Infection extends Item{

	public Infection()	{
		name = "전염";
		description = "독을 2배로 늘립니다";
		poisonStack=1;
		count=24;
		
		enhName = name+"(강화)";
		enhDescription = "독을 2배로 늘립니다";		
		enhCount=18;
	}
	
	@Override
	public void actionPoisonStack(Status enemy, int dice) {
		enemy.setCondition(3,enemy.getCondition(3)*2);
		ItemScript.printIced(enemy.getCondition(3));
	};
}