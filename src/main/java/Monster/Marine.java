package Monster;

import Battle.EnemyTurn;
import Character.*;
import Item.Item;
import ItemList.*;

public class Marine extends Enemy{
	
	public Marine() {
		name = "우주 해병";
		description = "Go! Go! Go!";
		grade = "일반";
		maxHp = 24;	
		hp = maxHp;
		diceQuantity = 2;
		inventory = new Item[] {new Cannon()};		
	}
}