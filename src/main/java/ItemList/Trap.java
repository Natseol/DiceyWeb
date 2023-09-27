package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Trap extends Item{

	public Trap()	{
		name = "함정";
		description = "[1] 의 전기 피해를 줍니다 독+2";
		attack=1;
		elecStack=1;
		poisonStack=2;
		count=7;
		
		enhName = name+"(강화)";
		enhDescription = "[1] 의 전기 피해를 줍니다 발화+1 독+2";		
		enhFireStack=1;
	}	
}