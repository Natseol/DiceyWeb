package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Bear extends Enemy{

	public Bear() {
		name = "곰";
		description = "야생 그 자체";
		grade = "어려움";
		maxHp = 56;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Bash(), new Overwhelming()};		
	}
}
