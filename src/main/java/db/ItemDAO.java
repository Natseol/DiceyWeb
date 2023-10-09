package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ItemDAO implements Serializable {
	private Connection con;
	
	public List<ItemVO> getList() {
		List<ItemVO> list = new ArrayList<>();
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe","java","qwer");
//			connect();
			
			String query = "select * from item";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while(rs.next()) {
				list.add(createListRow(rs));
			}
			rs.close();
			pstmt.close();
			con.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ItemVO getItem(int id) {
		ItemVO temp = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe","java","qwer");
//			connect();

			String query = "select * from item where id=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {//로우를 가져옴
				temp = createListRow(rs);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public void createItem(String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice) {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe","java","qwer");
//			connect();
			
			String insertQuery = "insert into item (name, description, attack, addAttack, count,"
					+ " limit, times, use, needDice, activeLimit, fireStack, iceStack, elecStack,"
					+ " poisonStack, recovery, defence, damage, newDice)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, attack);
			pstmt.setInt(4, addAttack);
			pstmt.setInt(5, count);
			pstmt.setString(6, limit);
			pstmt.setInt(7, times);
			pstmt.setInt(8, use);
			pstmt.setInt(9, needDice);
			pstmt.setString(10, activeLimit);
			pstmt.setInt(11, fireStack);
			pstmt.setInt(12, iceStack);
			pstmt.setInt(13, elecStack);
			pstmt.setInt(14, poisonStack);
			pstmt.setInt(15, recovery);
			pstmt.setInt(16, defence);
			pstmt.setInt(17, damage);
			pstmt.setString(18, newDice);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateItem(int id, String name, String description, int attack, int addAttack, int count, String limit, int times,
			int use, int needDice, String activeLimit, int fireStack, int iceStack, int elecStack, int poisonStack,
			int recovery, int defence, int damage, String newDice) {
		try {
			connect();
			
			String insertQuery = "update item set name=?, description=?, attack=?, addAttack=?, count,"
					+ "limit=?, times=?, use=?, needDice=?, activeLimit=?, fireStack=?, iceStack=?, elecStack=?,"
					+ "poisonStack=?, recovery=?, defence=?, damage=?, newDice=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, attack);
			pstmt.setInt(4, addAttack);
			pstmt.setInt(5, count);
			pstmt.setString(6, limit);
			pstmt.setInt(7, times);
			pstmt.setInt(8, use);
			pstmt.setInt(9, needDice);
			pstmt.setString(10, activeLimit);
			pstmt.setInt(11, fireStack);
			pstmt.setInt(12, iceStack);
			pstmt.setInt(13, elecStack);
			pstmt.setInt(14, poisonStack);
			pstmt.setInt(15, recovery);
			pstmt.setInt(16, defence);
			pstmt.setInt(17, damage);
			pstmt.setString(18, newDice);
			pstmt.setInt(19, id);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItem(int id) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe","java","qwer");
//			connect();
			
			String insertQuery = "delete from item where id=?";
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private ItemVO createListRow(ResultSet rs) {
		ItemVO temp = null;
		try {
			temp = new ItemVO(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getInt("attack"),
					rs.getInt("addattack"),
					rs.getInt("count"),
					rs.getString("limit"),
					rs.getInt("times"),
					rs.getInt("use"),
					rs.getInt("needdice"),
					rs.getString("activelimit"),
					rs.getInt("firestack"),
					rs.getInt("icestack"),
					rs.getInt("elecstack"),
					rs.getInt("poisonstack"),
					rs.getInt("recovery"),
					rs.getInt("defence"),
					rs.getInt("damage"),
					rs.getString("newdice"),
					rs.getInt("accumulation")
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	private void connect() throws Exception{
		Context ctx = new InitialContext();
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		con = dataFactory.getConnection();
	}
}
