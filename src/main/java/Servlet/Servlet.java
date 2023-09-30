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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Character.*;
import Field.Field;
import Main.*;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Script script = new Script();		
	Player player = new Player();
	Enemy[] enemy = Enemy.enemyList();
	
	int floor=1;		
	int eNum=0;
	Field field = new Field();
	
	MyTurn myTurn = new MyTurn(player);
	EnemyTurn enemyTurn = new EnemyTurn(enemy[eNum]);;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		
		System.out.println("GET 연결됨");
		
		response.setContentType("text/html;charset=UTF-8");
		player.chooseJob(1);
		player.setJobItem(player.getJob(), 1);
		 
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
                
        jsonData.put("player", player);
        jsonData.put("script", script.getChooseJob());
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), jsonData);
        objectMapper.writeValue(response.getWriter(), jsonData);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("POST 연결됨");
		response.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        
        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());

        // 각각의 파라미터로 저장
        String param1 = jsonNode.get("jobNum").asText();
        String param2 = jsonNode.get("equipmentNum").asText();

        // 변수에 저장한 후 필요한 작업을 수행
        System.out.println("jobNum: " + param1);
        System.out.println("equipmentNum: " + param2);
		
		player.chooseJob(Integer.parseInt(param1));
		player.setJobItem(player.getJob(), 1);
		 
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
                
        jsonData.put("player", player);
        jsonData.put("script", script.getChooseItem(player.getJob()));
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}

}
