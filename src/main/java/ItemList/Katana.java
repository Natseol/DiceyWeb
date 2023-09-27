package ItemList;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Katana extends Item{

	public Katana()	{
		name = "카타나";
		description = "ㅁ 의 피해를 줍니다 (최대 4) (턴당 2번)";
		limit="1 2 3 4";
		attack=-1;
		times=2;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 피해를 줍니다 (최대 5) (턴당 2번)";
		enhLimit="1 2 3 4 5";
	}	
}