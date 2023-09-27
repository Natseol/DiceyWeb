package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class FireSword extends Item{

	public FireSword()	{
		name = "불의 검";
		description = "ㅁ 의 피해를 줍니다 5 이상일 경우 화염피해를 줍니다";
		attack=-1;
		activeLimit="5 6 7 8 9 10 11";
		fireStack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 피해를 줍니다 5 이상일 경우 화염+2피해를 줍니다";		
		enhFireStack=2;
	}
}
