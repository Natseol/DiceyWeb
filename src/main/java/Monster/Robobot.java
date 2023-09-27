package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Robobot extends Enemy{
	
	public Robobot() {
		name = "로보봇";
		description = "피-융 피-융 피-융";
		grade = "일반";
		maxHp = 18;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new RayGun()};		
	}
}