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
	
	public boolean isTurn() {
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
	
}

