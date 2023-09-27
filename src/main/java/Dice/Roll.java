package Dice;

import java.util.Random;

public class Roll {
	public static int roll6() {
		return (int)(Math.random()*6)+1;
	}
	
	public static int random(int num) {
		return (int)(Math.random()*num)+1;
	}
	
	public static int[] shuffle(int startNum, int endNum) {
		Random random = new Random();
		int[] arr = new int[endNum-startNum+1];
		int Num=startNum;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Num++;
		}
		for (int i = 0; i < arr.length; i++) {
			int randomNum = random.nextInt(endNum-startNum);
			int temp = arr[i];
			arr[i] = arr[randomNum];
			arr[randomNum] = temp;			
		}		
		return arr;
	}
}
