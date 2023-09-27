package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class MagicMissile extends Item{

	public MagicMissile()	{
		name = "매직미사일";		
		description="[5] 의 피해를 줍니다 (짝수)";
		limit="-2";
		attack=5;
		
		enhName = name+"(강화)";
		enhDescription = "[7] 의 피해를 줍니다 (짝수)";		
		enhAddAttack=2;
	}	
}