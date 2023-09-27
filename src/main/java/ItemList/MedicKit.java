package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class MedicKit extends Item{
	
	public MedicKit()	{
		name = "구급상자";
		description="8 의 체력을 회복합니다";
		recovery=8;
		count=16;
		
		enhName = name+"(강화)";
		enhDescription = "8 의 체력을 회복합니다";
		enhCount=12;
	}	
}
