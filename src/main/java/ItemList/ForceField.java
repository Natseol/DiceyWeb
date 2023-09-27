package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class ForceField extends Item{
		
	public ForceField()	{
		name = "역장";
		description = "[1]+("+accmulation+") 의 누적되는 방어력을 얻습니다. (홀수)";
		limit="-1";
		defence=1;
		
		enhName = name+"(강화)";
		enhDescription = "[1]+("+accmulation+") 의 누적되는 방어력을 얻습니다.";
		enhLimit="";
	}	
	
	@Override
	public void actionDefence(Status player, int dice) {
		accmulation++;
		player.setDef(player.getDef()+accmulation);		
		ItemScript.printGainDefence(accmulation);
		description = "[1]+("+accmulation+") 의 누적되는 방어력을 얻습니다.";
	}
}