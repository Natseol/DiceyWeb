package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Fighter extends Enemy{

	public Fighter() {
		name = "투사";
		description = "전사가 아닙니다";
		grade = "일반";
		maxHp = 40;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Glove(), new Glove(), new Glove()};		
	}
}
