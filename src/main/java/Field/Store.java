package Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Character.*;
import Item.Item;
import ItemList.*;
import db.ItemVO;

public class Store implements Serializable{
	
	Item[] storeList = new Item[3];
	
	public Store() {//랜덤 아이템3개 생성
		for (int i = 0; i < storeList.length; i++) {
			int randomNum =(int)(Math.random()*(itemList.size()-9))+9;
			storeList[i]=itemList.get(randomNum);
		}		
	}
	
	public void resetStore() {
		for (int i = 0; i < storeList.length; i++) {
			int randomNum =(int)(Math.random()*(itemList.size()-9))+9;
			storeList[i]=itemList.get(randomNum);
		}	
	}
	
	public Item[] getStoreList() {
		return storeList;
	}	

	public Item getStoreList(int num) {
		return storeList[num];
	}
				
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void showList() {
		for (int i =0; i<storeList.length;i++) {			
			System.out.print((i+1)+") "
					+storeList[i].getName()
					+" : "+storeList[i].getDescription());
			if(storeList[i].getCount()>0) {
				System.out.print(" (카운트:"+storeList[i].getCount()+")");
			};
			System.out.println();
		}
	}
	
//	public static final Item[] ITEMLIST = {
//			new Nothing(),
//			new UsedGreat(),
//			
//			new Unknown(),
//			new SpareWitch(),
//			new Scythe(),
//			new BloodSucking(),
//			new EnhancedScythe(),
//			new EnhancedBlood(),
//			new Death(),
//			
//			//F:7, I:8, E:7, P:7, D:7+2, H:5+1
//			new Sword(),
//			new Dagger(),
//			new Axe(),
//			new GreatSword(),
//			new Boomerang(),
//			new SpikeShield(),//D           
//			new Spear(),                     
//			new Fist(),                       
//			new Staff(),                   
//			new Cannon(),                     
//			new Box(),                   
//			new Wand(),//F                      
//			new IceShards(),//I                 
//			new Glove(),//E                     
//			new ShortBow(),
//			new BroadSword(),
//			new Charge(),
//			new Crossbow(),//F
//			new FireSword(),//F
//			new IceSword(),//I
//			new HolySword(),//H
//			new Overwhelming(),
//			new DeadlyPoison(),//P
//			new PoisonKnife(),//P
//			new Revolver(),
//			new Trap(),//E, P
//			new Bash(),//D
//			new ShortSword(),
//			new MagicMissile(),
//			new Capacitor(),//E
//			new Claw(),//P
//			new Icicle(),//I
//			new Shovel(),//E
//			new TwoHandedSword(),
//			new Infection(),//P
//			new Meteor(),//F
//			new AbsoluteZero(),//I
//			new Storm(),//E
//			new IcePillar(),//I
//			new Burn(),//F
//			new FireBall(),//F
//			new LightingRod(),//E
//			new Kunai(),
//			new Katana(),
//			new Rat(),//P
//			new RayGun(),
//			new PoisonCloud(),//P
//			new SnowStorm(),//I
//			new Freeze(),//I
//			new Hex(),//F,I,E
//			new Parrying(),
//			new LongBow(),
//
//			new MedicKit(),//H                  
//			new Bandage(),//H                   
//			new Heal(),//H                      
//			new Shield(),//D
//			new Buckler(),//D
//			new KiteShield(),//D
//			new ForceField(),//D
//			new FrontLine(),//D
//			new Crystal(),//H
//			
//			new Saw(),             
//			new Sacrifice(),
//			new Talisman(),
//			new SneakEye(),
//			new DupDice(),
//			new Rising(),               
//
//			new Reroll(),                   
//			new Spatula(),
//			new LockPick(),            
//			new Blender(), 
//			new Precision(),          
//			new Clone(),
//			new Mirror(),          
//			new Spare(),          
//			new Guard(),//D
//			new MagicShield()//D,H
//	};
	
	private List<Item> basicItemlist = List.of(
			new Nothing(),
			new UsedGreat(),
			
			new Unknown(),
			new SpareWitch(),
			new Scythe(),
			new BloodSucking(),
			new EnhancedScythe(),
			new EnhancedBlood(),
			new Death(),
			
			//F:7, I:8, E:7, P:7, D:7+2, H:5+1
			new Sword(),
			new Dagger(),
			new Axe(),
			new GreatSword(),
			new Boomerang(),
			new SpikeShield(),//D           
			new Spear(),                     
			new Fist(),                       
			new Staff(),                   
			new Cannon(),                     
			new Box(),                   
			new Wand(),//F                      
			new IceShards(),//I                 
			new Glove(),//E                     
			new ShortBow(),
			new BroadSword(),
			new Charge(),
			new Crossbow(),//F
			new FireSword(),//F
			new IceSword(),//I
			new HolySword(),//H
			new Overwhelming(),
			new DeadlyPoison(),//P
			new PoisonKnife(),//P
			new Revolver(),
			new Trap(),//E, P
			new Bash(),//D
			new ShortSword(),
			new MagicMissile(),
			new Capacitor(),//E
			new Claw(),//P
			new Icicle(),//I
			new Shovel(),//E
			new TwoHandedSword(),
			new Infection(),//P
			new Meteor(),//F
			new AbsoluteZero(),//I
			new Storm(),//E
			new IcePillar(),//I
			new Burn(),//F
			new FireBall(),//F
			new LightingRod(),//E
			new Kunai(),
			new Katana(),
			new Rat(),//P
			new RayGun(),
			new PoisonCloud(),//P
			new SnowStorm(),//I
			new Freeze(),//I
			new Hex(),//F,I,E
			new Parrying(),
			new LongBow(),

			new MedicKit(),//H                  
			new Bandage(),//H                   
			new Heal(),//H                      
			new Shield(),//D
			new Buckler(),//D
			new KiteShield(),//D
			new ForceField(),//D
			new FrontLine(),//D
			new Crystal(),//H
			
			new Saw(),             
			new Sacrifice(),
			new Talisman(),
			new SneakEye(),
			new DupDice(),
			new Rising(),               

			new Reroll(),                   
			new Spatula(),
			new LockPick(),            
			new Blender(), 
			new Precision(),          
			new Clone(),
			new Mirror(),          
			new Spare(),          
			new Guard(),//D
			new MagicShield()//D,H
			);
	
	ArrayList<Item> itemList = new ArrayList<>(basicItemlist);
	
	public void changeItem(Player player, int inven, Item item) {	
		player.setInventory(inven, item);
	}
	
	public void addList(List<ItemVO> list) {
		ArrayList<Item> temp = new ArrayList<>(basicItemlist);
		for (int i = 0; i < list.size(); i++) {
			temp.add(new Item(list.get(i)));
		}
		itemList = temp;
	}
}
