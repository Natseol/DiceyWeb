package Item;

public interface IUpgrade {
	public void setAddAttack(int num);	
	public int getUpAddAttack();
	
	default void upgrade() {
		setAddAttack(getUpAddAttack());
		
	}
}
