package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class MagicShield extends Item{

	public MagicShield()	{
		name = "매직쉴드";
		description = "[4] 의 방어력을 얻고, [2] 의 체력을 회복합니다";
		recovery=2;
		defence=4;
		count=9;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 방어력을 얻고, [3] 의 체력을 회복합니다";
		enhRecovery=3;
	}	
}