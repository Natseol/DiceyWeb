package Item;

import Battle.*;
import Character.*;
import ItemList.*;
import Main.Color;

public class Item extends ItemUpgrade implements IItem, IAttack, ICount, IDefence, ILimit, ITimes, IUse,
	IElecStack, IFireStack, IIceStack, IPoisonStack, INeedDice, INewDice, IRecovery, IDamage, IActiveLimit,
	Cloneable{
	
	//흡혈, 상자, 석궁, 격돌, 패링, 역장, 제압, 가시방패, 양손검
	//전염, 락픽, 쇠톱, 거울, 상승, 주걱, 타락한검, 알수없음, 종말
	
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
	
	protected Item() {};
	
	protected Item(String name,
		String description,
		int attack,
		int addAttack,
		int count,
		String limit,
		int times,
		boolean use,
		int needDice,		
		String activeLimit,	
		int fireStack,
		int iceStack,
		int elecStack,
		int poisonStack,	
		int recovery,
		int defence,
		int damage,
		String newDice,		
		int accumulation
			) {
		this.name=name;
		this.description=description;
		this.attack=attack;
		this.addAttack=addAttack;
		this.count=count;
		this.limit=limit;
		this.times=times;
		this.use=use;
		this.needDice=needDice;
		this.activeLimit=activeLimit;
		this.fireStack=fireStack;
		this.iceStack=iceStack;
		this.elecStack=elecStack;
		this.poisonStack=poisonStack;
		this.recovery=recovery;
		this.defence=defence;
		this.damage=damage;
		this.newDice=newDice;
		this.accumulation=accumulation;
	}
		
	@Override
	public void setAttack(int attack) {this.attack=attack;}	
	@Override
	public int getAttack() {return attack;}
	
	@Override
	public void setAddAttack(int addAttack) {this.addAttack=addAttack;}	
	@Override
	public int getAddAttack() {return addAttack;}
	
	@Override
	public void setCount(int count) {this.count=count;}	
	@Override
	public int getCount() {return count;}
	
	@Override
	public void setDefence(int defence) {this.defence=defence;}	
	@Override
	public int getDefence() {return defence;}
	
	@Override
	public void setLimit(String limit) {this.limit=limit;}
	@Override
	public String getLimit() {return limit;}
	
	@Override
	public void setTimes(int times) {this.times=times;}
	@Override
	public int getTimes() {return times;}
	
	@Override
	public void setUse(boolean use) {this.use=use;}
	@Override
	public boolean getUse() {return use;}
		
	@Override
	public void setActiveLimit(String limit) {this.activeLimit=limit;}
	@Override
	public String getActiveLimit() {return activeLimit;}
	
	@Override
	public void setElecStack(int num) {this.elecStack=num;}
	@Override
	public int getElecStack() {return elecStack;}
	
	@Override
	public void setFireStack(int num) {this.fireStack=num;}
	@Override
	public int getFireStack() {return fireStack;}
	
	@Override
	public void setIceStack(int num) {this.iceStack=num;}
	@Override
	public int getIceStack() {return iceStack;}
	
	@Override
	public void setPoisonStack(int num) {this.poisonStack=num;}
	@Override
	public int getPoisonStack() {return poisonStack;}
	
	@Override
	public void setNeedDice(int needDice) {this.needDice=needDice;}
	@Override
	public int getNeedDice() {return needDice;}
	
	@Override
	public void setNewDice(String newDice) {this.newDice=newDice;}
	@Override
	public String getNewDice() {return newDice;}
	
	@Override
	public void setRecovery(int recovery) {this.recovery=recovery;}
	@Override
	public int getRecovery() {return recovery;}
	
	@Override
	public void setDamage(int damage) {this.damage=damage;}
	@Override
	public int getDamage() {return damage;}	
	
	@Override
	public String getName() {return name;}
	@Override
	public String getDescription() {return description;}
	
	public void action(Status player, Status enemy, int dice, TurnInfo my, int idx) {
		if (getCount()!=0) {
			if (actionCount(dice, my, idx)) return;
		}
				
		if (getNeedDice()!=0) {
			if (actionNeedDice(my, idx, dice)) return;
		}
		
		if (getTimes()!=0) {actionTimes(my, idx);}
		if (getUse()) {actionUse(my, idx);}
		
		if (getAttack()!=0) {actionAttack(player, enemy, dice);}
		if (actionActiveLimit(dice)) {			
			if (getFireStack()!=0) {actionFireStack(enemy, dice);}
			if (getIceStack()!=0) {actionIceStack(enemy, dice);}
			if (getElecStack()!=0) {actionElecStack(enemy, dice);}
			if (getPoisonStack()!=0) {actionPoisonStack(enemy, dice);}
			if (getRecovery()!=0) {actionRecovery(player, dice);}
			if (getDamage()!=0) {actionDamage(player, dice);}		
			if (getDefence()!=0) {actionDefence(player, dice);}
			if (getNewDice()!="") {actionNewDice(my, dice);}
		}
		
		if (player.getIsUseSkill()) {			
			if (getAttack()!=0) {actionAttack(player, enemy, dice);}
			if (actionActiveLimit(dice)) {			
				if (getFireStack()!=0) {actionFireStack(enemy, dice);}
				if (getIceStack()!=0) {actionIceStack(enemy, dice);}
				if (getElecStack()!=0) {actionElecStack(enemy, dice);}
				if (getPoisonStack()!=0) {actionPoisonStack(enemy, dice);}
				if (getRecovery()!=0) {actionRecovery(player, dice);}
				if (getDamage()!=0) {actionDamage(player, dice);}		
				if (getDefence()!=0) {actionDefence(player, dice);}
			}
			my.getTurnScript().add(" * 분노 스킬로 두번 발동합니다 *<br>");
			player.setIsUseSkill(false);
		}
	}
	
	@Override
	public void enhance() {
		name=enhName;
		description=enhDescription;
		if (enhAttack!=0) {attack=enhAttack;}
		if (enhAddAttack!=0) {addAttack=enhAddAttack;}
		if (enhCount!=0) {count=enhCount;}
		if (!enhLimit.equals("")) {limit=enhLimit;}
		if (enhTimes!=0) {times=enhTimes;}
		if (!enhActiveLimit.equals("")) {activeLimit=enhActiveLimit;}
		if (enhFireStack!=0) {fireStack=enhFireStack;}
		if (enhIceStack!=0) {iceStack=enhIceStack;}
		if (enhElecStack!=0) {elecStack=enhElecStack;}
		if (enhPoisonStack!=0) {poisonStack=enhPoisonStack;}
		if (enhRecovery!=0) {recovery=enhRecovery;}
		if (enhDefence!=0) {defence=enhDefence;}
		if (enhDamage!=0) {damage=enhDamage;}
		if (!enhNewDice.equals("")) {newDice=enhNewDice;}
		if (enhNeedDice!=0) {needDice=enhNeedDice;}		
	}

	public int getAccumulmation() {
		return accumulation;
	}
	public void setAccumulation(int num) {
		accumulation=num;
	}
	
	 @Override
	    public Item clone() {
	        try {
	        	Item cloned = (Item) super.clone();
	        	cloned.name=this.name;
	    		cloned.description=this.description;
	    		cloned.attack=this.attack;
	    		cloned.addAttack=this.addAttack;
	    		cloned.count=this.count;
	    		cloned.limit=this.limit;
	    		cloned.times=this.times;
	    		cloned.use=this.use;
	    		cloned.needDice=this.needDice;
	    		cloned.activeLimit=this.activeLimit;
	    		cloned.fireStack=this.fireStack;
	    		cloned.iceStack=this.iceStack;
	    		cloned.elecStack=this.elecStack;
	    		cloned.poisonStack=this.poisonStack;
	    		cloned.recovery=this.recovery;
	    		cloned.defence=this.defence;
	    		cloned.damage=this.damage;
	    		cloned.newDice=this.newDice;
	    		cloned.accumulation=this.accumulation;
	            return cloned;
	        } catch (CloneNotSupportedException e) {
	            // 예외 처리
	            return null;
	        }
	    }	
}
