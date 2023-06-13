/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Model.DBConnect;
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
    
    private Connection connection;
    public MedicoController() {
        this.connection = new DBConnect().getConnection();
    }
    
//    public static Medico cadastraMedico(Medico m) {
//        boolean res = salvaMedicos(m);
//        
//        if(res == true){
//            System.out.println("Cadastrado com sucesso");
//        } else {
//            System.out.println("Ocorreu um erro");
//        }
//        return(m);
//    }

    public static boolean salvaMedicos(Medico m) {
        medicos.add(m);
        return true;
    }

    public static boolean removeMedicos(int id) {
        String sql = "delete from pessoa where id = ? and medico = ?";

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.setLong(2, 1);
            
            stmt.execute();
            
            System.out.println("Excluído com sucesso");
            return true; 
       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
//        Iterator<Medico> it = medicos.iterator();
//        while(it.hasNext()){
//            Medico m = it.next();
//            if(m.getId_pessoa() == id){
//                it.remove();
//                return true;
//            }
//        }
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
    
    public static void listCleaner(){
        Iterator<Medico> it = medicos.iterator();
        while(it.hasNext()){
            it.remove();
        }
    }
}
