
import java.io.Serializable;
import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Contact implements Serializable {
    String phone;
    String fname;
    String lname;
    
    public static int numberOfContacts;
    public static ArrayList<Contact> book = new ArrayList<Contact>();
    
    public Contact() {
        phone = "";
        fname = "";
        lname = "";
    }

    //Getters 
    String getFname(){
        return this.fname;
    }
    String getPhone(){
        return this.phone;
    }
    String getLname(){
        return this.lname;
    }
   
    //Setters
    void setFname(String i){
        this.fname = i;
    }
    void setPhone(String num){
        this.phone = num;
    }
    void setLname(String n){
        this.lname = n;
    }

}

