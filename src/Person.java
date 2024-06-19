import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class Person implements Serializable{
	private String dept;
//	private int number;
	private String name;
	private int id;
	private String email;
	

//	public int getNumber() {
//		return number;
//	}
//
//	public void setNumber(int number) {
//		this.number = number;
//	}
	
//	public int compareTo(Object object) {
//		Person otherPerson = (Person) object;
//		int comparename = name.compareTo(otherPerson.getName());
//		if (comparename == 0) {
//			return dept.compareTo(otherPerson.getDept());
//		}
//		return comparename;
//		
//	}
	
	
//	public boolean getSortByName() {
//		return sortByName;
//	}
//
//	public static void setSortByName(boolean setsortByName) {
//		sortByName = setsortByName;
//	}

	public Person(String initdept, String initname, int initid, String initemail) {
		if (initdept.isEmpty()) {
			dept = "Not Yet Defined";
		} else {
			dept = initdept;
		}
		name = initname;
		id = initid;
		if (initemail.isEmpty()) {
			email = "Not Yet Defined";
		} else {
			email = initemail;
		}
		
	}
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
//	public void saveToTextFile() {
////		System.out.println("inside saveToTextFile() method");
//		PrintWriter outputStream = null;
//		String fileName = "contacts.txt";
//		try {
//			outputStream = new PrintWriter(new FileOutputStream(fileName, true));
//		} catch (FileNotFoundException e) {
//			System.out.println("Error opening the file " + fileName);
//			System.exit(0);
//		}
////		outputStream.println("hello");
//		outputStream.println(this);
//		outputStream.close();
//	}
	
//	public void saveToBinaryFile() {
//		ObjectOutputStream outputStream = null;
//		String fileName = "contacts.dat";
//		try {
//			outputStream = new ObjectOutputStream(new FileOutputStream(fileName, true));
//			outputStream.writeObject(this);
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Error opening the file " + fileName);
//			System.exit(0);
//		} catch (IOException e) {
//			System.out.println("Problem with output to file " + fileName);
//		}
//		
//		try {
//			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
//			
//			Person person = (Person) inputStream.readObject();
//			System.out.println("Test");
//			System.out.println("Faculty " + person.getNumber() + ", " + person.getDept() 
//			+ ", Name: " + person.getName() + ", Faculty ID: " 
//			+ person.getId() + " (" + person.getEmail() + ")");
//			
//			if (person instanceof Under) {
//				System.out.println("returned object class type is \"Under\"");
//			}
//			if (person instanceof Grad) {
//				System.out.println("returned object class type is \"Grad\"");
//			}
////			
//		} catch (FileNotFoundException e) {
//			System.out.println(fileName + "file not found");
//			System.exit(0);
//		} catch (IOException e) {
//			System.out.println("Error opening input file  " + fileName);
//			System.exit(0);
//		} catch (ClassNotFoundException e) {
//			System.out.println("Class not found!");
//			System.exit(0);
//		}
//		
//	}
	
//	public void writeOutput() {
//		System.out.println(this);
//		
//	}

	



}
