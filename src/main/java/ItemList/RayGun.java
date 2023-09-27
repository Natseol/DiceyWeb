package ItemList;

import Battle.TurnInfo;
import Character.Status;
import Item.Item;

public class RayGun extends Item{

	public RayGun() {
		name = "레이건";
		description = "[3] 의 피해를 줍니다 (최소 3) (턴당 3번) ";
		limit="3 4 5 6";
		attack=3;
		times=3;
		
		enhName = name+"(강화)";
		enhDescription = "[4] 의 피해를 줍니다 (최소 3) (턴당 3번) ";		
		enhAddAttack=1;
	}	
}
