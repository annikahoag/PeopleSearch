import java.util.Scanner;

public class PeopleData {

	//instance variables
	Scanner scn = new Scanner(System.in);
	int size=0;
	Person [] perseArr = new Person[1];
	int foundCount, compCount, foundIndex;
	
	//constructor
	public PeopleData() {
		
	}
	
	//take in new Person information 
	public void addPerson() {
		String nameLong, name="";
		int age=0;
		scn = new Scanner(System.in);
		
		try {
			//ask user for name and age
			System.out.println("Please enter the name of the person.");
			nameLong = scn.nextLine();
			name = nameLong.trim();
			scn = new Scanner(System.in);
			
			System.out.println("Please enter the age of the person.");
			age = scn.nextInt();
		}catch(java.util.InputMismatchException e) {
			System.out.println("Invalid input. Age will be set to 0.");
			age=0;
		}
		//create new person
		Person perse = new Person(name, age);
		
		perseArr = this.addToArray(size, perseArr, perse);
		
		size++;
		
	}
	
	
	//adds one index to perseArr and adds new Person
	private Person[] addToArray(int origSize, Person perseArr[], Person x) {
		
		Person newArr[] = new Person[origSize+1]; 
		
		for (int i=0; i<origSize; i++) {
			newArr[i] = perseArr[i];
		}
		
		newArr[origSize] = x;
		
		return newArr;
	}
	
	
	//search for name by sequential search
	public void searchSequential(String n) {
		String x = n;
		foundCount=0;
		compCount=0;
		foundIndex=0;
		
		for (int i=0; i<size; i++) {
			if (perseArr[i].getName().equals(x)) {
				System.out.println("Name: " + perseArr[i].getName());
				System.out.println("Age: " + perseArr[i].getAge());
				foundIndex = i;
				foundCount++;
				compCount++;
				break;
			}
			compCount++;
		}
		
		if (foundCount==0) {
			System.out.println("That person is not in the array.");
			System.out.println(compCount + " comparisons were made.");
		}else {
			System.out.println(compCount + " comparisons were made.");
			this.changeArr(foundIndex);
		}
		
		System.out.println();
		
	}
	
	
	//option to edit/delete the person or do nothing 
	private void changeArr(int foundIndex) {
		scn = new Scanner(System.in);
		int userInput, nameOrAge;
		
		try {
			System.out.println();
			System.out.println("Would you like to change the data of this person? Please enter: "
					+ "\n1 to edit information about the person, "
					+ "\n2 to delete the person, "
					+ "\n3 to do nothing");
			userInput = scn.nextInt();
			
			scn = new Scanner (System.in);
			if (userInput==1) {
				//edit info
				System.out.println("Would you like to change the name or age? Please enter: "
						+ "\n1 to change the name and 2 to change the age.");
				nameOrAge = scn.nextInt();
				this.editInfo(nameOrAge, foundIndex);
				
			}else if (userInput==2) {
				//delete person
				perseArr = this.deletePerson(perseArr, foundIndex);
				size--;
				System.out.println("Person has been deleted.");
				
			}else if (userInput==3) {
				//do nothing
				
			}else {
				System.out.println("Please only enter the listed numbers, program will do nothing.");
			}
		}catch(java.util.InputMismatchException e) {
			System.out.println("Please only enter integers. Program will do nothing.");
		}
	}

	//edit info. about the person
	private void editInfo(int nameOrAge, int index) {
		String inputNameLong, inputName;
		int inputAge;
		scn = new Scanner(System.in);
		
		try {
			if (nameOrAge==1) {
				System.out.println("Please enter the name you want to change it to.");
				inputNameLong = scn.nextLine();
				inputName = inputNameLong.trim();
				
				perseArr[index].setName(inputName);
			}else if (nameOrAge==2) {
				System.out.println("Please enter the age you want to change it to.");
				inputAge = scn.nextInt();
				
				perseArr[index].setAge(inputAge);
			}else {
				System.out.println("Please only enter the listed numbers, program will continue.");
			}
		}catch(java.util.InputMismatchException e) {
			System.out.println("Invalid input. No information will be changed.");
		}
	}
	
	
	//deletes person
	private Person[] deletePerson(Person[] arr, int index) {
		
		
		if (arr==null || index<0 || index >=arr.length) {
			return arr;
		}
		
		Person [] anotherArray = new Person[arr.length-1];
		
		int k=0;
		for (int i=0; i<arr.length; i++) {
			if (i==index) {
				continue;
			}
			
			anotherArray[k] = arr[i];
			k++;
		}
		return anotherArray;
		
	}
	
	
	//sort perseArr using selection sort and by making a copy of perseArr
	public Person [] sort() {
		int minIndex;
		Person [] copyArr = new Person[size];
		
		for (int i=0; i<size; i++) {
			copyArr[i] = perseArr[i];
		}
		
		for (int i=0; i<size; i++) {
			minIndex = i;
			
			for (int j=i+1; j<size; j++) {
				if (copyArr[minIndex].getName().compareTo(copyArr[j].getName()) > 0) {
					minIndex = j;
				}
			}
			
			Person minTemp = copyArr[minIndex];
			copyArr[minIndex] = copyArr[i];
			copyArr[i] = minTemp;
			
		}//end of outer for
		
		return copyArr;
	}
	
	
	public void searchBinary(String n) {
		foundCount=0;
		compCount=0;
		foundIndex=0;
		int start=0, end=size-1; 
		int midIndex = (size-1) / 2;
		Person copyArr[] = this.sort();
		
		while(start<end) {
			compCount++;
			
			if (n.equals(copyArr[midIndex].getName())) {
				System.out.println("Name: " + copyArr[midIndex].getName());
				System.out.println("Age: " + copyArr[midIndex].getAge());
				
				start=end;
				foundCount++;
				foundIndex = midIndex;
			}else if (n.compareTo(copyArr[midIndex].getName()) > 0) {
				start=midIndex+1;
				midIndex = (((midIndex+1) + end) / 2); 
				continue;
			}else if (n.compareTo(copyArr[midIndex].getName()) < 0) {
				end=midIndex;
				midIndex = (midIndex + start) / 2;
				continue;
			}
			
		}//end of while 
		
		
		if (foundCount==0) {
			
			if (copyArr[end].getName().equals(n)) {
				System.out.println("Name: " + copyArr[end].getName());
				System.out.println("Age: " + copyArr[end].getAge());
				System.out.println(compCount + " comparisons.");
				foundIndex = midIndex;
				foundIndex=this.perseArrIndex(copyArr[foundIndex].getName(), copyArr[foundIndex].getAge());
				this.changeArr(foundIndex);
			}else {
			
				System.out.println("That person is not in the array.");
				System.out.println(compCount + " comparisons were made.");
			}
		}else {
			foundIndex=this.perseArrIndex(copyArr[foundIndex].getName(), copyArr[foundIndex].getAge());
			
			System.out.println(compCount + " comparisons were made.");
			this.changeArr(foundIndex);
		}
		
	}
	
	
	//call sort method and print array
	public void print() {
		Person [] printArr = this.sort();
		
		for (int i=0; i<size; i++) {
			System.out.println("Name: " + printArr[i].getName());
			System.out.println("Age: " + printArr[i].getAge());
		}
	}
	
	
	//find found index in original array in order to edit information
	private int perseArrIndex(String nameSearch, int ageSearch) {
		
		for (int i=0; i<size; i++) {
			
			if (perseArr[i].getName().equals(nameSearch) && perseArr[i].getAge()==ageSearch) {
				return i;
			}else {
				continue;
			}
		}
		
		return 1;
	}
	
	
}//end of class
