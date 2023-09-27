package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Bash extends Item{

	public Bash()	{
		name = "강타";
		description = "ㅁ 의 피해를 줍니다 [1]의 방어력을 얻습니다";
		attack = -1;
		defence = 1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 피해를 줍니다 [2]의 방어력을 얻습니다";		
		enhDefence=2;
	}	
}