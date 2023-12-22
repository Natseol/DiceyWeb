package Item;

public class ItemScript {	
	
	protected static StringBuilder strb = new StringBuilder();
	
	public static void resetStrb() {
		strb.setLength(0);
	}

	public static StringBuilder getStrb() {
		return strb;
	}

	public static void printDamage(int dice) {
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!&nbsp<br>");
	}
	
	public static void printDamage2(int dice) {
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!&nbsp<br>");
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!!!&nbsp<br>");
	}
	
	public static void printDamage3(int dice) {
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!&nbsp<br>");
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!!!&nbsp<br>");
		strb.append("&nbsp"+dice+" 의 피해를 줬습니다!!!!!!&nbsp<br>");
	}
	
	public static void printCount(int beforeNum, int num) {
		strb.append("&nbsp카운트 : "+beforeNum+" > "+num+"&nbsp<br>");
	}
	
	public static void printNewDIce(int num) {
		strb.append("&nbsp[ "+num+" ] 생성&nbsp<br>");
	}
	public static void printTakedDamage(int dice) {
		strb.append("&nbsp"+dice+" 의 피해를 받았습니다!!&nbsp<br>");
	}
	
	public static void printDamageFire(int dice) {
		strb.append("&nbsp== "+dice+" 의 화염 피해를 줬습니다!! ==&nbsp<br>");
	}
	
	public static void printFire(int num) {
		strb.append("&nbsp== 상대방을 불태웁니다! +"+num+" ==&nbsp<br>");
	}
	
	public static void printDamageIce(int dice) {
		strb.append("&nbsp== "+dice+" 의 냉기 피해를 줬습니다!! ==&nbsp<br>");
	}
	
	public static void printIced(int num) {
		strb.append("&nbsp== 상대방을 얼립니다! +"+num+" ==&nbsp<br>");
	}
	
	public static void printDamageShock(int dice) {
		strb.append("&nbsp== "+dice+" 의 전기 피해를 줬습니다!! ==&nbsp<br>");
	}
	public static void printShock(int num) {
		strb.append("&nbsp== 상대방을 감전시킵니다! +"+num+" ==&nbsp<br>");
	}
	
	public static void printDamagePoison(int dice) {
		strb.append("&nbsp"+dice+" 의 독 피해를 줬습니다!! ==&nbsp<br>");
	}
	public static void printPoisoned(int num) {
		strb.append("&nbsp== 상대방을 중독시킵니다! +"+num+" ==&nbsp<br>");
	}
	
	public static void printGainDefence(int dice) {
		strb.append("&nbsp"+dice+" 의 방어력을 얻었습니다!!&nbsp<br>");
	}	

	public static void printRecovery(int dice) {
		strb.append("&nbsp"+dice+" 의 체력을 회복했습니다&nbsp<br>");
	}
	
	public static void printIncorrectDice() {
		strb.append("&nbsp* 눈금이 맞지않아 발동되지 않습니다 *&nbsp<br>");
	}	
	
}
