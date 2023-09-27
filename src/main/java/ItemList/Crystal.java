package ItemList;

import Battle.TurnInfo;
import Character.*;
import Dice.Roll;
import Item.Item;

public class Crystal extends Item {

	public Crystal()	{
		name = "크리스탈";
		description="[1] 의 체력을 회복하고 새 주사위를 얻습니다 (최대 3)";
		limit="1 2 3";
		recovery = 1;
		newDice="7";
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 체력을 회복하고 새 주사위를 얻습니다 (최대 3)";
		enhRecovery = -1;
	}
}
