package Field;

import java.util.Scanner;

import Character.*;
import Main.Color;
import Main.Input;
import Main.Script;

public class Field {
	
	Scanner scanner = new Scanner(System.in);
	Store store = new Store();
	Script script = new Script();
	
	int storeCount = 1;
	int healCount = 1;
	int forgeCount = 0;
	
	public void inStore(Player player) {
		while (true) {
			System.out.println();
			script.printStore();
			getStore().showList();

			System.out.println(Color.YELLOW+"상점의 장비를 선택하세요 (나가기 : 0)"+Color.RESET);
			int storeIdx=30;
			while (storeIdx>2||storeIdx<-1) {
				storeIdx=Input.checkInput(scanner.nextLine())-1;
			}			
			if (storeIdx+1 == 0) {
				break;
			}
			
			System.out.println();
			script.printInventoryAll(player);
			System.out.println(Color.YELLOW+"당신의 장비를 선택하세요 (다시선택 : 0)"+Color.RESET);
			int invenIdx=30;
			while (invenIdx>player.getInventory().length-1||invenIdx<-1) {
				invenIdx=Input.checkInput(scanner.nextLine())-1;
			}	
			if (invenIdx+1 == 0) {
				continue;
			}
			else if (invenIdx+1 == player.getInventory().length) {
				System.out.println("");
				System.out.println(" * 고유장비는 변경할 수 없습니다 *");
				continue;
			}
			else {
				player.setInventory(invenIdx,getStore().getStoreList(storeIdx));
				script.printInventoryAll(player);
				setStoreCount(0);
				break;
			}
		}//end of while 상점
	}
	
	public void inForge(Player player) {
		script.printForge();
		script.printInventoryAll(player);
		script.printEnhacedInventoryAll(player);
		System.out.println(Color.YELLOW+"당신의 장비를 선택하세요 (나가기 : 0)"+Color.RESET);
		int invenIdx=30;
		while (invenIdx>player.getInventory().length-1||invenIdx<-1) {
			invenIdx=Input.checkInput(scanner.nextLine())-1;
		}
		if (invenIdx==-1) return;
		System.out.println();
		System.out.print((invenIdx+1)+") "+player.getInventory(invenIdx).getEnhName()+" : ");
		System.out.println(player.getInventory(invenIdx).getEnhDescription());

		scanner.nextLine();
		if (player.getInventory(invenIdx).getEnhName().equals("빈슬롯")) {
			System.out.println("빈 슬롯을 선택하였습니다");
			return;
		}
		player.getInventory(invenIdx).enhance();
		script.printInventoryAll(player);
		setForgeCount(0);		
	}
	
	public void visitWell(Player player) {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("회복의 샘에 왔습니다");
		System.out.println("------------------------------------------------");
		System.out.println("체력을 "+(4+player.getLevel())+" 회복합니다  남은 횟수 : "+getHealCount());
		System.out.println(Color.YELLOW+"(예:1 아니오:0)"+Color.RESET);
		int input=30;
		while (input!=1&&input!=0) {
			input=Input.checkInput(scanner.nextLine());
		}	
		
		if (input==1) {
			player.addHp(4+player.getLevel());
			System.out.println();
			System.out.println(Color.B_BGREEN+" * 체력을 "+(4+player.getLevel())+" 회복했습니다 *"+Color.RESET);
			setHealCount(getHealCount()-1);
		}
	}
	
	public int getHealCount() {
		return healCount;
	}	
	public void setHealCount(int num) {
		healCount = num;
	}
	
	public int getStoreCount() {
		return storeCount;
	}	
	public void setStoreCount(int num) {
		storeCount = num;
	}
	
	public int getForgeCount() {
		return forgeCount;
	}	
	public void setForgeCount(int num) {
		forgeCount = num;
	}
	
	public Store getStore() {
		return store;
	}

}


