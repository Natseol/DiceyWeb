package Battle;

import java.util.Arrays;
import java.util.Scanner;

import Character.*;
import Dice.Roll;
import Main.*;
import Field.*;
import Item.Item;
import ItemList.*;

public class MyTurn extends TurnInfo{
	
	boolean isUseSkill;
	
 	public MyTurn(Player player) {
 		super(player);
		resetDiceList(player);
		isUseSkill=false;
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
 		isUseSkill=false;
 		resetTimes(player.getInventory());
 		resetNeedDice(player); 	
 		resetTurnScript();
 		resetStrb();
 		checkPoison(player);        	
    	checkIce(player);
 	}
 	
 	public boolean getIsUseSkill() {
 		return isUseSkill;
 	}
 	public void setIsUseSkill(boolean useSkill) {
 		isUseSkill = useSkill;
 	}
 	
 	public void doMyTurnLoop(Player player, Enemy enemy, EnemyTurn enemyTurn, int idxDice, int idxInven) {
 		turnScript.clear();

 		if (idxDice==77) {
 			Skill.useSkill(player, enemy, this);
 			return;
 		}

 		if (player.getCondition(0)>0) {
 			turnScript.add(player.damagedFireStr());	
 		}//상태이상	발화					
 		if (player.getHp()<1||enemy.getHp()<1) return;
 		//죽었는지 확인

 		int numDice=getDiceList(idxDice);

 		if (player.getCondition(2)>0) {
 			if (player.damagedParalysisList(this, idxDice)) {
 				turnScript.add(" * 충격을 받았습니다. 주사위를 놓칩니다 * <br>");
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
 		if (getIsUseSkill()) {
 			getItem(idxInven).action(player, enemy, numDice, this, idxInven);
 			turnScript.add(getItem(idxInven).getStrb().toString());
 			getItem(idxInven).resetStrb();
 			setTimesState(idxInven, getTimesState(idxInven));
 			turnScript.add(" * 한번 더 발동합니다 *<br>");					
 			setIsUseSkill(false);
 		}
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
