package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Hothead extends Enemy{
	
	public Hothead() {
		name = "불머리";
		description = "파란자켓과 우산을 들고 있습니다";
		grade = "일반";
		maxHp = 20;	
		hp = maxHp;
		diceQuantity = 2;
		inventory = new Item[] {new FireBall()};		
	}
}