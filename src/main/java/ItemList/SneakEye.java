package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class SneakEye extends Item{
	
	public SneakEye()	{
		name = "뱀의눈";
		description = "1 주사위를 2개 얻습니다";
		newDice="1 1";
		count=5;
		
		enhName = name+"(강화)";
		enhDescription = "1 주사위를 2개 얻습니다";
		enhCount=3;
	}	
}