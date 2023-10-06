package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class Overwhelming extends Item{
		
	public Overwhelming()	{
		name = "제압";
		description = "[1~2]의 사용할 때 마다 누적되는 피해를 줍니다.(턴당 3번)";
		attack=1;
		times=2;
		
		enhName = name+"(강화)";
		enhDescription = "[1~2]의 사용할 때 마다 누적되는 피해를 줍니다.(턴당 3번)";		
		enhTimes=3;
	}
	
	@Override
	public void actionAttack(Status player, Status enemy, int dice) {
		int damage=(int)(Math.random()*2)+1;
		accumulation+=damage;
		enemy.subtractHp(accumulation);
		printDamage(accumulation);
	}
}