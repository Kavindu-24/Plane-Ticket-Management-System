import java.io.File;    //Import the File class
import java.io.FileWriter;    //Import the filewriter class
import java.io.IOException;    //Import the IOException class to handle errors

public class Ticket {
    private char row;
    private int seat;
    public double price;
    private Person person;

    //Contructor to initialize Ticket object
    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Getter methods
    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    //Method to print ticket informations
    public void printInfo() {
        System.out.println();
        System.out.println("Row: " + row + "\t Seat: " + seat);
        System.out.println("Price:  £" + price );
        System.out.println("Personal Details -");
        person.printperson();     //Method in the Person class to print person details
    }


    //Method to save ticket information to files
    public void save(){
        String FileName = String.valueOf(row) + String.valueOf(seat) + ".txt";   //Constructing File name
        File myObj = new File(FileName);        //Creating a File object 

        try {
            FileWriter myWriter = new FileWriter(FileName);     //Creating FileWriter object to write to the File
            myWriter.write("Row: "+ row + "\t Seat: " + seat + "\n");
            myWriter.write("Price:  £" + price + "\n");
            myWriter.write("Personal Details - "+ "\n");
            myWriter.write("Full Name: "+ person.getfirstname() + "" + person.getsurname() + "\n");
            myWriter.write("Email: " + person.getemail() + "\n");
            myWriter.close();
            System.out.println("Ticket information saved to " + FileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving ticket Information! ");
            e.printStackTrace();       //Return some details about error
        }
    }



}

