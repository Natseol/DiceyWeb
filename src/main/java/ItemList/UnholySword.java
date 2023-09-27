package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Dice.Roll;
import Item.Item;

public class UnholySword extends Item{
	
	public UnholySword()	{
		name = "타락한검";
		description = "ㅁ의 피해를 주고 무작위 상태이상을 겁니다. 1의 피해를 입습니다";
		attack=-1;
		damage=1;
	}	
	
	@Override
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		int effect = Roll.random(4)-1;
		my.setTurnTimes(idx, 0);
		switch (effect) {
		case 0:
			enemy.subtractHp(dice);
			printDamageFire(dice);
			enemy.setCondition(0,enemy.getCondition(0)+1);
			break;
		case 1:
			enemy.subtractHp(dice);
			printDamageIce(dice);
			enemy.setCondition(1,enemy.getCondition(1)+1);
			break;
		case 2:
			enemy.subtractHp(dice);
			printDamageShock(dice);
			enemy.setCondition(2,enemy.getCondition(2)+1);
			break;
		case 3:
			enemy.subtractHp(dice);
			printDamagePoison(dice);
			enemy.setCondition(3,enemy.getCondition(3)+1);
			break;
		}
		player.subtractHp(1);
		printTakedDamage(1);
	}
}