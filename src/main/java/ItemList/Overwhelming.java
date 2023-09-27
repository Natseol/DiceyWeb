package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class Overwhelming extends Item{
		
	public Overwhelming()	{
		name = "제압";
		description = "[1~2]+("+accmulation+") 의 누적되는 피해를 줍니다.(턴당 2번)";
		attack=1;
		times=2;
		
		enhName = name+"(강화)";
		enhDescription = "[1~2]+("+accmulation+") 의 누적되는 피해를 줍니다.(턴당 3번)";		
		enhTimes=3;
	}
	
	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		int damage=(int)(Math.random()*2)+1;
		accmulation+=damage;
		enemy.subtractHp(accmulation);
		printDamage(accmulation);
		description = "[1~2]+("+accmulation+") 의 누적되는 피해를 줍니다.(턴당 2번)";
	}
}