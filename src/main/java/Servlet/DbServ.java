package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import db.ItemDAO;
import db.ItemVO;

/**
 * Servlet implementation class DbServ
 */
@WebServlet("/dbserv")
public class DbServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DbServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DB 생성");
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
		System.out.println("DB get");
		response.setCharacterEncoding("UTF-8");
		
		ItemDAO dao = new ItemDAO();
		List<ItemVO> list = dao.getList();
		
        ObjectMapper objectMapper = new ObjectMapper();
        
//        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
//        String param1 = jsonNode.get("jobNum").asText();
        
        // JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        
        jsonData.put("list", list);
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DB POST");
		response.setCharacterEncoding("UTF-8");
		
		ItemDAO dao = new ItemDAO();
		List<ItemVO> list = dao.getList();
		
        ObjectMapper objectMapper = new ObjectMapper();
        
//        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
//        String param1 = jsonNode.get("jobNum").asText();
        
        // JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        
        jsonData.put("list", list);
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);	
	}
}
