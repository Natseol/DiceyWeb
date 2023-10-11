package Main;

import java.io.Serializable;

import Battle.*;
import Character.*;
import Dice.Roll;
import Field.Store;
import ItemList.*;

public class Script implements Color, Serializable {
	StringBuilder strb = new StringBuilder();
	
	public StringBuilder getStrb() {
		return strb;
	}

	public String getChooseJob() {
		String str="";
		str+="------------------------------------------------<br>";
		str+="<br>";
		str+="주사위 던전에 온신걸 환영합니다<br>";
		str+="<br>";
		str+="------------------------------------------------<br>";
		str+="클래스를 선택하세요<br>";
		str+="(1.전사 2.도적 3.궁수 4.마법사 5.기사)<br>";
		str+="------------------------------------------------<br>";
		str+="<br>";
		return str;
	}
	
	public String getChooseItem(String job) {
		String str="";
		switch (job) {
		case "전사" :
			str +="장비를 선택하세요<br>";
			str +="1.[검, 도끼, 변경]<br>";
			str +="2.[카타나, 부메랑, 희생]<br>";
			break;
		case "도적" :
			str +="장비를 선택하세요<br>";
			str +="1.[단검, 짧은활, 락픽]<br>";
			str +="2.[맹독, 독칼, 자르기]<br>";
			break;
		case "궁수" :
			str +="장비를 선택하세요<br>";
			str +="1.[장궁, 짧은검, 정밀]<br>";
			str +="2.[석궁, 함정, 위조]<br>";
			break;
		case "마법사" :
			str +="장비를 선택하세요<br>";
			str +="1.[완드, 얼음파편, 거울]<br>";
			str +="2.[스태프, 눈폭풍, 두사위]<br>";
			break;
		case "기사" :
			str +="장비를 선택하세요<br>";
			str +="1.[격돌, 가시방패, 막기]<br>";
			str +="2.[성검, 패링, 매직쉴드]<br>";
			break;
		}		
		return str;
	}
	
	public String selectTurnEndStr() {
		String str = "- 턴 종료 -";
		return str;
	}
	
	public void printDamagedIce() {
		strb.append("&nbsp* 주사위가 얼어붙습니다. 주사위 한개의 눈금이 1로 변합니다 *&nbsp<br><br>");
	}
	
	public String printCheckTrueStr() {
		return " * 사용할 수 없습니다 * <br>";
	}
	
	public void printStore() {
		strb.append("상점에 방문했습니다<br>");
		strb.append("상품이 중복으로 보이는건 기분 탓입니다<br>");
		strb.append("(한번만 교환가능합니다)<br><br>");
	}
	
	public void printForge() {
		strb.append("대장간에 방문했습니다<br>");
		strb.append("(한층마다 한번만 교환가능합니다)<br><br>");		
	}
	
	public void changeAlready() {
		strb.append("이미 교환완료 했습니다<br><br>");
		
	}
	public void useAlready() {
	strb.append("지금은 이용할 수 없습니다<br><br>");
	}
	
	public void downFloor() {
		strb.append("*** 한 층 더 아래로 내려갑니다 ***<br><br>");
	}
	
	public void ending() {
		strb.append("축하합니다.<br><br>");
		strb.append("*** 모든 몬스터를 쓰러트렸습니다 ***<br><br>");
	}
}
