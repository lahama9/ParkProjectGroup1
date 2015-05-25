/**
 * AdminIO.java
 * 
 * This class is searching for Volunteer by lastName
 */
package user_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import park_model.AdminAbilities;
import park_model.User;
import config_files.Config;


/**
 * Runs the menu for administrators.
 * 
 * @author Julia and Lee
 * @version 5/10/2015
 */
public class AdminIO implements IO{
	private User myUser;
	private AdminAbilities myAdminAbilities;
	
	public AdminIO(User myUser) {
		this.myUser = myUser;
		myAdminAbilities = new AdminAbilities(Config.VOLUNTEER_FILE);
	}
	/**
	 * This my control menu
	 */
	@Override
	public void mainMenu() {
		
		System.out.println(); // spacer
		
		int i = 0;
		boolean validChoice = false;
		
 		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
 		System.out.println("Please select an option, Administrator " + myUser.getLastName() + ".");
		
		while(!validChoice){
			System.out.println("1: Search For Volunteer. \n2: Quit");
			try{
				i = Integer.parseInt(console.readLine());
				
				switch(i){
				case 1:
					searchVol(console);
				case 2:	
					System.out.println("\nHave a good day.");
					System.exit(0);
				default:
					System.out.println("\nPlease enter a number from the menu.\n");
				}
			}catch(NumberFormatException | IOException nfe){
				System.out.println("\nPlease enter a number from the menu.\n");
			}
		}
		
	}
	
	//This method shows off the name of volunteer have been searched
	private void searchVol(BufferedReader console){
		System.out.println(); // spacer
		String name = null;
		while (name == null) {
			System.out.println("Please enter a last name to search with: ");
			try {
				name = console.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collection<User> volunteers = myAdminAbilities.getVolunteersByLastName(name);
		System.out.println(); // spacer
		if (volunteers.isEmpty()) {
			System.out.println("There are no volunteers with that last name.\n");
		}
		else {
			for (User vol: volunteers) {
				System.out.println(vol);
			}
		}
		mainMenu();
	}
	
}