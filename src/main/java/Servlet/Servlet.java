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

import com.fasterxml.jackson.databind.ObjectMapper;

import Character.*;
import Main.*;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Player player = new Player(1);
	
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
		response.setContentType("text/html;charset=UTF-8");
		player.setJobItem(player.getJob(), 1);
		Script script = new Script();
		
		// JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        
        player.subtractHp(2);
        String changedhp = Integer.toString(player.getHp());
                
        jsonData.put("name", "John");
        jsonData.put("age", changedhp);
        jsonData.put("player", player);
        jsonData.put("script", script.getChooseJob());
                
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), jsonData);
        
        //참고할 것
//        // 자바 객체를 생성하고 데이터 설정
//        Person person1 = new Person("John", 30);
//        Person person2 = new Person("Alice", 25);
//
//        // Jackson ObjectMapper를 사용하여 객체를 JSON 문자열로 직렬화
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json1 = objectMapper.writeValueAsString(person1);
//        String json2 = objectMapper.writeValueAsString(person2);
//
//        // JSON 문자열 출력 또는 전송
//        System.out.println("JSON Person 1: " + json1);
//        System.out.println("JSON Person 2: " + json2);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
