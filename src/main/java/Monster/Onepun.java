package Monster;

import Battle.EnemyTurn;
import Character.*;
import Item.Item;
import ItemList.*;

public class Onepun extends Enemy{
	
	public Onepun() {
		name = "죽창맨";
		description = "매일 팔굽혀펴기, 윗몸 일으키기, 스쿼트를 101번씩 함";
		grade = "일반";
		maxHp = 38;	
		hp = maxHp;
		diceQuantity = 3;
		inventory = new Item[] {new Spear(), new Spear(), new Spear()};		
	}
}
