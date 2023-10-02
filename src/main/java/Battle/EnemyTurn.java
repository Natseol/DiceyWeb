package Battle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import Character.*;
import Dice.Roll;
import Item.Item;
import Item.ItemScript;
import ItemList.*;
import Main.Script;

public class EnemyTurn extends TurnInfo{
		
 	public EnemyTurn(Status enemy) {
 		super(enemy);
 		if (enemy.getInventory()!=null) {
	 		turnItem= enemy.getInventory().clone();		
			itemState=new int[enemy.getInventory().length][4];
			resetDiceList(enemy);
			resetTimes(turnItem);
			resetCount(enemy);
			resetNeedDice(enemy);
 		}
	}
 	
 	public void startTurn(Status enemy) {
		turnItem= enemy.getInventory().clone();		
 		resetDiceList(enemy);
 		resetTimes(turnItem);
 		resetNeedDice(enemy);
 		resetTurnScript();
 		resetStrb();
 	}
	
	public void doEnemyTurnLoop(Player player, Enemy enemy, MyTurn myTurn) {
		
//		if (enemy.getCondition(3)>0) {
//			enemy.damagedPoison();	
//		}//상태이상	중독
//		if (player.getHp()<1||enemy.getHp()<1) return;
//		//죽었는지 확인
		
//		resetTimes(enemy.getInventory());		
		
//		while (enemy.getCondition(1)>0) {
//			if (enemy.getCondition(1)>0) {
//				enemy.damagedIceList(this);
//				script.selectDiceList(this);	
//				script.printDamagedIce();
//			}//상태이상 빙결
//		}
		
		if (getDiceList().size()>0) {
			int enemyItemNum=0;
			
			int maxMin = Collections.max(getDiceList());
			int indexDice = getDiceList().indexOf(maxMin);				 

			if (enemy.getDiceMin()) {
				maxMin = Collections.min(getDiceList());
				indexDice = getDiceList().indexOf(maxMin);
			}
				
//				if (enemy.getCondition(0)>0) {
//					enemy.damagedFire();	
//				}//상태이상	발화
//				if (player.getHp()<1||enemy.getHp()<1) break;
//				//죽었는지 확인

//				script.printSelectedDice(getDiceList(indexDice));
//				if (enemy.getCondition(2)>0) {
//					if (enemy.damagedParalysisList(this, indexDice)) {
//						continue;
//					}
//				}//상태이상 마비

//				if (enemy.getCondition(0)>0) {
//					enemy.damagedFire();	
//				}//상태이상	발화
//				if (player.getHp()<1||enemy.getHp()<1) break;
//				//죽었는지 확인
				
			for(int j=0; j<getItem().length;j++) {
				if (getItem(enemyItemNum).getName().equals(new Nothing().getName())
						||!getItem(enemyItemNum).actionLimit(getDiceList(indexDice))) {
					enemyItemNum++;
					if (enemyItemNum==getItem().length) {
						getDiceList().remove(indexDice);
						ItemScript.printIncorrectDice();
						turnScript.add(ItemScript.getStrb().toString());
						ItemScript.resetStrb();
						break;
					}
					continue;
				}

				script.printSelectedDiceUse(enemyItemNum, enemy);
				getItem(j).action
				(enemy, player, maxMin, this, enemyItemNum);
				turnScript.add(getItem(j).getStrb().toString());
				getItem(j).resetStrb();
				getDiceList().remove(indexDice);

				if (getTurnTimes(enemyItemNum)==0) {
					setItem(enemyItemNum, new Nothing());
				}//횟수0 아이템은 빈슬롯으로 변경
				break;
			}
			if (player.getHp()<1||enemy.getHp()<1) return;

			int count = 0;
			for (int i = 0 ; i < getItem().length; i++) {
				if (getItem(i).getName().equals(new Nothing().getName())) {
					count++;
				}
			}
			
			if (count == getItem().length) {
				System.out.println("아이템으로 턴교체");
				myTurn.changeTurn();
				changeTurn();
				return;//전부 빈슬롯인지 확인
			}
		} else {
			System.out.println("다이스로 턴교체");
			myTurn.changeTurn();
			changeTurn();
		}
	}
}



