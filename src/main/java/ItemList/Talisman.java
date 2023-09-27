package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class Talisman extends Item{
	
	public Talisman()	{
		name = "부적";
		description = "3 주사위를 2개 얻습니다";
		newDice="3 3";
		count=7;
		
		enhName = name+"(강화)";
		enhDescription = "3 주사위를 2개 얻습니다";
		enhCount=6;
	}	
}