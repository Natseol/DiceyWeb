package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Sacrifice extends Item{

	public Sacrifice()	{
		name = "희생";
		description = "[3] 의 피해를 입고, 주사위를 생성합니다";
		damage=3;
		newDice="-1 7";
		
		enhName = name+"(강화)";
		enhDescription = "[2] 의 피해를 입고, 주사위를 생성합니다";
		enhDamage=2;
	}
}