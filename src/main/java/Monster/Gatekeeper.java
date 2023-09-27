package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Gatekeeper extends Enemy {

	public Gatekeeper() {
		name = "문지기";
		description = "공방일체";
		grade = "일반";
		maxHp = 48;	
		hp = maxHp;
		diceQuantity = 3;
		diceMin=true;
		inventory = new Item[] {new Shield(), new Charge(), new KiteShield()};		
	}
}
