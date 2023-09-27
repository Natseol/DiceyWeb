package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class SnowStorm extends Item{

	public SnowStorm()	{
		name = "눈폭풍";
		description = "[6] 의 냉기피해를 줍니다";
		attack=6;
		iceStack=1;
		count=8;
		
		enhName = name+"(강화)";
		enhDescription = "[8] 의 냉기피해를 줍니다";		
		enhAddAttack=2;
	}	
}