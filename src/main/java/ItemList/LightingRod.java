package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class LightingRod extends Item{

	public LightingRod()	{
		name = "피뢰침";
		description = "ㅁ 의 전기 피해를 줍니다 (최대 4)";
		limit="1 2 3 4";
		attack=-1;
		elecStack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 전기 피해를 줍니다 (최대 5)";		
		enhLimit="1 2 3 4 5";
	}
}