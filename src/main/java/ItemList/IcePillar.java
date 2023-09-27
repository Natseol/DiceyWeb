package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class IcePillar extends Item{

	public IcePillar()	{
		name = "얼음기둥";
		description = "[두번째 눈금+3] 의 얼음 피해를 줍니다 (홀수 주사위 2개 필요)";
		limit="-1";
		attack=-1;
		addAttack=3;
		iceStack=1;
		needDice=1;
		
		enhName = name+"(강화)";
		enhDescription = "[두번째 눈금+5] 의 얼음 피해를 줍니다 (홀수 주사위 2개 필요)";		
		enhAddAttack=5;
	}
}