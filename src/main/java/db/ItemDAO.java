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
			if (rs.next()) {//컬럼을 가져옴
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
	
	private ItemVO createListRow(ResultSet rs) {
		ItemVO temp = null;
		try {
			temp = new ItemVO(
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
