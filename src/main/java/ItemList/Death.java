package ItemList;

import Battle.*;
import Character.*;
import Item.Item;
import Main.Color;

public class Death extends Item{

	int phase;
	
	public Death()	{
		name = "종말";
		description = "끝이 오고 있습니다";
		attack=237;
		count=10;
	}	
	
	@Override
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		if (my.getTurnCount(idx)>0) {
			my.setTurnCount(idx, my.getTurnCount(idx)-1);
			System.out.println(Color.BBLACK+"점점 어두워집니다"+Color.RESET);
			my.setTurnTimes(idx, 0);
		}
		else {
			printDamage(237);
			enemy.subtractHp(237);
			my.setTurnTimes(idx, 0);
		}
		if (phase==0&&player.getHp()<40) {
			phase++;
			player.addHp(24);
			player.setDiceQuantity(5);
			player.setInventory(3, new EnhancedScythe());
			player.setInventory(2, new EnhancedBlood());
			player.setInventory(1, new UnholySword());
			my.setItem(3, new EnhancedScythe());
			my.setItem(2, new EnhancedBlood());
			my.setItem(1, new UnholySword());			
			System.out.println(Color.BCYAN+"\n\n  ** 어둠이 짙어집니다 **"+Color.RESET);
			System.out.println(" "+Color.B_BGREEN+" 24 "+Color.RESET+" 의 체력을 회복했습니다");
			System.out.println("  강화된 장비를 사용합니다");
			System.out.println("  추가 주사위를 획득합니다");
			System.out.println();
			System.out.println("------------------------------------------------");
			System.out.println("------------------- Phase 2 --------------------");
			System.out.println("------------------------------------------------");


		}
	}
}