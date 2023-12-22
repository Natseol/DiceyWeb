package Battle;

import Character.*;
import ItemList.*;

public class MyTurn extends TurnInfo{

	public MyTurn(Player player) {
		super(player);
		resetDiceList(player);
		if (player.getInventory()!=null) {
			turnItem= player.getInventory().clone();
			resetTimes(turnItem);
			resetCount(player);
			resetNeedDice(player);
			resetUse(player);
			isTurn=true;
		}
	}

	public void startTurn(Player player) {
		turnItem= player.getInventory().clone();	
		resetDiceList(player);
		resetTimes(player.getInventory());
		resetNeedDice(player); 	
		resetTurnScript();
		resetStrb();
		checkPoison(player);        	
		checkIce(player);
	}

	public void doMyTurnLoop(Player player, Enemy enemy, EnemyTurn enemyTurn, int idxDice, int idxInven) {
		turnScript.clear();

		if (player.getCondition(0)>0) {
			turnScript.add(player.damagedFireStr());	
		}//상태이상	발화					
		if (player.getHp()<1||enemy.getHp()<1) return;
		//죽었는지 확인

		int numDice=getDiceList(idxDice);

		if (player.getCondition(2)>0) {
			if (player.damagedParalysisList(this, idxDice)) {
				turnScript.add("&nbsp* 충격을 받았습니다. 주사위를 놓칩니다 *&nbsp<br>");
				return;
			}
		}//상태이상 마비

		if (getItem(idxInven).actionLimit(numDice)==false) {
			turnScript.add(script.printCheckTrueStr());
			return;
		}//장비 조건 확인

		if (getUseState(idxInven)==1) {
			turnScript.add(script.printCheckTrueStr());
			return;
		}

		getDiceList().remove(idxDice);
		getItem(idxInven).action(player, enemy, numDice, this, idxInven);
		turnScript.add(getItem(idxInven).getStrb().toString());
		getItem(idxInven).resetStrb();
		//장비 발동
		if (player.getHp()<1||enemy.getHp()<1) return;
		//죽었는지 확인

		if (getTimesState(idxInven)==0) {
			setItem(idxInven, new Nothing());
		}//횟수0 아이템은 빈슬롯으로 변경
	}
}
