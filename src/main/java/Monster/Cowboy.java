package Monster;

import Character.*;
import Item.Item;
import ItemList.*;

public class Cowboy extends Enemy{

	public Cowboy() {
		name = "카우보이";
		description = "석양이 진다!!!!!!!!!!!!";
		grade = "어려움";
		maxHp = 46;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Revolver(), new Precision()};		
	}
}