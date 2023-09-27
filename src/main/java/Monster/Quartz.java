package Monster;

import Battle.*;
import Character.*;
import Item.Item;
import ItemList.*;

public class Quartz extends Enemy{

	public Quartz() {
		name = "수정구슬";
		description = "불과얼음";
		grade = "일반";
		maxHp = 30;	
		hp = maxHp;
		diceQuantity = 1;
		inventory = new Item[] {new IceShards(), new FireBall()};		
	}
}
