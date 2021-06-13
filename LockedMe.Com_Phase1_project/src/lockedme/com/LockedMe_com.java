package lockedme.com;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class LockedMe_com {
	static Scanner sc_str=new Scanner(System.in); //Using Scanner for taking user inputs
	
	//Creating method for taking user inputs
	static String take_user_input() { 
		System.out.println("Enter the options");
		String str= sc_str.nextLine();
		return str;
	}
	
	//Creating method for giving user menus
	static void main_menu() {  
		options_1();
		switch (take_user_input()) {
		case "1":
			list_files();  //Calling list_files method
			main_menu();  //Recursive  method
			break;
		case "2":	
			operation_menu(); //Calling operation_menu method
			break;
		case "3":
			System.exit(0);  //Exit from the application
			break;
		default:
			System.err.println("You are not selected valid option");
			main_menu(); // Recursive method
			break;
		}
	}
	
	//Creating method for Operations menu
	static void operation_menu() {
		LockedMe_com.options_2();
		switch(take_user_input()) {
		case "1":
			add_file(); //calling add_file() method 
			operation_menu(); //Recursion method
			break;
							
		case "2":
			delete_file(); // calling delete_file() method 
			operation_menu(); //Recursion method
			 break;
			
		case "3":
			search_file(); //calling search_file() method 
			operation_menu(); //Recursion method
			break;
			
		case "4":
			main_menu();  //Navigate to back
		 break;
		default:
			System.err.println("You are selected invalid option");
			operation_menu();  //Recursion method
			break;
		  
		}
	}
	
	//Method for printing Main options
	static void options_1() {
		
		System.out.println("\n***************************************");
		System.out.println("1. Current files in a directory");
		System.out.println("2. Select options to perform operations");
		System.out.println("3. Exit");
		System.out.println("***************************************");
		
	}
	
	//method for printing Sub Options
	static void options_2() {
		System.out.println("\n********************************");
		System.out.println("1. Add a file to the directory");
		System.out.println("2. Delete a file from directory");
		System.out.println("3. Search a file in directory");
		System.out.println("4. Back to the Main");
		System.out.println("*********************************");
		
	}
	
	//List all files in a Directory.
	public static void list_files() {
		File directory=new File("E:\\practice");
		if(directory.isDirectory()) {
		
			String[] files=directory.list();
			if (files.length>0) {
				Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
				
				System.out.println("***Files in Current directory***");
				for(String f:files) {
					System.out.println(f);
				}
			}else {
				System.out.println("Selected directory is empty");
			}
			
			}
		else {
			System.err.println("Directory Not Found");
		}
		main_menu();
		
	}
	
	
	//create a file in the directory
	public static void add_file() {
		try {
			System.out.println("Enter the filename");
			String file_name = String.format("E:\\practice\\%s",sc_str.nextLine());
			File file=new File(file_name);
			
			if(file.createNewFile()){
				System.out.println("File created Successfully");		
			}
			else if(file.exists()) {
				System.out.println("File already existed");
				
			}
				
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	//Delete file from Directory
	public static void delete_file() {
		try {
			System.out.println("***Existing files***\n");
			File directory = new File("E:\\practice");
			File[] files = directory.listFiles();
			int i = 1;
			if (files.length > 0) {
				for (File f:files) {
					System.out.println(i+" - "+ f.getName());
					i++;
				}
				System.out.println("***Select key to delete existing file***");
				
				int file_index = Integer.parseInt(take_user_input())-1;
				if ((file_index<=(files.length)-1) &&  (file_index>=0)) {
					boolean b=files[file_index].delete();
					if(b==true) {
						System.out.println("File "+files[file_index]+" deleted");
					}else {
						System.out.println("File not deleted");
					}
				}else {
					System.err.println("please select valid option");
					delete_file();
				}
				
			}else {
				System.out.println("Selected directory is empty");
			}
			
		}catch(Exception e) {
		e.printStackTrace();
			System.err.println("Please enter valid option");
		}
		
	}
	
	//Search a file in a directory
	public static void search_file() {
		//create a FileFilter and override its accept-method
		System.out.println("Enter file name to search");
		String search_name = sc_str.nextLine();
		FileFilter logFilefilter = new FileFilter() 
		{
		      //Override accept method
		      public boolean accept(File file) {
		              
	             //if the file substring exists return true else return false.
	             if (file.getName().contains(search_name)) {
	                return true;
	             }else {
	            	 return false;
	             }
		      }
		};
		File directory = new File("E:\\practice");
		File[] files = directory.listFiles(logFilefilter);
		 if (files.length>0) {
//			 Let's list out the filtered files
			 System.out.println("---Files found---");
			  for (File f: files)
			  { 
			     System.out.println(f.getName());
			  }
		 }else {
			 System.out.println("File not found");
		 }
	      
		
		
	}
	
	
	public static void main(String[] args) {

		System.out.println("\t\t\t\t LockedMe.Com ");
		System.out.println("Established by Naina Akshay Jiddewar");
		main_menu();
	}
}




