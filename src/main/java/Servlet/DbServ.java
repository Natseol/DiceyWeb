package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
        
        int id = jsonNode.get("id").asInt();
        System.out.println("id 확인"+id);
        if (id>0) {
        	dao.deleteItem(id);
        } else {
        	String name = jsonNode.get("name").asText();
        	String description = jsonNode.get("description").asText();
			int attack = jsonNode.get("attack").asInt();
			int addAttack = jsonNode.get("addAttack").asInt();
			int count = jsonNode.get("count").asInt();    
			String limit = jsonNode.get("limit").asText();
			int times = jsonNode.get("times").asInt();
			int use = jsonNode.get("use").asInt();
			int needDice = jsonNode.get("needDice").asInt();
			String activeLimit = jsonNode.get("activeLimit").asText();
			int fireStack = jsonNode.get("fireStack").asInt();
			int iceStack = jsonNode.get("iceStack").asInt();
			int elecStack = jsonNode.get("elecStack").asInt();
			int poisonStack = jsonNode.get("poisonStack").asInt();
			int recovery = jsonNode.get("recovery").asInt();
			int defence = jsonNode.get("defence").asInt();
			int damage = jsonNode.get("damage").asInt();
			String newDice = jsonNode.get("newDice").asText();
						
        	String enhName = jsonNode.get("enhName").asText();
        	String enhDescription = jsonNode.get("enhDescription").asText();
			int enhAttack = jsonNode.get("enhAttack").asInt();
			int enhAddAttack = jsonNode.get("enhAddAttack").asInt();
			int enhCount = jsonNode.get("enhCount").asInt();    
			String enhLimit = jsonNode.get("enhLimit").asText();
			int enhTimes = jsonNode.get("enhTimes").asInt();
			int enhUse = jsonNode.get("enhUse").asInt();
			int enhNeedDice = jsonNode.get("enhNeedDice").asInt();
			String enhActiveLimit = jsonNode.get("enhActiveLimit").asText();
			int enhFireStack = jsonNode.get("enhFireStack").asInt();
			int enhIceStack = jsonNode.get("enhIceStack").asInt();
			int enhElecStack = jsonNode.get("enhElecStack").asInt();
			int enhPoisonStack = jsonNode.get("enhPoisonStack").asInt();
			int enhRecovery = jsonNode.get("enhRecovery").asInt();
			int enhDefence = jsonNode.get("enhDefence").asInt();
			int enhDamage = jsonNode.get("enhDamage").asInt();
			String enhNewDice = jsonNode.get("enhNewDice").asText();
			
			dao.createItem(name, description, attack, addAttack, count, limit, times,
					use, needDice, activeLimit, fireStack, iceStack, elecStack, poisonStack,
					recovery, defence, damage, newDice,
					enhName, enhDescription, enhAttack,	
					enhAddAttack, enhCount, enhLimit, enhTimes, enhUse, enhNeedDice,
					enhActiveLimit, enhFireStack, enhIceStack,
					enhElecStack, enhPoisonStack, enhRecovery,
					enhDefence, enhDamage, enhNewDice);
		}
                
        // JSON 데이터를 생성
        Map<String, Object> jsonData = new HashMap<>();
        list = dao.getList();
        jsonData.put("list", list);
        
        // JSON 데이터를 클라이언트에게 전송
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), jsonData);
	}
}
