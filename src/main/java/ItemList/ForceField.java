package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Item.ItemScript;

public class ForceField extends Item{
		
	public ForceField()	{
		name = "역장";
		description = "[1] 의 사용할때 마다 누적되는 방어력을 얻습니다. (홀수)";
		limit="-1";
		defence=1;
		
		enhName = name+"(강화)";
		enhDescription = "[1] 의 사용할때 마다 누적되는 방어력을 얻습니다.";
		enhLimit="";
	}	
	
	@Override
	public void actionDefence(Status player, int dice) {
		accumulation++;
		player.setDef(player.getDef()+accumulation);		
		ItemScript.printGainDefence(accumulation);
	}
}