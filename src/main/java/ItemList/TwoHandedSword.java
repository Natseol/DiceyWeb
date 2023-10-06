package ItemList;

import Battle.*;
import Character.*;
import Item.Item;

public class TwoHandedSword extends Item{

	public TwoHandedSword()	{
		name = "양손검";
		description = "눈금의 합만큼 피해를 줍니다 (주사위 2개 필요)";
		attack=1;
		accumulation=0;
		needDice=1;
		
		enhName = name+"(강화)";
		enhDescription = "눈금의 합만큼 피해를 줍니다 12일때 전기피해 (주사위 2개 필요)";		
		enhActiveLimit="6";
		enhElecStack=1;
	}		
	
	@Override
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		if (my.getNeedDiceState(idx)>0) {
			if (player.getIsUseSkill()) {
				my.getTurnScript().add(" * 분노 스킬로 두번 발동합니다 *<br>");
				enemy.subtractHp(dice+accumulation);			
				printDamage(dice+accumulation);
				player.setIsUseSkill(false);
			}
			enemy.subtractHp(dice+accumulation);			
			printDamage(dice+accumulation);
			accumulation=0;
			my.setNeedDiceState(idx, 0);
			my.setTimesState(idx, 0);
		}
		else {
			accumulation += dice;
			my.setNeedDiceState(idx, accumulation);
			my.setTimesState(idx, 1);
		}			
	}
}
