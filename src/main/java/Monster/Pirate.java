package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Pirate extends Enemy{

	public Pirate() {
		name = "해적";
		description = "약탈한 무기를 들고 있습니다";
		grade = "일반";
		maxHp = 34;	
		hp = maxHp;
		diceQuantity = 2;
		inventory = new Item[] {new Staff(), new Fist()};		
	}
}
