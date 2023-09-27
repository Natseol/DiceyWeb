package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class Scythe extends Item{

	public Scythe()	{
		name = "낫";
		description = "ㅁ 의 피해를 줍니다. 5 이상일 경우 [2] 만큼 회복합니다";
		attack=-1;
		activeLimit="5 6 7 8 9 10 11";
		recovery=2;
	}
}
