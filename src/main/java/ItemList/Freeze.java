package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Freeze extends Item{

	public Freeze()	{
		name = "동결";
		description = "[2] 의 냉기피해를 줍니다";
		attack=2;
		iceStack=1;
		count=5;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 냉기피해를 줍니다";		
		enhAddAttack=2;
	}	
}