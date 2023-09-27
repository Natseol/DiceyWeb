package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Guard extends Item{

	public Guard()	{
		name = "막기";
		description = "[3] 의 방어력을 얻습니다 (홀수)";
		limit="-1";
		defence=3;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 방어력을 얻습니다 (홀수)";
		enhDefence=4;
	}	
}