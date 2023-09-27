package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class EnhancedScythe extends Item{

	public EnhancedScythe()	{
		name = "낫(강화)";
		description = "ㅁ 의 피해를 줍니다. 4 이상일 경우 [3] 만큼 회복합니다";
		attack=-1;
		activeLimit="4 5 6 7 8 9 10 11";
		recovery=3;
	}
}