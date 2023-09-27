package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class VampireElite extends Enemy {

	public VampireElite() {
		name = "부활한 뱀파이어";
		description = "다시 살아돌아왔습니다";
		grade = "보스";
		maxHp = 72;	
		hp = maxHp;
		diceQuantity = 4;
		diceMin=true;
		inventory = new Item[] {new Death(), new Heal(), new BloodSucking(), new Scythe()};		
	}
}