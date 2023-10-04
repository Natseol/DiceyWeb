package Character;

import java.io.Serializable;
import java.util.Collections;

import Battle.*;
import Item.*;
import ItemList.*;
import Main.*;

public class Status implements Serializable {
	protected int hp;
	protected int maxHp;
	protected int def;
	protected int diceQuantity;
	protected Item[] inventory;
	protected int condition[]=new int[4];
	protected boolean isEffect;
	protected int sp;
	protected boolean isUseSkill;
		
	public Status() {}	
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void addHp(int num) {
		hp += num;
		if (hp > maxHp) {
			hp=maxHp;
		}
	}	
	public void subtractHp(int num) {
		if (def>=num) {
			def-=num;
		}
		else if (def>0&&def-num<0) {
			hp=(hp+def)-num;
			def=0;
			sp = sp + num - def;
		}
		else {
		hp -= num;
		sp += num;
		}
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = hp;
	}
	
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}		
	
	public int getDiceQuantity() {
		return diceQuantity;
	}
	public void setDiceQuantity(int diceQuantity) {
		this.diceQuantity = diceQuantity;
	}
	
	public Item[] getInventory() {
		return inventory;
	}
	public Item getInventory(int idx) {
		return inventory[idx];
	}
	public String getInventoryName(int idx) {
		return inventory[idx].getName();
	}
	public String getInventoryDescription(int idx) {
		return inventory[idx].getDescription();
	}
	
	public void setInventory(int idx, Item item) {
		inventory[idx] = item;
	}
		
	public boolean getIsEffect() {
		return isEffect;
	}	
	public void setIsEffect(boolean effect) {
		isEffect = effect;
	}	
	
	public int getSp() {
		return sp;
	}	
	public void setSp(int sp) {
		this.sp = sp;
	}
	
	public boolean getIsUseSkill() {
		return isUseSkill;
	}
	public void setIsUseSkill(boolean useSkill) {
		isUseSkill = useSkill;
	}
	
	
	public int[] getCondition() {
		return condition;
	}
	public int getCondition(int idx) {
		return condition[idx];
	}
	public void setCondition(int idx,int changeNum) {
		condition[idx]=changeNum;		
	}
	
	public void resetCondition() {
		for (int i=0;i<condition.length;i++) {
			condition[i]=0;
		}
	}
	
	public void resetFireStack() {
		setCondition(0,0);
	}
	
	public String damagedFireStr() {
		if (Math.random()<(0.25*getCondition(0))) {
			subtractHp(2);
			System.out.println(Color.RED+" * 주사위를 건들다 [2]의 피해를 입습니다 * "+Color.RESET);
			return "&nbsp* 주사위를 건들다 [2]의 피해를 입습니다 *&nbsp<br><br>";
		}
		return "";
	}
	
	public void damagedIceList(TurnInfo turninfo) {
		setCondition(1,getCondition(1)-1);
		int max = Collections.max(turninfo.getDiceList());
		int maxIndex = turninfo.getDiceList().indexOf(max);
		turninfo.getDiceList().set(maxIndex, 1);		
	}
	
	public boolean damagedParalysisList(TurnInfo turninfo, int idxDice) {
//		int index = idxDice - 1; 
		if (Math.random()<0.15*turninfo.getDiceList().get(idxDice)) {
		setCondition(2,getCondition(2)-1);
		turninfo.getDiceList().remove(idxDice);
		System.out.println(Color.BYELLOW+" * 충격을 받았습니다. 주사위를 놓칩니다 * \n"+Color.RESET);
		return true;
		}
		return false;
	}
	
	public String damagedPoisonStr() {
		subtractHp(getCondition(3));
		setCondition(3,getCondition(3)-1);
		return "&nbsp* 중독됐습니다. ["+(getCondition(3)+1)+"]의 피해를 입습니다 *&nbsp<br><br>";
	}
}
