package Servlet;

import java.io.IOException;
import java.util.HashMap;
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
import Main.Color;
import Main.Script;

/**
 * Servlet implementation class FieldServ
 */
//@WebServlet("/fieldserv")
public class FieldServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Script script = new Script();		
	Player player = new Player();
	Enemy[] enemy = Enemy.enemyList();
	
	int floor=1;		
	int enemyNum=0;
	Field field;	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("플레이어 생성, 직업선택");
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
		System.out.println("field GET 연결됨");
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		
        HttpSession session = request.getSession(false);
        
        if (session.getAttribute("player") != null) {
        	System.out.println("전달됨");
    		player = (Player) session.getAttribute("player");
    		session.removeAttribute("player");
    		enemy = (Enemy[]) session.getAttribute("enemy");
    		floor = (int) session.getAttribute("floor");
    		enemyNum = (int) session.getAttribute("enemyNum");
    		field = (Field) session.getAttribute("field");
    		System.out.println("적넘버"+enemyNum);
    		if (enemyNum==4||enemyNum==7||enemyNum==10||enemyNum==14||enemyNum==17) {
    			field.createField();
    			field.setForgeCount(1);
    		}
    		if (enemyNum==12||enemyNum==16) {
    			int num = field.getForgeCount();
    			field.createField();
    			field.setForgeCount(num);
    		}
        }

		// JSON 데이터를 생성
		Map<String, Object> jsonData = new HashMap<>();

		jsonData.put("player", player);
		jsonData.put("enemy", enemy[enemyNum]);
		jsonData.put("field", field.getStore());
		jsonData.put("script", script);

		// JSON 데이터를 클라이언트에게 전송
		response.setContentType("application/json");
		objectMapper.writeValue(response.getWriter(), jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("field POST 연결됨");
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
        
        if (jsonNode.get("endStage")!=null) {
        	System.out.println("포장중");
    		HttpSession session = request.getSession();
    		
    		session.setAttribute("player", player);
    		session.setAttribute("enemy", enemy);
    		session.setAttribute("enemyNum", enemyNum);
    		session.setAttribute("field", field);
    		session.setAttribute("floor", floor);
    		
    		response.sendRedirect("battleserv");
    		return;
        }
		
		if (jsonNode.get("store")!=null) {
	        String param1 = jsonNode.get("store").asText();
	        String param2 = jsonNode.get("forge").asText();
	        String param3 = jsonNode.get("well").asText();
	        int param4 = jsonNode.get("idxItem").asInt();
	        int param5 = jsonNode.get("idxStore").asInt();
	        
	        if (param1.equals("true")) {
	        	if (field.getStoreCount()>0) {
	        		player.setInventory(param4,field.getStore().getStoreList(param5));
	        		field.getStore().setStoreItem(param5);
	        		field.setStoreCount(field.getStoreCount()-1);
	        	} else {
	        		script.getStrb().append("모든 횟수를 소진 했습니다");
	        	}
	        } else if (param2.equals("true")) {
	    		if (player.getInventory(param4).getEnhName().equals("빈슬롯")
	    				||player.getInventory(param4).getName().endsWith("(강화)")) {
	    			script.getStrb().append("선택할 수 없습니다");
	    		} else if (field.getForgeCount()>0) {
	        		player.getInventory(param4).enhance();
	        		field.setForgeCount(field.getForgeCount()-1);	
	        	} else {
	        		script.getStrb().append("모든 횟수를 소진 했습니다");
	        	}
	        } else if (param3.equals("true")) {
	        	if (field.getHealCount()>0) {
		        	player.addHp(4+player.getLevel());
					field.setHealCount(field.getHealCount()-1);
					script.getStrb().append(" * 체력을 "+(4+player.getLevel())+" 회복했습니다 *");
	        	} else {
	        		script.getStrb().append("모든 횟수를 소진 했습니다");
	        	}
	        }	        
		}

		// JSON 데이터를 생성
		Map<String, Object> jsonData = new HashMap<>();

		jsonData.put("player", player);
		jsonData.put("enemy", enemy[enemyNum]);
		jsonData.put("field", field);
		jsonData.put("script", script);

		// JSON 데이터를 클라이언트에게 전송
		response.setContentType("application/json");
		objectMapper.writeValue(response.getWriter(), jsonData);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
