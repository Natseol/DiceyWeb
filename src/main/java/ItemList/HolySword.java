package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class HolySword extends Item{

	public HolySword()	{
		name = "성검";
		description="ㅁ 의 피해를 줍고, 체력을 [1] 회복합니다 (3~4 만 가능)";
		limit="3 4";
		attack=-1;
		recovery=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 피해를 줍고, 체력을 [2] 회복합니다 (3~4 만 가능)";		
		enhRecovery=2;
	}	
}