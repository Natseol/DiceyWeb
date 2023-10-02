package Battle;

import Character.*;
import Item.*;

public class BattleSetting extends TurnDice {
	
	int[] timesState=new int[5];
	int[] countState=new int[5];
	int[] needDiceState=new int[5];
	int[] useState=new int[5];
		
	public int[] getTimesState() {
		return timesState;
	}
	public int[] getCountState() {
		return countState;
	}
	public int[] getNeedDiceState() {
		return needDiceState;
	}
	public int[] getUseState() {
		return useState;
	}
	
	public int getTimesState(int idx) {
		return timesState[idx];
	}
	public void setTimesState(int idx, int num) {
		timesState[idx]=num;
	}
	
	public int getCountState(int idx) {
		return countState[idx];
	}
	public void setCountState(int idx, int num) {
		countState[idx]=num;
	}
	
	public int getNeedDiceState(int idx) {
		return needDiceState[idx];
	}
	public void setNeedDiceState(int idx, int num) {
		needDiceState[idx]=num;
	}
	
	public int getUseState(int idx) {
		return useState[idx];
	}
	public void setUseState(int idx, int num) {
		useState[idx]=num;
	}
	
	public void resetTimes(Item[] item) {
		for (int i=0;i<item.length;i++) {
			timesState[i]=item[i].getTimes();
		}
	}
	
	public void resetCount(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			countState[i]=status.getInventory(i).getCount();
		}
	}
	
	public void resetNeedDice(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			needDiceState[i]=0;
		}
	}
	
	public void resetUse(Status status) {
		for (int i=0;i<status.getInventory().length;i++) {
			useState[i]=0;
		}
	}
}
