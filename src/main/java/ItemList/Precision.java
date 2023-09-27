package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Precision extends Item{

	int otherDice = 0;
	
	public Precision()	{
		name = "정밀";
		description = "6 주사위를 2개 얻습니다";
		newDice="6 6";			
		count=11;
		
		enhName = name+"(강화)";
		enhDescription = "6 주사위를 2개 얻습니다";
		enhCount=9;
	}	
}
