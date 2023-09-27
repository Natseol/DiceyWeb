package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class IceSword extends Item{

	public IceSword()	{
		name = "냉기의 검";		
		description="[4] 의 얼음 피해를 줍니다 (3 or 5만 가능)";
		limit="3 5";
		attack=4;
		
		enhName = name+"(강화)";
		enhDescription = "[6] 의 얼음 피해를 줍니다 (3 or 5만 가능)";		
		enhAddAttack=2;
	}	
}