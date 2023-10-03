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

import com.fasterxml.jackson.databind.ObjectMapper;

import Battle.EnemyTurn;
import Battle.MyTurn;
import Character.Enemy;
import Character.Player;
import Field.Field;
import Main.Script;

/**
 * Servlet implementation class FieldServ
 */
@WebServlet("/fieldserv")
public class FieldServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Script script = new Script();		
	Player player = new Player();
	Enemy[] enemy = Enemy.enemyList();
	
	int floor=1;		
	int enemyNum=0;
	Field field = new Field();	

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		HttpSession session = request.getSession(false);
//
//		if (session.getAttribute("player") != null) {
//			System.out.println("전달됨");
//			player = (Player) session.getAttribute("player");
//			enemy = (Enemy[]) session.getAttribute("enemy");
//			floor = (int) session.getAttribute("floor");
//			enemyNum = (int) session.getAttribute("enemyNum");
//			field = (Field) session.getAttribute("field");
//		}

		System.out.println("field GET 연결됨");
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();

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
		// TODO Auto-generated method stubresponse.getWriter().append("Served at: ").append(request.getContextPath());
//		HttpSession session = request.getSession(false);
//
//		if (session.getAttribute("player") != null) {
//			System.out.println("전달됨");
//			player = (Player) session.getAttribute("player");
//			enemy = (Enemy[]) session.getAttribute("enemy");
//			floor = (int) session.getAttribute("floor");
//			enemyNum = (int) session.getAttribute("enemyNum");
//			field = (Field) session.getAttribute("field");
//		}
		player.chooseJob(1);
		player.setJobItem(player.getJob(), 1);
		System.out.println("플레이어 생성, 직업선택");
		System.out.println(player.getInventoryName(0));

		System.out.println("field POST 연결됨");
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();

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
