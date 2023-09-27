package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class SwordMan extends Enemy{

	public SwordMan() {
		name = "검의 달인";
		description = "전사가 아닙니다";
		grade = "어려움";
		maxHp = 42;	
		hp = maxHp;
		diceQuantity = 2;
		inventory = new Item[] {new FireSword(), new BroadSword()};		
	}
}
