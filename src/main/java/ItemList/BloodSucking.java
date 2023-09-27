package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class BloodSucking extends Item{

	public BloodSucking()	{
		name = "흡혈";
		description = "[1] 의 피해를 주고 눈금의 반만큼 체력을 흡수합니다.";
		attack=1;
		recovery=-1;
	}
	
	@Override
	public void actionRecovery(Status player, int dice) {
		player.addHp((dice+1)/2);
		printRecovery((dice+1)/2);
	}
}

