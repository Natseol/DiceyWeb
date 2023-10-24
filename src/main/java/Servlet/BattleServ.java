package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Character.Enemy;
import Character.Player;
import Character.Skill;
import Field.Field;
import Main.Script;
import db.ItemDAO;
import db.ItemVO;

/**
 * Servlet implementation class BattleServ
 */
@WebServlet("/battleserv")
public class BattleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Script script;		
	Player player;
	Enemy[] enemy;
	
	int floor;		
	int enemyNum;
	Field field;
	
	MyTurn myTurn;
	EnemyTurn enemyTurn;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("배틀서브 생성");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
		
        if (session.getAttribute("player") != null) {
        	System.out.println("전달됨");
    		player = (Player) session.getAttribute("player");
    		enemy = (Enemy[]) session.getAttribute("enemy");
    		floor = (int) session.getAttribute("floor");
    		enemyNum = (int) session.getAttribute("enemyNum");
    		field = (Field) session.getAttribute("field");
    		session.removeAttribute("player");
    		
//    		enemyNum=17;//test
    		
    		enemy[enemyNum].setHp(enemy[enemyNum].getMaxHp());
    		enemy[enemyNum].resetCondition();
    		myTurn = new MyTurn(player);
    		enemyTurn = new EnemyTurn(enemy[enemyNum]);
        }
		
//		player.setCondition(0,2);
//		player.setCondition(1,2);
//		player.setCondition(2,2);
//		player.setCondition(3,2);
        
//		player.setLevel(5);
//		player.setSp(12);
//		player.setExp(4);
//		player.setHp(2);
//		player.setDef(4);
		enemy[enemyNum].setHp(2);
//		field.setForgeCount(1);

//		enemy[enemyNum].setCondition(0,4);
//		enemy[enemyNum].setCondition(1,2);
//		enemy[enemyNum].setCondition(2,2);
//		enemy[enemyNum].setCondition(3,2);
        
		System.out.println("batlle GET 연결됨");
		response.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        
        putJsonData(jsonData);
        jsonData.put("field", field);
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("batlle POST 연결됨");
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
        
        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
        
        if (jsonNode.get("endStage")!=null) {
        	player.resetPlayer();
        	
    		HttpSession session = request.getSession();
    		
    		enemyNum++;
    		session.setAttribute("player", player);
    		session.setAttribute("enemy", enemy);
    		session.setAttribute("floor", floor);
    		session.setAttribute("enemyNum", enemyNum);
    		session.setAttribute("field", field);
    		
    		response.sendRedirect("fieldserv");
    		return;
        }
        
        if (jsonNode.get("magicDice")!=null) {
        	int dice = jsonNode.get("magicDice").asInt();
        	Skill.skillMag(player, myTurn, dice);
        } else if (jsonNode.get("isUseSkill").asText().equals("true")) {
//        	String param = jsonNode.get("isUseSkill").asText();
//        	System.out.println("isUseSkill: " + param);
        	Skill.useSkill(player, enemy[enemyNum], myTurn);
        } else {
        // 각각의 파라미터로 저장
        int param1 = jsonNode.get("idxDice").asInt();
        int param2 = jsonNode.get("idxItem").asInt();

        // 변수 확인
        System.out.println("idxDice: " + param1);
        System.out.println("idxItem: " + param2);

        myTurn.doMyTurnLoop(player, enemy[enemyNum], enemyTurn, param1, param2);
        }
        
        if (player.getHp()<1) {
        	myTurn.getTurnScript().add("<br>&nbspYOU DIED&nbsp");
        } else if (enemy[enemyNum].getHp()<1) {
        	myTurn.getTurnScript().add("<br>&nbsp승리&nbsp");
        	myTurn.getTurnScript().add(player.levelUpStr());
        }
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();        
        
        putJsonData(jsonData);
        jsonData.put("script", myTurn.getTurnScript());
        jsonData.put("skillScript", Skill.getStrb().toString());
        Skill.getStrb().setLength(0);
                
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("batlle PUT 연결됨");
		response.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        
        if (myTurn.getIsTurn()) {
        	player.resetFireStack();
        	myTurn.changeTurn();
        	enemyTurn.getTurnScript().add("= 턴 시작 =<br><br>");
        	enemyTurn.startTurn(enemy[enemyNum]);
        } else {        
        	enemyTurn.doEnemyTurnLoop(player, enemy[enemyNum], myTurn);
        }
        if (myTurn.getIsTurn()) {
        	enemy[enemyNum].resetFireStack();
        	enemyTurn.getTurnScript().add("<br>= 턴 종료 =<br><br>");
        	myTurn.startTurn(player);
        	enemyTurn.getTurnScript().addAll(myTurn.getTurnScript());
        }
        System.out.println("내턴:"+myTurn.getIsTurn());
        System.out.println("적턴:"+enemyTurn.getIsTurn());
        
        if (player.getHp()<1) {
        	enemyTurn.getTurnScript().add("<br>YOU DIED");
        } else if (enemy[enemyNum].getHp()<1) {
        	enemyTurn.getTurnScript().add("<br>승리");
        	enemyTurn.getTurnScript().add(player.levelUpStr());
        }
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();        
        
        putJsonData(jsonData);
        jsonData.put("script", enemyTurn.getTurnScript());
                
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}
	
	private void putJsonData(Map<String, Object> jsonData) {
		jsonData.put("player", player);
        jsonData.put("enemy", enemy[enemyNum]);
        jsonData.put("myTurn", myTurn);
        jsonData.put("enemyTurn", enemyTurn);
        jsonData.put("enemyNum", enemyNum);
	}
}
