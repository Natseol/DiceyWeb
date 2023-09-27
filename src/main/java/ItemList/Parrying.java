package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Parrying extends Item{

	public Parrying()	{
		name = "패링";
		description = "ㅁ 의 피해를 줍니다. 방어력이 있으면 +1 의 피해 (최대 3)";
		limit="1 2 3";
		attack=-1;
		addAttack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 피해를 줍니다. 방어력이 있으면 +3 의 피해 (최대 3)";		
		enhAddAttack=3;
	}

	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		if (player.getDef()>0) {
			enemy.subtractHp(dice+addAttack);
			printDamage(dice+addAttack);
		} else {
			enemy.subtractHp(dice);
			printDamage(dice);
		}
			
	}
}