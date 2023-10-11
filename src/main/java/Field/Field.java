package Field;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import Character.*;
import Main.Color;
import Main.Input;
import Main.Script;
import db.ItemVO;

public class Field implements Serializable{
	Store store=new Store();
	Script script = new Script();
	
	int storeCount = 1;
	int healCount = 1;
	int forgeCount = 0;
	
	public void createField() {
		storeCount = 1;
		healCount = 1;
		forgeCount = 0;
		store.resetStore();
	}	
	
	public int getHealCount() {
		return healCount;
	}	
	public void setHealCount(int num) {
		healCount = num;
	}
	
	public int getStoreCount() {
		return storeCount;
	}	
	public void setStoreCount(int num) {
		storeCount = num;
	}
	
	public int getForgeCount() {
		return forgeCount;
	}	
	public void setForgeCount(int num) {
		forgeCount = num;
	}
	
	public Store getStore() {
		return store;
	}
}


