package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Fist extends Item{

	public Fist() {
		name = "주먹질";
		description = "[3] 의 피해를 줍니다 (턴당 2번)";
		attack=3;
		times=2;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 피해를 줍니다 (턴당 2번)";		
		enhAddAttack=1;
	}	
}
