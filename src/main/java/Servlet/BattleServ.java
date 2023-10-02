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
import Field.Field;
import Main.Script;

/**
 * Servlet implementation class BattleServ
 */
@WebServlet("/battleserv")
public class BattleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Script script = new Script();		
	Player player = new Player();
	Enemy[] enemy = Enemy.enemyList();
	
	int floor=1;		
	int enemyNum=0;
	Field field = new Field();
	
	MyTurn myTurn = new MyTurn(player);
	EnemyTurn enemyTurn = new EnemyTurn(enemy[enemyNum]);

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("배틀서브 생성");
		
		player.chooseJob(1);
		player.setJobItem(player.getJob(), 1);
		myTurn.changeTurn();
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
        }
		
		System.out.println("batlle GET 연결됨");
		response.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
    
		myTurn.startTurn(player);
		enemyTurn.startTurn(enemy[enemyNum]);
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        
        jsonData.put("player", player);
        jsonData.put("enemy", enemy[enemyNum]);
        jsonData.put("myTurn", myTurn);
        jsonData.put("enemyTurn", enemyTurn);
                
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

        // 각각의 파라미터로 저장
        String param1 = jsonNode.get("idxDice").asText();
        String param2 = jsonNode.get("idxItem").asText();

        // 변수 확인
        System.out.println("idxDice: " + param1);
        System.out.println("idxItem: " + param2);

        myTurn.doMyTurnLoop(player, enemy[enemyNum], enemyTurn, Integer.parseInt(param1), Integer.parseInt(param2));
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();        
        
        jsonData.put("player", player);
        jsonData.put("enemy", enemy[enemyNum]);
        jsonData.put("myTurn", myTurn);
        jsonData.put("isMyTurn", myTurn.getIsTurn());
        jsonData.put("isEnemyTurn", enemyTurn.getIsTurn());
        jsonData.put("enemyTurn", enemyTurn);
        jsonData.put("script", myTurn.getTurnScript());
                
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
        
//        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
//
//        // 각각의 파라미터로 저장
//        String param1 = jsonNode.get("idxDice").asText();
//        String param2 = jsonNode.get("idxItem").asText();
//
//        // 변수 확인
//        System.out.println("idxDice: " + param1);
//        System.out.println("idxItem: " + param2);
        System.out.println("내턴:"+myTurn.getIsTurn());
        System.out.println("적턴:"+enemyTurn.getIsTurn());
        
        if (myTurn.getIsTurn()) {
        	myTurn.changeTurn();
        	enemyTurn.changeTurn();
        	enemyTurn.resetTurnScript();
        	enemyTurn.getTurnScript().add("= 턴 시작 =<br>");
        	enemyTurn.startTurn(enemy[enemyNum]);
        	enemyTurn.resetTimes(enemy[enemyNum].getInventory());	
        } else {        
        	enemyTurn.doEnemyTurnLoop(player, enemy[enemyNum], myTurn);
        }
        if (!enemyTurn.getIsTurn()) {
        	enemyTurn.getTurnScript().add("= 턴 종료 =<br>");
        	myTurn.startTurn(player);
        	myTurn.resetTimes(player.getInventory());
        }
        System.out.println("내턴:"+myTurn.getIsTurn());
        System.out.println("적턴:"+enemyTurn.getIsTurn());
        
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();        
        
        jsonData.put("player", player);
        jsonData.put("enemy", enemy[enemyNum]);
        jsonData.put("myTurn", myTurn);
        jsonData.put("enemyTurn", enemyTurn);
        jsonData.put("script", enemyTurn.getTurnScript());
                
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}
}