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
	
//	protected int accumulation;
			
	protected String enhName;
	protected String enhDescription;
	
	protected int enhAttack;	
	protected int enhAddAttack;
	protected int enhCount;
	protected String enhLimit="";
	protected int enhTimes;
	protected boolean enhUse;
	
	protected int enhNeedDice;
	protected String enhActiveLimit="";
	protected int enhFireStack;
	protected int enhIceStack;
	protected int enhElecStack;
	protected int enhPoisonStack;
	protected int enhRecovery;
	protected int enhDefence;
	protected int enhDamage;
	protected String enhNewDice="";

	public ItemVO(String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice, int accmulation,
			String enhName, String enhDescription, int enhAttack,	
			int enhAddAttack, int enhCount, String enhLimit, int enhTimes, int enhUse, int enhNeedDice,
			String enhActiveLimit, int enhFireStack, int enhIceStack,
			int enhElecStack, int enhPoisonStack, int enhRecovery,
			int enhDefence, int enhDamage, String enhNewDice) {
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
		
		this.enhName = enhName;
		this.enhDescription = enhDescription;
		this.enhAttack = enhAttack;
		this.enhAddAttack = enhAddAttack;
		this.enhCount = enhCount;
		this.enhLimit = enhLimit;
		this.enhTimes = enhTimes;
		
		if (enhUse==0) {this.enhUse = false;}
		else {this.enhUse = true;}
		
		this.enhNeedDice = enhNeedDice;
		this.enhActiveLimit = enhActiveLimit;
		this.enhFireStack = enhFireStack;
		this.enhIceStack = enhIceStack;
		this.enhElecStack = enhElecStack;
		this.enhPoisonStack = enhPoisonStack;
		this.enhRecovery = enhRecovery;
		this.enhDefence = enhDefence;
		this.enhDamage = enhDamage;
		this.enhNewDice = enhNewDice;
	}
	
	public ItemVO(int id, String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice,
			String enhName, String enhDescription, int enhAttack,	
			int enhAddAttack, int enhCount, String enhLimit, int enhTimes, int enhUse, int enhNeedDice,
			String enhActiveLimit, int enhFireStack, int enhIceStack,
			int enhElecStack, int enhPoisonStack, int enhRecovery,
			int enhDefence, int enhDamage, String enhNewDice) {
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
		
		this.enhName = enhName;
		this.enhDescription = enhDescription;
		this.enhAttack = enhAttack;
		this.enhAddAttack = enhAddAttack;
		this.enhCount = enhCount;
		this.enhLimit = enhLimit;
		this.enhTimes = enhTimes;
		
		if (enhUse==0) {this.enhUse = false;}
		else {this.enhUse = true;}
		
		this.enhNeedDice = enhNeedDice;
		this.enhActiveLimit = enhActiveLimit;
		this.enhFireStack = enhFireStack;
		this.enhIceStack = enhIceStack;
		this.enhElecStack = enhElecStack;
		this.enhPoisonStack = enhPoisonStack;
		this.enhRecovery = enhRecovery;
		this.enhDefence = enhDefence;
		this.enhDamage = enhDamage;
		this.enhNewDice = enhNewDice;
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

	public String getEnhName() {
		return enhName;
	}

	public String getEnhDescription() {
		return enhDescription;
	}

	public int getEnhAttack() {
		return enhAttack;
	}

	public int getEnhAddAttack() {
		return enhAddAttack;
	}

	public int getEnhCount() {
		return enhCount;
	}

	public String getEnhLimit() {
		return enhLimit;
	}

	public int getEnhTimes() {
		return enhTimes;
	}

	public boolean isEnhUse() {
		return enhUse;
	}

	public String getEnhActiveLimit() {
		return enhActiveLimit;
	}

	public int getEnhFireStack() {
		return enhFireStack;
	}

	public int getEnhIceStack() {
		return enhIceStack;
	}

	public int getEnhElecStack() {
		return enhElecStack;
	}

	public int getEnhPoisonStack() {
		return enhPoisonStack;
	}

	public int getEnhRecovery() {
		return enhRecovery;
	}

	public int getEnhDefence() {
		return enhDefence;
	}

	public int getEnhDamage() {
		return enhDamage;
	}

	public String getEnhNewDice() {
		return enhNewDice;
	}

	public int getEnhNeedDice() {
		return enhNeedDice;
	}

//	public int getAccumulation() {
//		return accumulation;
//	}	
	
}
