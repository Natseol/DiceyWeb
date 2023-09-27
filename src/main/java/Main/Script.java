package Main;

import Battle.*;
import Character.*;
import Dice.Roll;
import Field.Store;
import ItemList.*;

public class Script implements Color {
	public void chooseJob() {
		System.out.println("------------------------------------------------");
		System.out.println();
		System.out.println("\t    주사위 던전에 온신걸 환영합니다");
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("\t\t클래스를 선택하세요");
		System.out.println("\t(1.전사 2.도적 3.궁수 4.마법사 5.기사)");
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	
	public void explain() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println();
		System.out.println(CYAN+BOLD+"1. 주사위 눈금을 확인하고, 주사위의 '번호'를 눌러 선택하세요"+RESET);
		System.out.println();
		System.out.println(CYAN+BOLD+"2. 장비의 '번호'를 눌러 선택한 주사위의 눈금을 적용시킵니다"+RESET);
		System.out.println();
		System.out.println(" - 아이템의 효과 -");
		System.out.println(" 1) ㅁ:주사위 눈금만큼 숫자를 넣습니다");
		System.out.println("  ex) 4눈금의 주사위로 '검:ㅁ 의 피해를 줍니다'를 사용할경우, 4의 피해를 줍니다");
		System.out.println(" 2) 카운트:주사위의 눈금만큼 카운트를 낮춥니다. 카운트가 0이 되면 발동합니다");
		System.out.println(" 3) 주사위 눈금이 맘에 들지 않으면 주사위 눈금과 관련된 장비를 사용하세요");
		System.out.println("  ex) 변경, 상승과 같이 주사위 변경에 도움이 되는 장비들이 있습니다");
		System.out.println(" 4) 상태이상을 주는 장비도 있습니다. 적절한 상태이상으로 전투를 유리하게 진행하세요");
		System.out.println();
		System.out.println(CYAN+BOLD+"3. SP가 가득차면 스킬을 사용할 수 있습니다. 주사위 선택에서 77을 눌러 스킬을 사용해보세요"+RESET);
		System.out.println();
		System.out.println(CYAN+BOLD+"4. 사용가능한 장비가 없거나, 모든 주사위를 사용했으면 0을 눌러 턴을 종료하세요"+RESET);
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	
	public void chooseItem(String job) {		
		System.out.println();
		switch (job) {
		case "전사" :
			System.out.println(YELLOW+"\t\t\t장비를 선택하세요"+RESET);
			System.out.println("\t    1.[검, 도끼, 변경]");
			System.out.println("\t    2.[카타나, 부메랑, 희생]");
			break;
		case "도적" :
			System.out.println(YELLOW+"\t\t\t장비를 선택하세요"+RESET);
			System.out.println("\t    1.[단검, 짧은활, 락픽]");
			System.out.println("\t    2.[맹독, 독칼, 자르기]");
			break;
		case "궁수" :
			System.out.println(YELLOW+"\t\t\t장비를 선택하세요"+RESET);
			System.out.println("\t    1.[장궁, 짧은검, 정밀]");
			System.out.println("\t    2.[석궁, 함정, 위조]");
			break;
		case "마법사" :
			System.out.println(YELLOW+"\t\t\t장비를 선택하세요"+RESET);
			System.out.println("\t    1.[완드, 얼음파편, 거울]");
			System.out.println("\t    2.[스태프, 눈폭풍, 두사위]");
			break;
		case "기사" :
			System.out.println(YELLOW+"\t\t\t장비를 선택하세요"+RESET);
			System.out.println("\t    1.[격돌, 가시방패, 막기]");
			System.out.println("\t    2.[성검, 패링, 매직쉴드]");
			break;
		}		
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	

	public void startBattle() {
		System.out.println();
		System.out.println("\t\t전투를 시작합니다");
		System.out.println("------------------------------------------------");
	}
	
	public void startMyTurn() {
		System.out.println();
		System.out.println();
		System.out.println("\t   - 나의 턴 -");
		System.out.println("------------------------------------------------");
	}
	
	public void printBattleInfo(Player player, Enemy enemy) {		
		System.out.print("Lv"+player.getLevel()+" "+player.getJob()+" 주사위:"+player.getDiceQuantity()+"\t\t\t");
		System.out.println(enemy.getName()+" 주사위:"+enemy.getDiceQuantity());
		if (player.getHp()<player.getMaxHp()*0.3) {
			System.out.print(BRED+player.getHp()+RESET); 
		}
		else {
		System.out.print(player.getHp());
		}
		if (player.getDef()>0) {
			System.out.print(BCYAN+"("+player.getDef()+")"+RESET);
		}
		System.out.print(" / "+player.getMaxHp());
		if (player.getSp()>10+player.getLevel()) {
			System.out.print(BOLD+"  [ 사용가능 ]\t\t"+RESET);
		}
		
		else {
			System.out.print("  [SP : "+Math.round(player.getSp()*100/(10+player.getSp()))+"]\t\t");
		}
		System.out.print(enemy.getHp());
		if (enemy.getDef()>0) {
			System.out.print(BCYAN+"("+enemy.getDef()+")"+RESET);
		}
		System.out.println(" / "+enemy.getMaxHp());
		printConditionInfo(player, enemy);
	}
	
	public void printConditionInfo (Player player, Enemy enemy) {
		int countCondi=0;
		if (player.getCondition(0)>0) {
			System.out.print(RED+"발화:"+player.getCondition(0)+" "+RESET);
			countCondi++;
		}
		if (player.getCondition(1)>0) {
			System.out.print(BBLUE+"빙결:"+player.getCondition(1)+" "+RESET);
			countCondi++;
		}
		if (player.getCondition(2)>0) {
			System.out.print(BYELLOW+"감전:"+player.getCondition(2)+" "+RESET);
			countCondi++;
		}
		if (player.getCondition(3)>0) {
			System.out.print(BPURPLE+"중독:"+player.getCondition(3)+" "+RESET);
			countCondi++;
		}
		switch (countCondi) {
			case 4:
				System.out.print("\t\t\t");
				break;
			case 3:
				System.out.print("\t\t\t");
				break;
			case 2:
				System.out.print("\t\t\t\t");
				break;
			case 1:
				System.out.print("\t\t\t\t\t");
				break;
			default:
				System.out.print("\t\t\t\t");
				break;
		}
		if (enemy.getCondition(0)>0) {
			System.out.print(RED+"발화:"+enemy.getCondition(0)+" "+RESET);
		}
		if (enemy.getCondition(1)>0) {
			System.out.print(BBLUE+"빙결:"+enemy.getCondition(1)+" "+RESET);
		}
		if (enemy.getCondition(2)>0) {
			System.out.print(BYELLOW+"감전:"+enemy.getCondition(2)+" "+RESET);
		}
		if (enemy.getCondition(3)>0) {
			System.out.print(BPURPLE+"중독:"+enemy.getCondition(3)+" "+RESET);
		}
		System.out.println();
	}
	
	public void selectDiceList(MyTurn turnInfo) {
		System.out.println(" - 당신의 주사위 - ");
		for (int i = 0; i < turnInfo.getDiceList().size(); i++) {
			System.out.print("("+(i+1)+")"+turnInfo.getDiceList(i)+"  ");
		}
		System.out.println();
		System.out.println(YELLOW+"주사위를 선택하세요 (0:턴 종료, 77:스킬발동, 88:설명보기, 99:적 정보 보기)"+RESET);
	}

	public void selectDiceList(TurnInfo turnInfo) {
		System.out.println();
		for (int i = 0; i < turnInfo.getDiceList().size(); i++) {
			System.out.print("("+(i+1)+")"+turnInfo.getDiceList(i)+"  ");
		}
		System.out.println();
	}
	
	public void printSelectedDice(int numDice) {
	System.out.println();
	System.out.println("선택된 눈금 : "+B_BLACK+" "+numDice+" "+RESET);
	}
	
	public void printSelectedDiceUse(int enemyItemNum, Enemy enemy) {
		System.out.println((enemyItemNum+1)+")["+enemy.getInventoryName(enemyItemNum)+"]을 사용합니다");
		System.out.println();
		
	}
	
	public void selectTurnEnd() {
			System.out.println();
			System.out.println(" - 턴 종료 -");						
			System.out.println("------------------------------------------------");
	}
	
	public void printSelectItem(TurnInfo turnInfo) {
		System.out.println(YELLOW+"장비를 선택하세요 (0:주사위 다시 선택하기)"+RESET);
	}
	
	public void printInventoryAll(Player player) {
		System.out.println("--------------------- 장비 ----------------------");
		for (int i=0;i<player.getInventory().length;i++) {
			System.out.print((i+1)+") "+player.getInventory(i).getName()+" : "+player.getInventory(i).getDescription());
			if(player.getInventory(i).getCount()>0) {
				System.out.print(" (카운트:"+player.getInventory(i).getCount()+")");
			}
			if (i==player.getInventory().length-1) {
				System.out.println(" -고유-");
			}
			else {
				System.out.println();
			}
				
		}
		System.out.println("------------------------------------------------");
	}
	
	public void printEnhacedInventoryAll(Player player) {
		System.out.println("--------------------- 강화 ----------------------");
		for (int i=0;i<player.getInventory().length;i++) {
			System.out.print((i+1)+") "+player.getInventory(i).getEnhName()+" : "+player.getInventory(i).getEnhDescription());
			if(player.getInventory(i).getCount()>0) {
				System.out.print(" (카운트:");
				if (player.getInventory(i).getEnhCount()!=0) System.out.print(player.getInventory(i).getEnhCount());
				else System.out.print(player.getInventory(i).getCount());
				System.out.print(")");
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------");
	}
	
	public void printItem(TurnInfo turnInfo) {
		System.out.println("------------------------------------------------");
		for (int i = 0; i < turnInfo.getItem().length; i++) {
			if (turnInfo.getItem(i).getName().equals(new Nothing().getName())||turnInfo.getTurnUse(i)==1) {
				System.out.print(BLACK);
			}
			System.out.print(i+1+") "+turnInfo.getItem(i).getName()+" : "+turnInfo.getItem(i).getDescription());
			if (turnInfo.getTurnTimes(i) > 1) {
				System.out.print(" 남은횟수:"+turnInfo.getTurnTimes(i));
			}
			if(turnInfo.getTurnCount(i)>0) {
				System.out.print(" (카운트:"+turnInfo.getTurnCount(i)+")");
			}
			if(turnInfo.getNeedDice(i)>0) {
				System.out.print(" 누적:"+turnInfo.getNeedDice(i));
			}			
			System.out.println(RESET);
		}
		System.out.println("------------------------------------------------");
	}	
	
	public void printEnemyInfo(TurnInfo turnInfo, Enemy enemy) {
		System.out.println();
		System.out.println();
		System.out.println(" - "+enemy.getName()+"("+enemy.getGrade()+") -");
		System.out.println("설명: "+enemy.getDescription());
		System.out.println("주사위: "+enemy.getDiceQuantity()+"개");
		printItem(turnInfo);
		System.out.println();
	}
	
	public void printPlayerInfo(Player player) {
		System.out.println();
		System.out.println("--------------------- 상태 ----------------------");
			System.out.println(player.getJob()+" Lv:"+player.getLevel()+" [EXP: "+player.getExp()+"/"+player.getExpTable(player.getLevel()-1)+"]");
			System.out.println("주사위:"+player.getDiceQuantity());
			System.out.print("Hp: "+player.getHp());
			System.out.println("/"+player.getMaxHp());
	}
	
	public void printDamagedIce() {
		System.out.println(B_BBLUE+" * 주사위가 얼어붙습니다. 눈금이 1로 변합니다 *"+RESET);
		System.out.println();
	}

	public void printCheckTrue() {
		System.out.println();
		System.out.println(B_BLACK+" * 주사위 눈금을 확인하세요 * "+RESET);
		System.out.println();
		System.out.println("------------------------------------------------");
	}
	
	public void chooseInField(int floor) {
		System.out.println("\n");
		System.out.println("-------------------- 지하 "+floor+"층 --------------------");
		System.out.println("1. 다음 전투로");
		System.out.println("2. 상점 방문하기");
		System.out.println("3. 대장간 방문하기");
		System.out.println("4. 회복의 샘으로 가기");
		System.out.println("5. 다음 층으로 내려가기");
		System.out.println("아무키 내 정보 확인하기");
		System.out.println(Color.YELLOW+"어디로 이동하시겠습니까?"+Color.RESET);
	}	
	
	public void printStore() {
		System.out.println("------------------------------------------------");
		System.out.println("상점에 방문했습니다");
		System.out.println("상품이 중복으로 보이는건 기분 탓입니다");
		System.out.println("(한번만 교환가능합니다)");
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	
	public void printForge() {
		System.out.println("------------------------------------------------");
		System.out.println("대장간에 방문했습니다");
		System.out.println("(한층마다 한번만 교환가능합니다)");
		System.out.println("------------------------------------------------");
		System.out.println();
	}
	
	public void changeAlready() {
		System.out.println();
		System.out.println("이미 교환완료 했습니다");
		System.out.println();
		System.out.println("------------------------------------------------");
	}
	public void useAlready() {
	System.out.println();
	System.out.println("지금은 이용할 수 없습니다");
	System.out.println("------------------------------------------------");
	}
	
	public void downFloor() {
		System.out.println();
		System.out.println();
		System.out.println("\t*** 한 층 더 아래로 내려갑니다 ***");
		System.out.println();
		System.out.println();
	}
	
	public void ending() {
		System.out.println();
		System.out.println(BWHITE+"------------------------------------------------");
		System.out.println();
		System.out.println("\t\t     축하합니다.");
		System.out.println("\t   *** 모든 몬스터를 쓰러트렸습니다 ***");
		System.out.println();
		System.out.println("------------------------------------------------");
	}
}
