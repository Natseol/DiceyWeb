package Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Character.*;
import Dice.Roll;
import Field.*;
import Item.Item;
import ItemList.*;
import Main.Color;
import Main.Script;

public class TurnInfo extends BattleSetting{
	
	Scanner scanner = new Scanner(System.in);
	Item[] turnItem;
	Script script = new Script();
	List<String> turnScript = new ArrayList<>();
	boolean isTurn;
	boolean isEndTurn;

	public boolean getIsTurn() {
		return isTurn;
	}

	public void changeTurn() {
		if (!isTurn) {
			isTurn = true;
		} else {
			isTurn = false;
		}
	}

	public TurnInfo() {}
	
 	public TurnInfo(Status status) {}
	
	public Item[] getItem() {
		return turnItem;
	}
	public Item getItem(int idx) {
		return turnItem[idx];
	}
	public void setItem(int idx, Item item) {
		turnItem[idx] = item;
	}

	public List<String> getTurnScript() {
		return turnScript;
	}
	
	public void resetTurnScript() {
		turnScript.clear();
	}
	
	public void resetStrb() {
		script.getStrb().setLength(0);
	}
	
 	public void checkPoison(Status player) {
		if (player.getCondition(3)>0) {
			turnScript.add(player.damagedPoisonStr());
		}//상태이상	중독
 	}
 	
 	public void checkIce(Status player) {
		while (player.getCondition(1)>0) {
			if (player.getCondition(1)>0) {
				player.damagedIceList(this);
				script.printDamagedIce();
			}//상태이상 빙결
		}
		turnScript.add(script.getStrb().toString());
 	}
}

