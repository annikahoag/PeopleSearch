import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner (System.in);
		boolean runAgain=true;
		int userInput;
		PeopleData people = new PeopleData();
		String nameLong, name;
		
		
		while(runAgain) {
			try {
				System.out.println();
				System.out.println("What would you like to do? Please enter: "
						+ "\n1 to input a person. "
						+ "\n2 to search for a person using sequential search. "
						+ "\n3 to search for a person using binary search. "
						+ "\n4 to print all people in alphabetical. "
						+ "\n5 to end the program.");
				userInput = scn.nextInt();
				
				switch(userInput) {
					//input a person
					case 1: userInput=1;
						people.addPerson();
						runAgain=true;
						break;
						
					//search by sequential	
					case 2: userInput=2;
						
						scn = new Scanner(System.in);
						System.out.println("Please enter the name of the person you want to search for.");
						nameLong = scn.nextLine();
						name = nameLong.trim();
						
						people.searchSequential(name);
						runAgain=true;
						break;
					
					//search by binary	
					case 3: userInput=3;

						scn = new Scanner(System.in);
						System.out.println("Please enter the name of the person you want to search for.");
						nameLong = scn.nextLine();
						name = nameLong.trim();
						people.searchBinary(name);
					
						runAgain=true;
						break;
					
					//print by alphabetical order	
					case 4: userInput=4;
						people.print();
						runAgain=true;
						break;
					
					//end program	
					case 5: userInput=5;
						System.out.println("Program is ending.");
						runAgain=false;
						break;
				
				}
			}catch(java.util.InputMismatchException e) {
				System.out.println("Please only enter integers. Program is ending.");
				runAgain = false;
			}
			
		}//end of while loop
		
		scn.close();

	}

}
