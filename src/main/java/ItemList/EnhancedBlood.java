package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class EnhancedBlood extends Item{

	public EnhancedBlood()	{
		name = "흡혈(강화)";
		description = "1 만큼 피해를 주고 ㅁ만큼 체력을 흡수합니다.";
		attack=1;
		recovery=-1;
	}
}
