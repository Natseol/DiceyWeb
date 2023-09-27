package Main;

import Dice.Roll;

public interface Color {
	public static final String RESET = "\u001B[0m";
	public static final String BOLD = "\u001B[1m";
	
	public static final String BLACK = "\u001B[30m";	//기본   
	public static final String RED = "\u001B[31m";		//화염     
	public static final String GREEN = "\u001B[32m";    //
	public static final String YELLOW = "\u001B[33m";   //선택지
	public static final String BLUE = "\u001B[34m";	   
	public static final String PURPLE = "\u001B[35m";	//마비     
	public static final String CYAN = "\u001B[36m";		//냉기    
	public static final String WHITE = "\u001B[37m";
	
	public static final String BBLACK = "\u001B[90m";	  
	public static final String BRED = "\u001B[91m";		   
	public static final String BGREEN = "\u001B[92m";    
	public static final String BYELLOW = "\u001B[93m";   
	public static final String BBLUE = "\u001B[94m";	   
	public static final String BPURPLE = "\u001B[95m";	     
	public static final String BCYAN = "\u001B[96m";	    
	public static final String BWHITE = "\u001B[97m";
	
	public static final String B_BLACK = "\u001B[40m"; 	    
	public static final String B_RED = "\u001B[41m";    //피해 
	public static final String B_GREEN = "\u001B[42m"; 	//회복   
	public static final String B_YELLOW = "\u001B[43m"; //방어력     
	public static final String B_BLUE = "\u001B[44m";    
	public static final String B_PURPLE = "\u001B[45m";     
	public static final String B_CYAN = "\u001B[46m";     
	public static final String B_WHITE = "\u001B[47m";
	
	public static final String B_BBLACK = "\u001B[40m"; 	    
	public static final String B_BRED = "\u001B[41m";    //피해 
	public static final String B_BGREEN = "\u001B[42m"; 	//회복   
	public static final String B_BYELLOW = "\u001B[43m"; //방어력     
	public static final String B_BBLUE = "\u001B[44m";    
	public static final String B_BPURPLE = "\u001B[45m";     
	public static final String B_BCYAN = "\u001B[46m";     
	public static final String B_BWHITE = "\u001B[47m";
	
//	public static void main(String[] args) {
//		System.out.println(BLACK+"BLACK");//장비없음
//		System.out.println(BBLACK+"BBLACK");
//		System.out.println(RED+"RED");//화염
//		System.out.println(BRED+"BRED");
//		System.out.println(GREEN+"GREEN");
//		System.out.println(BGREEN+"BGREEN"); //회복
//		System.out.println(YELLOW+"YELLOW");//선택지 
//		System.out.println(BYELLOW+"BYELLOW");//방어력 > 마비(전기)
//		System.out.println(BLUE+"BLUE");
//		System.out.println(BBLUE+"BBLUE");//냉기
//		System.out.println(PURPLE+"PURPLE");
//		System.out.println(BPURPLE+"BPURPLE");//마비 > 독
//		System.out.println(CYAN+"CYAN");//스킬
//		System.out.println(BCYAN+"BCYAN");//중독 > 방어력
//		System.out.println(WHITE+"WHITE");
//		System.out.println(BWHITE+"BWHITE");//기본
//		System.out.println(RESET+"RESET");//기본
//	}
}
