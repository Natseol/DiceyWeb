package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class FireBall extends Item{

	public FireBall()	{
		name = "화염구";		
		description="ㅁ 의 화염 피해를 줍니다 (짝수)";
		limit="-2";
		attack=-1;
		fireStack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 화염+2 피해를 줍니다 (짝수)";		
		enhFireStack=2;
	}	
}