package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Caster extends Enemy{

	public Caster() {
		name = "마술사";
		description = "견습생";
		grade = "일반";
		maxHp = 28;	
		hp = maxHp;
		diceQuantity = 1;
		inventory = new Item[] {new MagicMissile(), new Shield()};		
	}
}
