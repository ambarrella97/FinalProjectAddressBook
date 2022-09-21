package addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;
		do {
			System.out.println("--------Welcome to Address Book---------");
			System.out.println("Enter '1' to Add contact");
			System.out.println("Enter '2' to Search contact");
			System.out.println("Enter '3' to Print contact");
			System.out.println("Enter '4' to Edit contact");
			System.out.println("Enter '5' to Delete contact");
			System.out.println("Enter '0' to Exit");
			System.out.println("Do you want to print contacts, add contact, search for contact, edit contact or delete contact?");
			System.out.println("Answer with '1', '2', '3', '4', '5' or '0' to exit application.");	
			try {
				answer = input.nextInt();
			} catch (NumberFormatException e) {
				answer = -1;
			}
			if(answer == 1)
				
				Add.add_contact();
			else if(answer == 2)
				Search.choose_field();
			else if(answer == 3)
				Print.show_contacts();
			else if(answer == 5)
				Delete.choose_field();
				
			
		}while(answer != exit);
		System.out.println("Application terminating...");
	}
}

class Add extends Main{
	public static void add_contact() throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir")+"/Address_Book/src/contacts.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1)); 
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/Address_Book/src/contacts.txt", true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);
		Scanner input= new Scanner(System.in);
		boolean duplicate, valid;
		String currentLine1;
		String f1 = "";
		String f2 = "";
		String f5 = "";
		String f6 = "";
		String f8 = "";
		int f3 = -1;
		int f4 = -1;
		int f7 = -1;
		int f9 = -1;
		String str;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();		
		do {
			duplicate = false;
			valid = true;
			System.out.println("Give Phone: ");
			try {
			    f3 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				valid = false;
				System.out.println("Phone must be number.");
			}
			while((currentLine1 = reader1.readLine()) != null) {
				String[] words1=currentLine1.split(",");
				if(words1[2].equals(String.valueOf(f3))) {
						duplicate=true;
						System.out.println("Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true || valid == false);
		do {
			duplicate = false;
			valid = true;
			System.out.println("Give Mobile phone: ");
			try {
			    f4 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Mobile phone must be number.");
				valid = false;
			}
			while((currentLine1 = reader1.readLine()) != null) {
				String[] words1=currentLine1.split(",");
				if(words1[3].equals(String.valueOf(f4))) {
						duplicate=true;
						System.out.println("Mobile Phone must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true || valid == false);
		do {
			duplicate = false;
			System.out.println("Give E-mail: ");
			f5 = input.nextLine();
			while((currentLine1 = reader1.readLine()) != null) {//check for duplicate
				String[] words1=currentLine1.split(",");
				if(words1[4].equals(f5)) {
						duplicate=true;
						System.out.println("E-mail must be unique among the contacts.");
				}
			}
			reader1 = new BufferedReader(new FileReader(file1));
		}while (duplicate == true);
		System.out.println("Give Street: ");
		f6 = input.nextLine();
		System.out.println("Give street number: ");
		do {
			valid = true;
			try {
			    f7 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Street number must be a number.");
				valid = false;
			}
		}while(valid == false);
		System.out.println("Give town: ");
		f8 = input.nextLine();
		System.out.println("Give Zip code: ");
		do {
			valid = true;
			try {
			    f9 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Zip code must be a number.");
				valid = false;
			}
		}while(valid == false);
		if(f1 == "" || f2 == "" || f5 == "" || f6 == "" || f8 == "" || f3 == -1 || f4 == -1 || f7 == -1 || f9 == -1) {//i check that all variables have a valid attribute assigned
			System.out.println("You gave false inputs, adding new contact wasn't successful: ");
		}
		else {
			str = f1 + "," + f2 + "," + String.valueOf(f3) + "," + String.valueOf(f4) + "," + f5 + "," + f6 + "," + String.valueOf(f7) + "," + f8 + "," + String.valueOf(f9);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));//with these code I add a line at the bottom of the file
			out.println(str);
			out.close();
		}
		writer.close(); 
		reader1.close(); 
	}

}

 class Search {
	public static void choose_field() throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;
		do {
			System.out.println("Do you want to search beased on name or based on phone?");
			System.out.println("Give '1' or '2' or anser '0' to return to main menu.");	
			try {
				answer = input.nextInt();
			} catch (Exception e) {
				answer = 0;
			}
			if(answer == 1)
				name_search();
			else if(answer == 2)
				number_search();
							
		}while(answer != exit);
	}
	
	public static void name_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		String f1,f2;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();
		File file = new File(System.getProperty("user.dir")+"/Address_Book/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {
				String[] info=currentLine.split(",");
				if(info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("----There is a contact for the information you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
				}
				else if(info[0].equals(f1) && !info[1].equals(f2)) {
					System.out.println("----There is a contact for the Name you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
				}
				else if(!info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("----There is a contact for the Surname you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
				}
				
			}
		}
		System.out.println("-------------------");
		reader.close();
		choose_field();
	}
	
	public static void number_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		int f1 = -1;
		int f2 = -1;
		boolean valid;
		System.out.println("Give Phone number: ");
		do {
			valid = true;
			try {
			    f1 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Phone number must be number.");
				valid = false;
			}
		}while(valid == false);
		System.out.println("Give mobile number: ");
		do {
			valid = true;
			try {
			    f2 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Mobile number must be number.");
				valid = false;
			}
		}while(valid == false);
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		if(f1 == -1 && f2 == -1) {
			System.out.println("-------------------");
			System.out.println("You gave wrong information.");
		}
		else {
			while((currentLine = reader.readLine()) !=null) {
				if(!first) {
					fields = currentLine.split(",");
					first = true;
				}
				else {
					String[] info=currentLine.split(",");
					if(f1 == -1 && f2 != -1) {
						if(info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Mobile number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
						}
					}
					else if(f1 != -1 && f2 == -1) {
						if(info[2].equals(String.valueOf(f1))) {
							System.out.println("----There is a contact for the Phone number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
						}
					}
					else if (f1 != -1 && f2 != -1) {
						if(info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Phone and Mobile number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
						}
						else if(info[2].equals(String.valueOf(f1)) && !info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Phone number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
						}
						else if(!info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Phone number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
						}
					}					
				}			
			}
		}
		
		System.out.println("-------------------");
		reader.close();
		choose_field();
	}


}

 class Print extends Main{
	public static void show_contacts() throws IOException, FileNotFoundException{
		File file = new File(System.getProperty("user.dir")+"/Address_Book/src/contacts.txt");//we get the contact file 
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParentFile());
		System.out.println(file.exists());
		file.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {
				System.out.println("-------------------");
				String[] info=currentLine.split(",");
				for (int i = 0; i < fields.length; i++ ) {
					System.out.println(fields[i] +": "+ info[i]);					
				}
			}
		}
		System.out.println("-------------------");
		reader.close();
	}
}

 class Delete {
	public static void choose_field() throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;
		do {
			System.out.println("Do you want to delete a contact based on the name or the phone number?");
			System.out.println("Give '1', '2' or '0' to go back to main menu.");	
			try {
				answer = input.nextInt();
			} catch (Exception e) {
				answer = 0;
			}
			if(answer == 1) 
				name_search();				
			else if(answer == 2)
				number_search();
							
		}while(answer != exit);
	}
	
	public static void name_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		String f1,f2;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();
		File file = new File(System.getProperty("user.dir")+"/Address_Book/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		List<String> lines = new ArrayList<String>();
		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {
				String[] info=currentLine.split(",");
				if(info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("----There is a contact for the information you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
					lines.add(currentLine);
				}
				else if(info[0].equals(f1) && !info[1].equals(f2)) {
					System.out.println("----There is a contact for the Name you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
					System.out.println("----Name ans Surname must be valid----");
				}
				else if(!info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("----There is a contact for the Surname you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
					System.out.println("----Name and Surname must be valid----");
				}
				
			}
		}
		System.out.println("-------------------");
		reader.close();
		for(Object str:lines){
			contact_delete(str.toString());
		} 
		choose_field();
	}
	
	public static void number_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		int f1 = -1;
		int f2 = -1;
		boolean valid;
		System.out.println("Give Phone number: ");
		do {
			valid = true;
			try {
			    f1 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				valid = false;
			}
		}while(valid == false);
		System.out.println("Give Mobile number: ");
		do {
			valid = true;
			try {
			    f2 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				valid = false;
			}
		}while(valid == false);
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		if(f1 == -1 && f2 == -1) {
			System.out.println("-------------------");
			System.out.println("You gave wrong information.");
		}
		else {
			while((currentLine = reader.readLine()) !=null) {
				if(!first) {
					fields = currentLine.split(",");
					first = true;
				}
				else {
					String[] info=currentLine.split(",");
					if(f1 == -1 && f2 != -1) {
						if(info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Mobile number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							System.out.println("----Phone and Mobile numbers must be valid----");
						}
					}
					else if(f1 != -1 && f2 == -1) {
						if(info[2].equals(String.valueOf(f1))) {
							System.out.println("----There is a contact for the Phone number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							System.out.println("----Phone and Mobile numbers must be valid----");
						}
					}
					else if (f1 != -1 && f2 != -1) {
						if(info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the information you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							contact_delete(currentLine);
						}
						else if(info[2].equals(String.valueOf(f1)) && !info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Phone number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							System.out.println("----Phone and Mobile numbers must be valid----");
						}
						else if(!info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the Mobile number you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							System.out.println("----Phone and Mobile numbers must be valid----");
						}
					}					
				}			
			}
		}
		
		System.out.println("-------------------");
		reader.close();
		choose_field();
	}
	
	public static void contact_delete(String line)  throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));	
		String currentLine1;
		boolean first = false;
		String[] fields = new String[0];
		File file2 = new File(System.getProperty("user.dir")+"/src/contactstemp.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		while((currentLine1 = reader1.readLine()) !=null) {
			if(!first) {
				fields = currentLine1.split(",");
				writer.write(currentLine1 + "\n");
				first = true;
			}
			else if(!currentLine1.equals(line)){	
				writer.write(currentLine1 + "\n");
			}
		}
		reader1.close();
		writer.close();
		file1.delete();
		file2.renameTo(file1);
		System.out.println("Information was valid, deletion completed successfully");
	}	
	
	
}