package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Smile extends Enemy{
	
	public Smile() {
		name = "스마일";
		description = "후루-룹 후-루루-룹";
		grade = "일반";
		maxHp = 22;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new PoisonCloud()};		
	}
}