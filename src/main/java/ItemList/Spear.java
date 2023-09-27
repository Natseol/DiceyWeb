package ItemList;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Spear extends Item{

	public Spear()	{
		name = "창";
		description = "[10] 의 피해를 줍니다 (6 만 사용 가능)";
		limit="6";
		attack=10;
		
		enhName = name+"(강화)";
		enhDescription = "[12] 의 피해를 줍니다 (6 만 사용 가능)";
		enhAddAttack = 2;
	}	
}
