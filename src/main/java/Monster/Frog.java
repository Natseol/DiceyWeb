package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Frog extends Enemy{

	public Frog() {
		name = "개구리";
		description = "개구리";
		grade = "일반";
		maxHp = 22;	
		hp = maxHp;
		diceQuantity = 2;
		inventory = new Item[] {new Sword(), new MedicKit()};		
	}
}