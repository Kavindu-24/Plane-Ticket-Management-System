
public class Person {

    private String firstname;
    private String surname;
    private String email;
    
    //Contructor to initialize Person object
    public Person(String firstname, String surname, String email){
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }

    //getter methods
    public String getfirstname(){
        return firstname;
    }

    public String getsurname(){
        return surname;
    }

    public String getemail(){
        return email;
    }


    //method to print person informations
    public void printperson(){
        System.out.println("Full Name: "+ firstname +" "+ surname);
        System.out.println("Email: "+ email);
    }

}
