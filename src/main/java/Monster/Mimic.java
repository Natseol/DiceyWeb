package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Mimic extends Enemy{

	public Mimic() {
		name = "미믹";
		description = "알 수 없음";
		grade = "어려움";
		maxHp = 58;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Unknown(), new Box(), new Unknown()};		
	}
}