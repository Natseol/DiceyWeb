package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class BroadSword extends Item{

	public BroadSword()	{
		name = "브로드 소드";
		description = "ㅁ+1 의 피해를 줍니다";
		attack=-1;
		addAttack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ+2 의 피해를 줍니다";		
		enhAddAttack=2;
	}	
}