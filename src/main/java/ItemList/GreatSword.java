package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class GreatSword extends Item{

	public GreatSword()	{
		name = "대검";
		description = "ㅁx3 만큼 피해를 줍니다 (최대 5) (전투당 1번)";
		limit="1 2 3 4 5";
		attack=-3;
		use=true;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁx3 만큼 전기 피해를 줍니다 (최대 5) (전투당 1번)";	
		enhElecStack=1;
	}	
}

