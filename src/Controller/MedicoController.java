/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Medico;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class MedicoController {
    public static List<Medico> medicos = new ArrayList();
    
    public static Medico cadastraMedico(Medico m) {
        boolean res = salvaMedicos(m);
        
        if(res == true){
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        return(m);
    }

    public static boolean salvaMedicos(Medico m) {
        medicos.add(m);
        return true;
    }

    public static boolean removeMedicos(int id) {
        Iterator<Medico> it = medicos.iterator();
        while(it.hasNext()){
            Medico m = it.next();
            if(m.getId_pessoa() == id){
                it.remove();
                return true;
            }
        }
//        for (int i = 0; i < MedicoController.count; i++) {
//            if (MedicoController.medicos[i].getId_pessoa() == id) {
//                MedicoController.medicos[i] = null;
//                for (int j = i; j < MedicoController.count - 1; j++) {
//                    MedicoController.medicos[j] = MedicoController.medicos[j + 1];
//                }
//                MedicoController.count--;
//                return true;
//            }
//        }
        return false;
    }
    
    public static Medico buscarPorId(int id) {
        for(Medico m : medicos){
            if(m.getId_pessoa() == id){
                return m;
            }
        }
//        for (int i = 0; i < MedicoController.count; i++) {
//            if (MedicoController.medicos[i].getId() == id) {
//                return MedicoController.medicos[i];
//            }
//        }
        return null;
    }
/*=================== À Fazer ======================*/
//    public static boolean editarMedicos(Medic pessoa) {;
//        for (int i = 0; i < PessoaController.count; i++) {
//            if (PessoaController.pessoas[i].getId() == pessoa.getId()) {
//                PessoaController.pessoas[i] = pessoa;
//                return true;
//            }
//        }
//        return false;
//    }

    public static List<Medico> listarMedicos() {
        return medicos;
//        return Arrays.copyOf(MedicoController.medicos, MedicoController.count);
    }
    
//    public static int proximaPosicaoLivre() {
//        for (int i = 0; i < MedicoController.medicos.length; i++) {
//            if (medicos[i] == null) {
//                return i;
//            }
//        }
//        return -1;
//    }
        
    public static boolean medicoExiste(int id) {
       for(Medico m: medicos){
           if(m != null){
                if(m.getId_pessoa() == id){
                    return true;
                }
           }
       }
       return false;
    }

}
