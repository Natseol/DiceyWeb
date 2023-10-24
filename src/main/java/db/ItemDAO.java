package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
			connectTomcat();
			
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
			connectTomcat();

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
			int recovery, int defence, int damage, String newDice,
			String enhName, String enhDescription, int enhAttack,	
			int enhAddAttack, int enhCount, String enhLimit, int enhTimes, int enhUse, int enhNeedDice,
			String enhActiveLimit, int enhFireStack, int enhIceStack,
			int enhElecStack, int enhPoisonStack, int enhRecovery,
			int enhDefence, int enhDamage, String enhNewDice) {
		
		try {
			connectTomcat();
			
			  String insertQuery = "insert into item (name, description, attack, addAttack, count, limit, times,"
					+ " use, needDice, activeLimit, fireStack, iceStack, elecStack, poisonStack,"
					+ " recovery, defence, damage, newDice,"
					+ " enhName, enhDescription, enhAttack,"
					+ " enhAddAttack, enhCount, enhLimit, enhTimes, enhUse, enhNeedDice,"
					+ " enhActiveLimit, enhFireStack, enhIceStack,"
					+ " enhElecStack, enhPoisonStack, enhRecovery,"
					+ " enhDefence, enhDamage, enhNewDice)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			pstmt.setString(19, enhName);
			pstmt.setString(20, enhDescription);
			pstmt.setInt(21, enhAttack);
			pstmt.setInt(22, enhAddAttack);
			pstmt.setInt(23, enhCount);
			pstmt.setString(24, enhLimit);
			pstmt.setInt(25, enhTimes);
			pstmt.setInt(26, enhUse);
			pstmt.setInt(27, enhNeedDice);
			pstmt.setString(28, enhActiveLimit);
			pstmt.setInt(29, enhFireStack);
			pstmt.setInt(30, enhIceStack);
			pstmt.setInt(31, enhElecStack);
			pstmt.setInt(32, enhPoisonStack);
			pstmt.setInt(33, enhRecovery);
			pstmt.setInt(34, enhDefence);
			pstmt.setInt(35, enhDamage);
			pstmt.setString(36, enhNewDice);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItem(int id) {
		try {
			connectTomcat();
			
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
	
	public void createTable() {
		try {
			connectTomcat();
			
		    if (!doesTableExist("ITEM")) {
		    	String insertQuery ="create TABLE ITEM"
						+" (ID number(10, 0) generated as identity constraint pk_item_id primary key,"
						+"	NAME VARCHAR2(50),"
						+"	DESCRIPTION VARCHAR2(100),"
						+"	ATTACK number(10, 0),"
						+"	ADDATTACK number(10, 0),"
						+"	COUNT number(10, 0),"
						+"	LIMIT VARCHAR2(20),"
						+"	TIMES number(10, 0) DEFAULT 1,"
						+"	USE number(10, 0),"
						+"	NEEDDICE number(10, 0),"
						+"	ACTIVELIMIT VARCHAR2(20),"
						+"	FIRESTACK number(10, 0),"
						+"	ICESTACK number(10, 0),"
						+"	ELECSTACK number(10, 0),"
						+"	POISONSTACK number(10, 0),"
						+"	RECOVERY number(10, 0),"
						+"	DEFENCE number(10, 0),"
						+"	DAMAGE number(10, 0),"
						+"	NEWDICE VARCHAR2(20),"
						+"	ACCUMULATION number(10, 0),"
						+"	ENHNAME VARCHAR2(50),"
						+"	ENHDESCRIPTION VARCHAR2(100),"
						+"	ENHATTACK number(10, 0),"
						+"	ENHADDATTACK number(10, 0),"
						+"	ENHCOUNT number(10, 0),"
						+"	ENHLIMIT VARCHAR2(20),"
						+"	ENHTIMES number(10, 0) DEFAULT 1,"
						+"	ENHUSE number(10, 0),"
						+"	ENHNEEDDICE number(10, 0),"
						+"	ENHACTIVELIMIT VARCHAR2(20),"
						+"	ENHFIRESTACK number(10, 0),"
						+"	ENHICESTACK number(10, 0),"
						+"	ENHELECSTACK number(10, 0),"
						+"	ENHPOISONSTACK number(10, 0),"
						+"	ENHRECOVERY number(10, 0),"
						+"	ENHDEFENCE number(10, 0),"
						+"	ENHDAMAGE number(10, 0),"
						+"	ENHNEWDICE VARCHAR2(20),"
						+"	ENHACCUMULATION number(10, 0))";
		        PreparedStatement pstmt = con.prepareStatement(insertQuery);
		        pstmt.executeUpdate();

		        pstmt.close();
		        con.close();
		    }
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
					rs.getInt("needDice"),
					rs.getString("activeLimit"),
					rs.getInt("fireStack"),
					rs.getInt("iceStack"),
					rs.getInt("elecStack"),
					rs.getInt("poisonStack"),
					rs.getInt("recovery"),
					rs.getInt("defence"),
					rs.getInt("damage"),
					rs.getString("newdice"),
					rs.getString("enhName"),
					rs.getString("enhDescription"),
					rs.getInt("enhAttack"),
					rs.getInt("enhAddAttack"),
					rs.getInt("enhCount"),
					rs.getString("enhLimit"),
					rs.getInt("enhTimes"),
					rs.getInt("enhUse"),
					rs.getInt("enhNeedDice"),
					rs.getString("enhActiveLimit"),
					rs.getInt("enhFireStack"),
					rs.getInt("enhIceStack"),
					rs.getInt("enhElecStack"),
					rs.getInt("enhPoisonStack"),
					rs.getInt("enhRecovery"),
					rs.getInt("enhDefence"),
					rs.getInt("enhDamage"),
					rs.getString("enhNewDice")
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	private void connectTomcat() throws Exception{
		Context ctx = new InitialContext();
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		DataSource dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		con = dataFactory.getConnection();
	}
	
	private void connect() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/xe","java","qwer");
	}
	
	private boolean doesTableExist(String tableName) throws SQLException {
	    DatabaseMetaData metadata = con.getMetaData();
	    ResultSet tables = metadata.getTables(null, null, tableName, null);
	    return tables.next();
	}
}
