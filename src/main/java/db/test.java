package db;

import java.util.List;

public class test {
	public static void main(String[] args){
		ItemDAO DAO = new ItemDAO();
		List<ItemVO> list=DAO.getList();
		
		for (ItemVO boardVO : list) {
			System.out.println(boardVO.getName());
		}
		
	}
}
