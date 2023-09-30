package Main;

import java.util.Scanner;

import Battle.*;
import Character.*;
import Dice.*;
import Field.*;
import ItemList.*;
import Monster.*;

public class Main extends Script {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Script script = new Script();		
		Player player = new Player();
		Enemy[] enemy = Enemy.enemyList();
		
		int floor=1;		
		int eNum=0;
		Field field = new Field();
		
		script.chooseJob();
		int inputNum=scanner.nextInt();
		player.chooseJob(inputNum);
		
		script.chooseItem(player.getJob());
		inputNum=scanner.nextInt();
		player.setJobItem(player.getJob(), inputNum);

		MyTurn myTurn = new MyTurn(player);//주사위 초기화;
		EnemyTurn enemyTurn = new EnemyTurn(enemy[eNum]);;
		
		while (true) {//스테이지 진입
			
			script.startBattle();			
			myTurn = new MyTurn(player);//주사위 초기화
			enemyTurn = new EnemyTurn(enemy[eNum]);
			while (true) {//전투시작
				
				myTurn.startTurn(player);
				enemyTurn.startTurn(enemy[eNum]);

//				myTurn.doMyTurnLoop(player, enemy[eNum], enemyTurn);
				if (player.getHp()<1||enemy[eNum].getHp()<1) break;

				enemyTurn.doEnemyTurnLoop(player, enemy[eNum], myTurn);
				if (player.getHp()<1||enemy[eNum].getHp()<1) break;

				script.startMyTurn();

			}//end of while Battle

			if (player.getHp()<1) {
				script.printBattleInfo(player, enemy[eNum]);
				System.out.println();
				System.out.println(BOLD+RED+" YOU DIED"+RESET);
				break;
			}//졌을 때
			if (enemy[eNum].getHp()<1) {
				System.out.println();
				System.out.println(B_CYAN+enemy[eNum].getName()+"을(를) 물리쳤습니다!!"+RESET);
				eNum++;
				player.levelUp();
				if (eNum==12||eNum==16) {
					System.out.println("\n  새로운 상점 이용과 추가 회복을 할 수 있습니다\n");
					field = new Field();
				}
				if (eNum>17) {
					script.ending();
					System.exit(1);
				}
			}//이겼을 때

			player.resetPlayer();//플레이어 정보 초기화
			
			while (true) {//필드진입

				script.chooseInField(floor);
				int chooseInField=Input.checkInput(scanner.nextLine());
				if (chooseInField == 1) {//1.전투
					if(eNum == 3||eNum == 6||eNum == 9||eNum == 13||eNum == 17 ) {
						System.out.println();
						System.out.println("이 층에는 더이상 적이 없습니다");
						continue;
					}
					System.out.println();
					break;
				}
				else if (chooseInField == 2) {//2.상점
					if (field.getStoreCount()==1) {
						field.inStore(player);
					}
					else {
						script.changeAlready();
					} 
				}
				else if (chooseInField == 3) {//3.대장간
					if (field.getForgeCount()>0) {
						field.inForge(player);
					}
					else {
						script.useAlready();
					}
				}
				else if (chooseInField == 4) {//4.회복샘
					if (field.getHealCount()>0) {
						field.visitWell(player);
					}
					else {
						script.useAlready();
					}
				}
				else if (chooseInField == 5) {//5.다음층
					if (eNum>=14&&eNum<=17) eNum=17;
					if (eNum>=10&&eNum<=13) eNum=13;
					if (eNum>=7&&eNum<=9) eNum=9;
					if (eNum>=4&&eNum<=6) eNum=6;
					if (eNum>=0&&eNum<=3) eNum=3;
					floor++;
					field = new Field();
					field.setForgeCount(1);
					script.downFloor();
					break;
				}
				else {//아무키. 정보					
					script.printPlayerInfo(player);
					script.printInventoryAll(player);
					continue;
				}
			}//end of while Field
			if (eNum==17) {
				System.out.println(" 최후의 상점에 들어갑니다.");
				System.out.println(" 나가면 바로 전투가 시작됩니다");
				field=new Field();
				field.inStore(player);				
			}
		}//end of while stage
		scanner.close();
	}//end of main
}

