package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Skeleton extends Enemy{

	public Skeleton() {
		name = "해골";
		description = "생각보다 강합니다";
		grade = "어려움";
		maxHp = 52;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new TwoHandedSword(), new Shield()};		
	}
}
