package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Valkyrie extends Enemy{

	public Valkyrie() {
		name = "발키리";
		description = "오딘의 편에서 싸울 용사를 찾고 있습니다";
		grade = "어려움";
		maxHp = 54;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Icicle(), new IcePillar(), new AbsoluteZero()};		
	}
}