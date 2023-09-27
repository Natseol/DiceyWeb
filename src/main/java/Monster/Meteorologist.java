package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Meteorologist extends Enemy{

	public Meteorologist() {
		name = "기상학자";
		description = "날씨를 변화시킵니다";
		grade = "어려움";
		maxHp = 53;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new LightingRod(), new LightingRod(), new Storm()};		
	}
}
