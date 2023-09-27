package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Sword extends Item{

	public Sword()	{
		name = "검";
		description = "ㅁ 의 피해를 줍니다";
		attack=-1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ+1 의 피해를 줍니다";		
		enhAddAttack=1;
	}
}
