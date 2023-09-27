package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class Crossbow extends Item{
	
	public Crossbow()	{
		name = "석궁";
		description="[13-마지막 눈금] 의 피해를 줍니다 (2이하 : 발화+1)";
		attack=1;
		activeLimit="1 2";
		fireStack=1;
		count=13;
		
		enhName = name+"(강화)";
		enhDescription = "[13-마지막 눈금] 의 피해를 줍니다 (발화+1)";		
		enhActiveLimit="";
	}
	
	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		enemy.subtractHp(13-dice);
		printDamage(13-dice);
	}
}
