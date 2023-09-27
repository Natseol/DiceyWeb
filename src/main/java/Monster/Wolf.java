package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Wolf extends Enemy{

	public Wolf() {
		name = "늑대";
		description = "발톱을 숨기고 있습니다";
		grade = "어려움";
		maxHp = 51;	
		hp = maxHp;
		diceQuantity = 3;
		diceMin=true;
		inventory = new Item[] {new Claw(), new Claw(), new Infection()};		
	}
}
