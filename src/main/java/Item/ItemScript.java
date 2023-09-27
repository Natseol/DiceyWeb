package Item;

import Main.Color;

public class ItemScript {

	public static void printDamage(int dice) {
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!");
		System.out.println();
	}
	
	public static void printDamage2(int dice) {
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!");
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!!!");		
		System.out.println();
	}
	
	public static void printDamage3(int dice) {
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!");
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!!!");		
		System.out.println(" "+Color.B_RED+" "+dice+" "+Color.RESET+" 의 피해를 줬습니다!!!!!!");
		System.out.println();
	}
	
	public static void printTakedDamage(int dice) {
		System.out.println(" =="+Color.B_RED+" "+dice+" "+Color.RESET+"의 피해를 받았습니다!! ==");
		System.out.println();
	}
	
	public static void printDamageFire(int dice) {
		System.out.println(Color.B_RED+" == "+dice+" 의 화염 피해를 줬습니다!! == "+Color.RESET);
		System.out.println();
	}
	
	public static void printFire(int num) {
		System.out.println(Color.B_RED+" == 상대방을 불태웁니다! +"+num+" == "+Color.RESET);
		System.out.println();
	}
	
	public static void printDamageIce(int dice) {
		System.out.println(Color.B_BBLUE+" == "+dice+" 의 냉기 피해를 줬습니다!! == "+Color.RESET);
		System.out.println();
	}
	
	public static void printIced(int num) {
		System.out.println(Color.B_BBLUE+" == 상대방을 얼립니다! +"+num+" == "+Color.RESET);
		System.out.println();
	}
	
	public static void printDamageShock(int dice) {
		System.out.println(Color.B_BYELLOW+" == "+dice+" 의 전기 피해를 줬습니다!! == "+Color.RESET);
		System.out.println();
	}
	public static void printShock(int num) {
		System.out.println(Color.B_BYELLOW+" == 상대방을 감전시킵니다! +"+num+" == "+Color.RESET);
		System.out.println();
	}
	
	public static void printDamagePoison(int dice) {
		System.out.println(Color.BPURPLE+" == "+dice+" 의 독 피해를 줬습니다!! == "+Color.RESET);
		System.out.println();
	}
	public static void printPoisoned(int num) {
		System.out.println(Color.BPURPLE+" == 상대방을 중독시킵니다! +"+num+" == "+Color.RESET);
		System.out.println();
	}
	
	public static void printGainDefence(int dice) {
		System.out.println(" "+Color.B_BCYAN+" "+dice+" "+Color.RESET+" 의 방어력을 얻었습니다!!");
		System.out.println();
	}	

	public static void printRecovery(int dice) {
		System.out.println(" "+Color.B_BGREEN+" "+dice+" "+Color.RESET+" 의 체력을 회복했습니다");
		System.out.println();
	}
	
	public static void printIncorrectDice() {
		System.out.println(" * 눈금이 맞지않아 발동되지 않습니다 *");
		System.out.println();
	}	
	
}
