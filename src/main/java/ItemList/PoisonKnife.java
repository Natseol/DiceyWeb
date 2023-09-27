package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class PoisonKnife extends Item{

	public PoisonKnife()	{
		name = "독칼";
		description = "[1] 의 독 피해를 줍니다 (1눈 주사위 2개 필요)";
		limit="1";
		attack=1;
		poisonStack=1;
		needDice=1;
		
		enhName = name+"(강화)";
		enhDescription = "[1] 의 독+2 피해를 줍니다 (1눈 주사위 2개 필요)";		
		enhPoisonStack=2;
	}	
}