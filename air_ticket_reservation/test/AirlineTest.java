/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chaithika Stephen
 */
import Classes.Airline;
public class AirlineTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Airline a = new Airline();
        a.setAirline_ID("LK-01");
        a.setAirline_name("Sri Lankan");
        a.setOrigin("Sri Lanka");
        a.setActive(true);
        
        Airline a2 = new Airline("LK-01");
        if(a2.exist){
            System.out.println("Wada huththoooo");
        }else{
            System.out.println("ggg my life");
        }
        
        if(!a.save()){
            System.out.println("error");
        }
        
        a2 = new Airline("LK-01");
        if(a2.exist){
            System.out.println("Wada huththoooo");
        }else{
            System.out.println("ggg my life");
        }
       
    }
    
}
