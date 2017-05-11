/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chaithika
 */
import Classes.Airport;
public class AirportTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Airport a = new Airport();
        a.setAirportID("A123");
        a.setName("CMB");
        a.setApstate("Colombo");
        a.setCity("Katunayake");
        a.setCountry("SL");
        a.setActive(true);
        
        Airport a1 = new Airport("A123");
        if(a1.exist)
            System.out.println("Available");
        else
            System.out.println("Aul kollo nane");
        
        a.save();
        
        Airport a2 = new Airport("A123");
        if(a1.exist)
            System.out.println("Available");
        else
            System.out.println("Aul kollo nane");
        
        a.deactivate();
        boolean x = a.isActive();
        System.out.println(x);
    }
    
}
