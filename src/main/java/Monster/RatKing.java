package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class RatKing extends Enemy{

	public RatKing() {
		name = "쥐왕";
		description = "독에 강합니다";
		grade = "어려움";
		maxHp = 52;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Rat(), new Rat(), new Rat(), new Rat(), new Rat(), new Rat()};		
	}
}