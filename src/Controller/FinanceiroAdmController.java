/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FinanceiroAdm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jv232
 */
public class FinanceiroAdmController {
    private static List<FinanceiroAdm> FinanceiroAdm = new ArrayList();
    
        
    public static void cadastraFinanceiroADM() {

        FinanceiroAdm mov = new FinanceiroAdm();

        boolean res = salvaFinanceiroADM(mov);
        if (res == true) {
            System.out.println("Cadastrada com Sucesso");
        } else {
            System.out.println("Ocorreu um ERRO");
        }

    }
    
    public static boolean salvaFinanceiroADM(FinanceiroAdm mov){
        FinanceiroAdm.add(mov);
        return true;
    }
    
    public static boolean removeFinanceiroAdm(int id) {
        
        Iterator<FinanceiroAdm> iterator = FinanceiroAdm.iterator();
        
         while(iterator.hasNext()){
            FinanceiroAdm fa = iterator.next();
            if(fa.getId() == id){
                iterator.remove();
                return true;
            }
            
        }
        return false;
    }

 
    public static List<FinanceiroAdm> listarFinanceiroADM() {
        return FinanceiroAdm;
    }
    
    public static FinanceiroAdm buscarPorId(int id) {
        
        for(FinanceiroAdm fa: FinanceiroAdm){
            
            if(fa.getId() == id){
                return fa;
            }
        }       
 
        return null;
    }
    
}
