package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class Dagger extends Item{

	public Dagger()	{
		name = "단검";
		description = "ㅁ 의 피해를 줍니다 (최대 3) (재사용 가능)";
		limit="1 2 3";
		attack=-1;
		times=-1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ+1 의 피해를 줍니다 (최대 3) (재사용 가능)";		
		enhAddAttack=1;
	}
}
