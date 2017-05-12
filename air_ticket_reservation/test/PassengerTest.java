/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chaithika
 */
import Classes.Passenger;
import java.util.Date;
public class PassengerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Passenger p1 = new Passenger();
        p1.setActive(true);
        Date d = new Date();
        p1.setDob(d);
        p1.setFname("Vinodh");
        p1.setLname("Padmasiri");
        p1.setPaddress("Colombo");
        p1.setPass_no("N123456789");
        p1.setPassword("test");
        p1.setTel("0112294006");
        
        Passenger p2 = new Passenger("N123456789");
        if(p2.exist){
            System.out.println("Wada bois");
        }
        else{
            System.out.println("Poddak aul wage");
        }
        
        p1.save();
        
        Passenger p3 = new Passenger("N123456789");
        if(p3.exist){
            System.out.println("Wada bois");
        }
        else{
            System.out.println("Poddak aul wage");
        }
        
        p1.deactivate();
        boolean x = p1.isActive();
        System.out.println(x);
    }
    
}
