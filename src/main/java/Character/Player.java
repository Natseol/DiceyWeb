package Character;

import Item.Item;
import ItemList.*;
import Main.Color;

public class Player extends Job {
	protected int exp;
	protected int[] expTable= {6,6,6,8,8,10};
	protected int level;
	
    public Player() {}
	public Player(int num) {//1.전사 2.도적
		level=1;
		inventory = new Item[5];
		switch (num) {
		case 1 : setJobWarrior(); break;
		case 2 : setJobThief(); break;
		case 3 : setJobArcher(); break;
		case 4 : setJobMagician(); break;
		case 5 : setJobKnight(); break;
		default : break;
		}		
	}

	public int getExp() {
		return exp;
	}	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getLevel() {
		return level;
	}	
	public void setLevel(int level) {
		this.level = level;
	}
	
    public int getExpTable(int idx) {
    	return expTable[idx];
    }
	
	public void levelUp() {
		exp += 2;
		if (exp == expTable[level-1]) {
			level++;
			exp=0;
			if (job.equals("전사")||job.equals("기사")) {
				maxHp+=6;
			}
			else {
				maxHp+=5;
			}				
			hp = maxHp;
			diceQuantity=(int)(2+level*0.5);
			System.out.println();
			System.out.println(Color.B_BLUE+"레벨이 올랐습니다!!"+Color.RESET);
		}
	}
	
	public void resetPlayer() {
		for (int i =0;i<inventory.length;i++) {//누적 초기화
			inventory[i].setAccumulation(0);
		}
				
		for (int i=0;i<condition.length;i++) {
			condition[i]=0;
		}
		
		def = 0;
		
		if (job.equals("전사")||job.equals("기사"))
			switch (level) {
			case 1 : diceQuantity = 2; break;
			case 2 : diceQuantity = 3; break;
			case 3 : diceQuantity = 3; break;
			case 4 : diceQuantity = 4; break;
			case 5 : diceQuantity = 4; break;
			case 6 : diceQuantity = 5; break;
			}
		else {
			switch (level) {
			case 1 : diceQuantity = 3; break;
			case 2 : diceQuantity = 4; break;
			case 3 : diceQuantity = 4; break;
			case 4 : diceQuantity = 5; break;
			case 5 : diceQuantity = 5; break;
			case 6 : diceQuantity = 6; break;
			}
		}

	}
}
