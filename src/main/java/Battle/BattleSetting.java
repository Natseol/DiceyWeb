package Battle;

import Character.*;
import Item.*;

public class BattleSetting extends TurnDice {
	
	int[][] itemState=new int[6][4];//0:times 1:count 2:needDice 3:use
	
	public int getTurnTimes(int idx) {
		return itemState[idx][0];
	}
	public void setTurnTimes(int idx, int num) {
		itemState[idx][0]=num;
	}
	
	public int getTurnCount(int idx) {
		return itemState[idx][1];
	}
	public void setTurnCount(int idx, int num) {
		itemState[idx][1]=num;
	}
	
	public int getNeedDice(int idx) {
		return itemState[idx][2];
	}
	public void setNeedDice(int idx, int num) {
		itemState[idx][2]=num;
	}
	
	public int getTurnUse(int idx) {
		return itemState[idx][3];
	}
	public void setTurnUse(int idx, int num) {
		itemState[idx][3]=num;
	}
	
	public void resetTimes(Item[] item) {
		for (int i=0;i<item.length;i++) {
			this.itemState[i][0]=item[i].getTimes();
		}
	}
	
	public void resetCount(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			this.itemState[i][1]=status.getInventory(i).getCount();
		}
	}
	
	public void resetNeedDice(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			this.itemState[i][2]=0;
		}
	}
	
	public void resetUse(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			this.itemState[i][3]=0;
		}
	}
}
