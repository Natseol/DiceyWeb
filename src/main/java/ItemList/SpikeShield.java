package ItemList;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;
import Item.ItemScript;

public class SpikeShield extends Item{
	
	public SpikeShield()	{
		name = "가시방패";
		description = "피해를 줍니다 (홀수)  방어력을 얻습니다 (짝수) (최대 5)";
		limit="1 2 3 4 5";
		attack=1;
		activeLimit="-2";
		defence=1;
		
		enhName = name+"(강화)";
		enhDescription = "피해를 줍니다 (홀수)  방어력을 얻습니다 (짝수) (최대 5) (턴당 2번)";		
		enhTimes=2;
	}
	
	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		if (dice%2!=0) {
			enemy.subtractHp(dice);
			printDamage(dice);
		}
	}
}

