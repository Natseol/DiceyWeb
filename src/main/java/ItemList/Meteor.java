package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Meteor extends Item{

	public Meteor()	{
		name = "운석낙하";
		description = "[20] 의 화염피해를 줍니다 발화+3";
		attack=20;
		fireStack=3;
		count=20;
		
		enhName = name+"(강화)";
		enhDescription = "[25] 의 화염피해를 줍니다 발화+3";		
		enhAddAttack=5;
	}	
}