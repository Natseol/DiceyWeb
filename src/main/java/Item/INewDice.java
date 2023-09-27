package Item;

import Battle.TurnInfo;
import Dice.Roll;

public interface INewDice {
	public void setNewDice(String newDice);	
	public String getNewDice();	
	
	default void actionNewDice(TurnInfo my, int dice) {//음수면 dice, 7이상이면 랜덤으로 계산
		String[] newDiceStr = getNewDice().split(" ");
		int[] newDiceInt = new int[newDiceStr.length];
		for (int i = 0; i < newDiceInt.length; i++) {
			newDiceInt[i]=Integer.parseInt(newDiceStr[i]);
		}
		
		for (int i : newDiceInt) {
			if (i < 0) my.getDiceList().add(dice);
			else if (i > 6) my.getDiceList().add(Roll.roll6());
			else my.getDiceList().add(i);
		}
	}	
}