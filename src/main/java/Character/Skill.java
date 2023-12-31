package Character;

import Battle.MyTurn;
import Dice.Roll;

public class Skill {
	
	static StringBuilder strb = new StringBuilder();
	
	public static StringBuilder getStrb() {
		return strb;
	}

	public static void setStrb(StringBuilder strb) {
		Skill.strb = strb;
	}

	public static void useSkill(Player player, Enemy enemy, MyTurn myturn) {
		if (player.getSp()>10+player.getLevel()) {
			switch (player.getJob()) {
			case "전사":
				strb.append("[분노] 스킬을 사용합니다<br>");
				strb.append("다음 장비 사용을 한번 더 반복합니다");
				if (player.getLevel()>2) {
					strb.append(" (무작위 상태이상 해제)<br>");
					skillWar(player,player.getLevel()-2);
				}
				strb.append("<br>");
				player.setIsUseSkill(true);			
			break;
			case "도적":
				strb.append("[속임수] 스킬을 사용합니다<br>");
				strb.append("추가 주사위를 생성합니다<br><br>");
				for (int i = 0; i<2+player.getLevel();i++) {
					myturn.getDiceList().add(1);
				}
				break;			
			case "궁수":
				strb.append("[빠른 손놀림] 스킬을 사용합니다<br>");
				strb.append("모든 장비의 카운트를 낮춥니다<br><br>");
				for (int i=0;i<myturn.getItem().length;i++) {
					if (myturn.getCountState(i)>0) {
						myturn.setCountState(i, myturn.getCountState(i)-(2+player.getLevel()));
					}
				}
				break;				
			case "마법사":
				strb.append("[창조] 스킬을 사용합니다<br>");
				strb.append("생성하고 싶은 주사위의 눈금을 적으세요<br>");	
				break;
			case "기사":
				strb.append("[신의은총] 스킬을 사용합니다<br>");
				strb.append("방어력이 증가하고 적에게 모든 상태이상을 겁니다<br><br>");
				for (int i=0;i<enemy.getCondition().length;i++) {
					enemy.setCondition(i, enemy.getCondition(i)+1);
				}
				if (player.getLevel()>3) {
					player.setDef(player.getDef()+6);
					strb.append("6 의 방어력을 얻었습니다!!<br>");
					player.addHp(player.getLevel()-3);
					strb.append((player.getLevel()-3)+" 의 체력을 회복했습니다<br>");
				}
				else {
					player.setDef(player.getDef()+3+player.getLevel());				
					strb.append((player.getLevel()+3+player.getLevel())+" 의 방어력을 얻었습니다!!<br>");
				}
				break;
			}
			player.setSp(0);
		}
	}

	private static void skillWar(Player player, int num) {
		int count=0;
		int check;
		while (count<num) {
			int idx=Roll.random(4)-1;
			check=0;
			if (player.getCondition(idx)>0) {
				player.setCondition(idx, player.getCondition(idx)-1);
				count++;
			}
			for (int i =0; i<player.getCondition().length;i++) {
				if (player.getCondition(i)==0) {
					check++;
				}
			}
			if (check==player.getCondition().length) break;
		}
	}
	
	public static void skillMag(Player player, MyTurn myTurn, int dice) {
		if (dice < 5+player.getLevel()&&0<dice) {
			myTurn.getDiceList().add(dice);
			strb.append("눈금 ["+dice+"] 주사위를 생성합니다<br><br>");
		}
		else {
			strb.append("원하는 눈금 창조에 실패했습니다<br><br>");
			myTurn.getDiceList().add(Roll.roll6());
		}
	}
}
