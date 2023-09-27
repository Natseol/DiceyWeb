package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class GatekeeperElite extends Enemy {

	public GatekeeperElite() {
		name = "정예 문지기";
		description = "정예 공방일체";
		grade = "매우 어려움";
		maxHp = 66;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new FrontLine(), new Charge(), new ForceField()};		
	}
}
