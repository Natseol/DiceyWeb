package ItemList;

import Item.Item;

public class LongBow extends Item{
	
	public LongBow()	{
		name = "장궁";
		description="[12] 의 전기 피해를 줍니다";
		attack=12;
		elecStack=1;
		count=15;
		
		enhName = name+"(강화)";
		enhDescription = "[14] 의 전기 피해를 줍니다";		
		enhAddAttack=2;
	}	
}
