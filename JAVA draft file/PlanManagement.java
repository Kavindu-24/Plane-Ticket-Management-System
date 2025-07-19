import java.util.Scanner;   //import the Scanner class to read inputs


public class PlanManagement {
    private static int[][] array = new int[4][];    //Array to store seat availability
    private static Ticket[] tickets = new Ticket[52];   //Array to store sold tickets

 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //initialize arrays for seat availability
        array[0] = new int[14];
        array[1] = new int[12];
        array[2] = new int[12];
        array[3] = new int[14];


        boolean True = true;
        while (True) {
            try{
                int option;
                do{
                    displayMenu();  //To display the main menu
                    System.out.print("Please select an Option :");
                    option = scanner.nextInt();
                    True = false;

                    //Switch statement to handle user options
                    switch (option) {
                        case 1 -> buy_seat(scanner);          //To buy a seat
                        case 2 -> cancel_seat(scanner);       //To cancel a seat
                        case 3 -> find_first_available();     //Find the first available seat
                        case 4 -> show_seating_plan();        //Shows the seats that are available(O) and the seats that have been sold(X)
                        case 5 -> print_tickets_info();       //Prints the information of all tickets that have been sold
                        case 6 -> search_ticket(scanner);     //Search the ticket from person's information
                    }

                    System.out.println();

                }while(option !=0);  
                
                System.out.println("****  THANK YOU ! ****");

            } catch(Exception ex){
                System.out.println("Invalid choice. Please try again. ");
                scanner.next();  //Clear the buffer
                True = true;
            }
            
        }
    
    }    
        

    //Method to display the main menu
    private static void displayMenu(){
        System.out.println("");
        System.out.println("****   Welcome to the Plane Management application   ****");
        System.out.println("");
        System.out.println("");
        System.out.println("*********************************************************");
        System.out.println(""); 
        System.out.println("*                   MENU OPTIONS                        *"); 
        System.out.println(""); 
        System.out.println("*********************************************************");
        System.out.println(""); 
        System.out.println("1) buy a seat");
        System.out.println("2) Cancel a seat");
        System.out.println("3) Find first available seat");
        System.out.println("4) Show seating plan");
        System.out.println("5) Print tickets information and total sales");
        System.out.println("6) Search ticket");
        System.out.println("0) Quit");
        System.out.println(""); 
        System.out.println("*********************************************************"); 
        System.out.println(""); 
    }


    //Method to buy a seat
    public static void buy_seat(Scanner scanner){
        //asks the user to input a row letter
        System.out.print("Enter row letter (A,B,C or D): ");
        char rowletter = scanner.next().toUpperCase().charAt(0); 
        int rownum = (int) (rowletter - 'A');      

        //check the row letter is correct or not
        if (rownum < 0 || rownum >= array.length) {
            System.out.println("Invalid row letter!");
            buy_seat(scanner);  //Call to allow the user to input the row letter again
            return;
        }    


        int seatnum = 0;
        boolean correct = true;
        while (correct) {
            try{
                //asks the user to input a seat number
                System.out.print("Enter seat number : ");
                seatnum = scanner.nextInt();
                correct = false;

                //validation
                if (seatnum<0 || seatnum > array[rownum].length) {
                    System.out.println("Invaild seat number!");  
                    correct = true;     
                }
                
            } catch(Exception x){
                System.out.println("Invaild seat number!");
                scanner.next();   //Clear the buffer
            }
        }    


        if (array[rownum][seatnum-1] == 1) {
            System.out.println("\nSeat is already Sold.");    
        } else{
            //Prompt user to confirm seat purchase
            System.out.print("\nSeat is available! Do you want to buy it? (yes or no): ");
            String confirm = scanner.next().toLowerCase();
            if (confirm.equals("yes")) {
                array[rownum][seatnum-1] = 1;
                // asks the user to input personal details
                System.out.print("Enter your first name: ");
                String firstname = scanner.next();
                System.out.print("Enter your sure name: ");
                String surname = scanner.next();
                String email = null;
                boolean True = true;
                while (True) {
                    System.out.print("Enter your valid email address: ");
                    email = scanner.next();
                    //check the email address format
                    if (email.contains("@") && email.contains(".")) {
                        True = false;
                    } else{
                        System.out.println("Incorrect email address format!(e.g- example@example.com) ");
                    }  
                }
   
                
                //Create a Person object with user input
                Person person = new Person(firstname, surname, email);


                //check the ticket price based on seat number
                double price = 0;
                if (seatnum >= 1 && seatnum <=5 ) {
                        price = 200;
                }    
                else if (seatnum >= 6 && seatnum <=9 ) {
                        price = 150;    
                }
                else{
                        price = 180;    
                }

                //adding the ticket array to find available slot and creat ticket object
                for(int i = 0; i< tickets.length; i++){
                    if (tickets[i] == null) {
                        tickets[i] = new Ticket(rowletter, seatnum, price, person);
                        tickets[i].save();  //Save ticket information to file
                        break;
                    }
                }

                System.out.println("\nYour seat reservation Successfully Sold. Safe Flight! ");     
            } else{
                System.out.println("\nSeat not Sold. ");
            }
        }
        
    }

 
    //Method to cancel a seat
    private static void cancel_seat(Scanner scanner){
        //asks the user to input a row letter
        System.out.print("Enter row letter (A,B,C or D): ");
        char rowletter = scanner.next().toUpperCase().charAt(0); 
        int rownum = (int) (rowletter - 'A'); 

        //validation
        if (rownum < 0 || rownum >= array.length) {
            System.out.println("Invalid row letter!");
            cancel_seat(scanner);   //Call to allow the user to input the row letter again
            return;
        }


        int seatnum = 0;
        boolean correct = true;
        while (correct) {
            try{
                //asks the user to input a seat number
                System.out.print("Enter seat number : ");
                seatnum = scanner.nextInt();
                correct = false;

                //validation
                if (seatnum<0 || seatnum >= array[rownum].length) {
                    System.out.println("Invaild seat number!");  
                    correct = true;     
                }
                
            } catch(Exception x){
                System.out.println("Invaild seat number!");
                scanner.next();   //Clear the buffer
            }
        }    


        if (array[rownum][seatnum-1] == 0) {
            System.out.println("\nSeat is already Available.");    
        } else{
            array[rownum][seatnum-1] = 0;
            
            //tickets array to find and cancel the corresponding ticket
            for(int i = 0; i < tickets.length; i++){
                if (tickets[i] != null && tickets[i].getRow() == rowletter && tickets[i].getSeat() == seatnum) {
                    tickets[i] = null; //Remove the ticket
                    break;
                }
            }    
            System.out.println("\nSeat Successfully Canceled.");
        }
    }    


    //Find the first available seat in the array
    private static void find_first_available(){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if (array[i][j] == 0) {
                    char row = (char) ('A' + i);
                    System.out.println("\nFirst available seat is: " + row + (j + 1));
                    return;    //return the value when the first available seat is found
                }
            }

        }
    }


    //Method to shows the seats that are available(O) and the seats that have been sold(X)
    private static void show_seating_plan(){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    System.out.print("O ");   //represent an available seat
                } else {
                    System.out.print("X ");   //represent a sold seat
                }
            }
            System.out.println();  //Move to the next line after printing seats of the current row
        }
    }


    //Method to prints the information of all tickets that have been sold
    public static void print_tickets_info(){
        int total = 0;
        for(Ticket element: tickets){
            if (element != null) {
                element.printInfo();
                total += element.getPrice();
                System.out.println();
            }
        }
        System.out.println("Total Price:  £" + total);   //Print the total price of the tickets sold
    }


    //Method to search the ticket from person's information
    private static void search_ticket(Scanner scanner){
        //asks the user to input a row letter
        System.out.print("Enter row letter (A,B,C or D): ");
        char rowletter = scanner.next().toUpperCase().charAt(0); 
        int rownum = (int) (rowletter - 'A');

        //validation
        if (rownum < 0 || rownum >= array.length) {
            System.out.println("Invalid row letter!");
            search_ticket(scanner);  //Call to allow the user to input the row letter again
            return;
        }


        int seatnum = 0;
        boolean correct = true;
        while (correct) {
            try{
                //asks the user to input a seat number
                System.out.print("Enter seat number : ");
                seatnum = scanner.nextInt();
                correct = false;

                //validation
                if (seatnum<0 || seatnum >= array[rownum].length) {
                    System.out.println("Invaild seat number!");  
                    correct = true;     
                }
                
            } catch(Exception x){
                System.out.println("Invaild seat number!");
                scanner.next();  //Clear the buffer
            }
        }    


        if (array[rownum][seatnum-1] == 0) {
            System.out.println("\nThis seat is Available! ");
        } else{
            //Search for the ticket corresponding to the provided row letter and seat number
            for(int i = 0; i < tickets.length; i++){
                if (tickets[i].getRow() == rowletter && tickets[i].getSeat() == seatnum){
                    tickets[i].printInfo();     //Print ticket & person informations
                    break;
                }
            }    
        }
    }




}

