package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class Box extends Item{
	
	public Box()	{
		name = "상자";
		description = "[?] 피해를 줍니다";
		attack=1;
		
		enhName = name+"(강화)";
		enhDescription = "[?] 피해를 줍니다";		
		enhAddAttack=1;
	}	
	
	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		int damage = Roll.roll6();
		enemy.subtractHp(damage);
		printDamage(damage);
	}
}
//알수없음 : 1~6까지의 랜덤한 피해를 준다