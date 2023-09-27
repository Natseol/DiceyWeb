package ItemList;

import Battle.MyTurn;
import Battle.TurnInfo;
import Character.Enemy;
import Character.Player;
import Character.Status;
import Item.Item;

public class IceShards extends Item{

	public IceShards()	{
		name = "얼음파편";		
		description="ㅁ 의 얼음 피해를 줍니다 (홀수)";
		limit="-1";
		attack=-1;
		iceStack=1;
		
		enhName = name+"(강화)";
		enhDescription = "ㅁ 의 얼음 피해를 줍니다";
		enhLimit="";
	}	
}
