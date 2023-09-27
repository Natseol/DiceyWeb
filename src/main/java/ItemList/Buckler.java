package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Buckler extends Item{

	public Buckler()	{
		name = "버클러";
		description = "[4] 의 방어력을 얻습니다";
		defence=4;
		count=7;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 방어력을 얻습니다";
		enhCount=5;
	}	
}