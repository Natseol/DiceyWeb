package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class Capacitor extends Item{

	public Capacitor()	{
		name = "축전기";
		description="[2] 의 전기 피해를 줍니다 (재사용 가능)";
		attack=2;
		count=5;
		elecStack=1;
		times=-1;
		
		enhName = name+"(강화)";
		enhDescription = "[3] 의 전기 피해를 줍니다 (재사용 가능)";		
		enhAddAttack=1;
	}	
}