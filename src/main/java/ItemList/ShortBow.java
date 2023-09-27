package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.*;
import Item.Item;

public class ShortBow extends Item{
	
	public ShortBow()	{
		name = "짧은활";
		description="[6] 의 피해를 줍니다";
		attack=6;
		count=8;
		
		enhName = name+"(강화)";
		enhDescription = "[8] 의 피해를 줍니다";		
		enhAddAttack=2;
	}	
}
