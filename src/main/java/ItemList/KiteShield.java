package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class KiteShield extends Item{

	public KiteShield()	{
		name = "카이트쉴드";
		description = "[8] 의 방어력을 얻습니다";
		defence=8;
		count=14;
		
		enhName = name+"(강화)";
		enhDescription = "[8] 의 방어력을 얻습니다";
		enhCount=11;
	}	
}