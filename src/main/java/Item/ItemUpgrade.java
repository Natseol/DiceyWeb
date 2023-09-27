package Item;

public abstract class ItemUpgrade extends ItemScript{

	protected String enhName;
	protected String enhDescription;
	
	protected int enhAttack;	
	protected int enhAddAttack;
	protected int enhCount;
	protected String enhLimit="";
	protected int enhTimes;
	
	protected String enhActiveLimit="";
	
	protected int enhFireStack;
	protected int enhIceStack;
	protected int enhElecStack;
	protected int enhPoisonStack;
	
	protected int enhRecovery;
	protected int enhDefence;
	protected int enhDamage;
	protected String enhNewDice="";
	protected int enhNeedDice;
	
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
}
