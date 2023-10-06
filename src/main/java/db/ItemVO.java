package db;

import java.io.Serializable;

public class ItemVO implements Serializable{
	protected int id;
		
	protected String name;
	protected String description;
	
	protected int attack;	
	protected int addAttack;
	protected int count;
	protected String limit="";
	protected int times=1;
	protected boolean use;
	protected int needDice;
	
	protected String activeLimit="";	
	protected int fireStack;
	protected int iceStack;
	protected int elecStack;
	protected int poisonStack;	
	protected int recovery;
	protected int defence;
	protected int damage;
	protected String newDice="";
		
	protected int accumulation;

	public ItemVO(String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice) {
		this.name = name;
		this.description = description;
		this.attack = attack;
		this.addAttack = addAttack;
		this.count = count;
		this.limit = limit;
		this.times = times;
		
		if (use==0) {this.use = false;}
		else {this.use = true;}
		
		this.needDice = needDice;
		this.activeLimit = activeLimit;
		this.fireStack = fireStack;
		this.iceStack = iceStack;
		this.elecStack = elecStack;
		this.poisonStack = poisonStack;
		this.recovery = recovery;
		this.defence = defence;
		this.damage = damage;
		this.newDice = newDice;
	}
	
	public ItemVO(int id, String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice, int accumulation) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.attack = attack;
		this.addAttack = addAttack;
		this.count = count;
		this.limit = limit;
		this.times = times;
		
		if (use==0) {this.use = false;}
		else {this.use = true;}
		
		this.needDice = needDice;
		this.activeLimit = activeLimit;
		this.fireStack = fireStack;
		this.iceStack = iceStack;
		this.elecStack = elecStack;
		this.poisonStack = poisonStack;
		this.recovery = recovery;
		this.defence = defence;
		this.damage = damage;
		this.newDice = newDice;
		this.accumulation = accumulation;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getAttack() {
		return attack;
	}

	public int getAddAttack() {
		return addAttack;
	}

	public int getCount() {
		return count;
	}

	public String getLimit() {
		return limit;
	}

	public int getTimes() {
		return times;
	}

	public boolean isUse() {
		return use;
	}

	public int getNeedDice() {
		return needDice;
	}

	public String getActiveLimit() {
		return activeLimit;
	}

	public int getFireStack() {
		return fireStack;
	}

	public int getIceStack() {
		return iceStack;
	}

	public int getElecStack() {
		return elecStack;
	}

	public int getPoisonStack() {
		return poisonStack;
	}

	public int getRecovery() {
		return recovery;
	}

	public int getDefence() {
		return defence;
	}

	public int getDamage() {
		return damage;
	}

	public String getNewDice() {
		return newDice;
	}

	public int getAccumulation() {
		return accumulation;
	}
	
}
