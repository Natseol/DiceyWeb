package Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Battle.EnemyTurn;
import Dice.Roll;
import Item.Item;
import ItemList.*;
import Monster.*;

public class Enemy extends Status{
	protected String name;
	protected String description;
	protected String grade;
	protected boolean diceMin=false;
	
	public Enemy() {}
	
	public static Enemy setEnemy(int num) {		
		switch (num) {		
		case 0 : return new Pirate();
		case 1 : return new Gatekeeper();//3층   
		case 2 : return new Vampire();//4층        
		case 3 : return new GatekeeperElite();//5층
		case 4 : return new VampireElite();//6층
		
		case 5 : return new Marine();//1층			
		case 6 : return new Frog();
		case 7 : return new Rabbit();
		case 8 : return new Hothead();
		case 9 : return new Robobot();
		case 10 : return new Smile();
		
		case 11 : return new Caster();
		case 12 : return new Quartz();//2층
		case 13 : return new Scholar();
		case 14 : return new Onepun();
		
		case 15 : return new Fighter();     
		case 16 : return new SnowMan();
		case 17 : return new Cowboy();
		case 18 : return new SwordMan();     
		
		case 19 : return new Bear();           
		case 20 : return new Wolf();
		case 21 : return new Skeleton();
		case 22 : return new Meteorologist();
		
		case 23 : return new Mimic();          
		case 24 : return new Witch();
		case 25 : return new RatKing();
		case 26 : return new Valkyrie();

		}
		return new Marine();
	}
	
	public static List<Enemy> randomEnemy(int start, int end) {
		List<Enemy> randomEnemy = new ArrayList<>();
		int[] enemyNum =Roll.shuffle(start, end);
		for (int i = 0; i < enemyNum.length; i++) {
			randomEnemy.add(setEnemy(enemyNum[i]));			
		}
		return randomEnemy;
	}
	
	public static Enemy[] enemyList() {
		Enemy[] enemy = new Enemy[18];
		
		List<Enemy> enemyAll = new ArrayList<>();
		List<Enemy> enemy1floor = randomEnemy(5,10);
		List<Enemy> enemy2floor = randomEnemy(11,14);
		List<Enemy> enemy3floor = randomEnemy(15,18);
		List<Enemy> enemy4floor = randomEnemy(19,22);
		List<Enemy> enemy5floor = randomEnemy(23,26);
				
		for (int i = 0 ; i<3;i++) {//1층 3개 몹
			enemyAll.add(enemy1floor.get(i));
		}
		
		enemyAll.add(setEnemy(0));
		for (int i = 0 ; i<2;i++) {//2층 문지기 + 2개 몹
			enemyAll.add(enemy2floor.get(i));
		}
		
		enemyAll.add(setEnemy(1));
		for (int i = 0 ; i<2;i++) {//3층 문지기 + 2개 몹
			enemyAll.add(enemy3floor.get(i));
		}
		
		enemyAll.add(setEnemy(2));
		for (int i = 0 ; i<3;i++) {//4층 문지기 + 3개 몹
			enemyAll.add(enemy4floor.get(i));
		}
		
		enemyAll.add(setEnemy(3));
		for (int i = 0 ; i<3;i++) {//5층 문지기 + 3개 몹
			enemyAll.add(enemy5floor.get(i));
		}
		
		enemyAll.add(setEnemy(4));
		
		for (int i = 0; i < 18; i++) {
//			System.out.println(enemyAll.get(i).getName());
			enemy[i] = enemyAll.get(i);
		}
		
		return enemy;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGrade() {
		return grade;
	}
	
//	public void action(EnemyTurn EnemyTurn) {}
	
	public boolean getDiceMin() {
		return diceMin;
	}
	
}
