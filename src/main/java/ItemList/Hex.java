package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class Hex extends Item{

	public Hex()	{
		name = "헥스";
		description = "발화+1 빙결+1 마비+1";
		fireStack=1;
		iceStack=1;
		elecStack=1;
		count=7;
		
		enhName = name+"(강화)";
		enhDescription = "발화+2 빙결+2 마비+2";
		enhFireStack=2;
		enhIceStack=2;
		enhElecStack=2;
	}	
}