import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.HeureSupManager;
import domaine.HeureSup;

public class HeureSuppApi_Test {
    java.util.HashMap<java.lang.Integer,java.lang.Double> _startegieHeure;
    java.util.HashMap<java.lang.Integer,java.lang.Double> _startegieWeekend;

    HeureSupManager _HeureSupManager;
    List<HeureSup> _ListHeureSup;
    @BeforeEach
    public void setUp()
    {
        System.out.println("Before : instantiation");
        _startegieHeure = new HashMap<>();
        _startegieWeekend = new HashMap<>();
       _ListHeureSup = new ArrayList<HeureSup>();
    }
    @Test
    void HeureSupManager_HeureSupManager_parametres_return__startegieHeure_and__startegieWeekend_not_null(){
        // Arange 
        _startegieHeure.put(4, 3.5); // 3,5h sup le jeudi
        _startegieWeekend.put(7, 22.0); // 22,7h sup le dimanche

        //Action
        _HeureSupManager = new HeureSupManager(_startegieHeure, _startegieWeekend);

        // Assert
        assertEquals(_startegieHeure, _HeureSupManager.get_startegieHeure());
        assertEquals(_startegieWeekend, _HeureSupManager.get_startegieWeekend());
    }
         @Test
    void HeureSupManager_calculerHeurSupD1Utilisateur_(){

        //Arrange

        HeureSup heureSup_8h_jeudi = new HeureSup(0, 4, LocalDate.of(2022, 11, 6), 8.0);
         _startegieHeure.put(6, heureSup_8h_jeudi.get_duree()); 

         HeureSup heureSup_20h_dimanche = new HeureSup(1, 4, LocalDate.of(2022, 11, 7), 20.0);
         _startegieWeekend.put(heureSup_20h_dimanche.get_idEmploye(), heureSup_20h_dimanche.get_duree());
        
        
    
        //Action

        _ListHeureSup.add(heureSup_8h_jeudi);
       _ListHeureSup.add(heureSup_20h_dimanche);
       _HeureSupManager = new HeureSupManager(_startegieHeure, _startegieWeekend);
 
        double result = _HeureSupManager.calculerHeurSupD1Utilisateur(_ListHeureSup);

        


        //Assert
        assertEquals(28.0,result); 

    } 
}
