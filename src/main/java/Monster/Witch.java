package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Witch extends Enemy {

	public Witch() {
		name = "마녀";
		description = " ";
		grade = "매우 어려움";
		maxHp = 56;	
		hp = maxHp;
		diceQuantity = 4;
		diceMin=true;
		inventory = new Item[] {new SpareWitch(), new Wand(), new Meteor()};		
	}
}