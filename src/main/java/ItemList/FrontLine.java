package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class FrontLine extends Item{

	public FrontLine()	{
		name = "선봉";
		description = "3 의 피해를 입고, [3] 의 방어력을 얻습니다 주사위를 돌려받습니다";
		defence=3;
		damage=3;
		newDice="-1";
		times=1;
		
		enhName = name+"(강화)";
		enhDescription = "4 의 피해를 입고, [5] 의 방어력을 얻습니다 주사위를 돌려받습니다";
		enhDefence=5;
		enhDamage=5;		
	}	
}