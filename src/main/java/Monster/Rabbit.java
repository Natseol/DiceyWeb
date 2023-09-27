package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Rabbit extends Enemy{

	public Rabbit() {
		name = "토끼";
		description = "농사꾼";
		grade = "일반";
		maxHp = 20;	
		hp = maxHp;
		diceQuantity = 1;
		inventory = new Item[] {new Shovel()};		
	}
}