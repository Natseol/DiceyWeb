package Item;

public abstract class ItemUpgrade extends ItemScript{

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
	
	public abstract void enhance() ;
	
	public String getEnhName() {
		return enhName;
	}
	
	public String getEnhDescription() {
		return enhDescription;
	}
	
	public int getEnhCount() {
		return enhCount;
	}

	public int getEnhAttack() {
		return enhAttack;
	}

	public int getEnhAddAttack() {
		return enhAddAttack;
	}

	public String getEnhLimit() {
		return enhLimit;
	}

	public int getEnhTimes() {
		return enhTimes;
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
}
