package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class Claw extends Item{

	public Claw()	{
		name = "발톱";
		description = "ㅁ 의 독 피해를 줍니다 (최대 3)";
		limit="1 2 3";
		attack=-1;
		poisonStack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 독 피해를 줍니다 (최대 5)";		
		enhLimit="1 2 3 4 5";
	}
}