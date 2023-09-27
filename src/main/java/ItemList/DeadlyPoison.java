package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class DeadlyPoison extends Item{

	public DeadlyPoison()	{
		name = "맹독";
		description = "[3]의 독 피해를 줍니다. 독+3";
		attack=3;
		poisonStack=3;
		count=9;
		
		enhName = name+"(강화)";
		enhDescription = "[3]의 독 피해를 줍니다. 독+4";		
		enhPoisonStack=4;
	}	
}