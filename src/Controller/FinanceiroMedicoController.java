/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CalendarioAno;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet; 
import Model.DBConnect;

import Model.Consulta;
import Model.FinanceiroMedico;
import Model.Franquia;
import Model.Medico;
import Model.Procedimento;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author yn719471
 */
//public class FinanceiroMedicoController implements Runnable {
public class FinanceiroMedicoController {
    private static List<FinanceiroMedico> financasMedico = new ArrayList();
    private static LocalDate data;

    
    private Connection connection;
    public FinanceiroMedicoController(LocalDate data) {
        this.connection = new DBConnect().getConnection();
        setData(data);
    }

    public static LocalDate getData() {
        return data;
    }

    public static void setData(LocalDate dataMes) {
        data = dataMes;
    }
    
    public static List<FinanceiroMedico> DadosRelatorioMensal(int id) throws SQLException {
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from financeiro_medico where id_franquia = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FinanceiroMedico fm = new FinanceiroMedico();
                fm.setId(rs.getInt("id"));
                fm.setIdMedico(rs.getInt("id_medico"));
                fm.setIdFranquia(rs.getInt("id_franquia"));
                fm.setIdUnidade(rs.getInt("id_unidade"));
                fm.setValor(rs.getDouble("valor"));
                fm.setEstado(rs.getInt("estado"));
                               
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                fm.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    fm.setDataModificacao(dataMod.toLocalDateTime());
                salvaFinanceiroMedico(fm);
            }
        }
        return financasMedico;
    }
    
    public static void salvaFinanceiroMedico(FinanceiroMedico fm) {
        financasMedico.add(fm);
    }  

    public static List<FinanceiroMedico> listarFinanceiroMedico() {
        setFinanceiroMedico();
        return financasMedico;
    }

//    public static boolean salvaFinanceiroMedico(FinanceiroMedico fm) {;
//    }

    public static boolean deleteFinanceiroMedico(int id) {
        String sql = "delete from financeiro_medico where id = ?";

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();

            System.out.println("Excluído com sucesso");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static FinanceiroMedico buscarPorId(int id) {
        setFinanceiroMedico();
        for(FinanceiroMedico fa: financasMedico){
            if(fa.getId() == id){
                return fa;
            }
        }
        
        return null;
    }

    public static boolean financeiroMedicoExiste(int id) {
        setFinanceiroMedico();
        for (FinanceiroMedico ic : financasMedico) {
            if (ic.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public static void varreduraFinMed(){
        ConsultaController.setConsultas();
        ProcedimentoController.setProcedimentos();
        
        for(Consulta c: ConsultaController.consultas){
            if(c != null){
                if(c.getEstado() == 4){
                    Medico m = MedicoController.buscarPorId(c.getIdMedico());
                    int idFranquia = m.getFranquia();
                    String sql = "insert into financeiro_medico" + "(id_medico, id_franquia, id_unidade, valor, estado, data_criacao)" +
                    "values (?, ?, ?, ?, ?, current_timestamp())";
                    try (Connection connection = new DBConnect().getConnection(); 
                            PreparedStatement stmt = connection.prepareStatement(sql)){
                       
                        stmt.setInt(1, c.getIdMedico());
                        stmt.setInt(2, idFranquia);
                        stmt.setInt(3, c.getUnidade());
                        stmt.setDouble(4, c.getValor()*0.7);
                        stmt.setInt(5, 1);

                        stmt.execute();
                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        
        for(Procedimento p: ProcedimentoController.procedimentos){
            if(p != null){
                if(p.getEstado() == 4){
                    Medico m = MedicoController.buscarPorId((int) p.getIdMedico());
                    int idFranquia = m.getFranquia();
                    String sql = "insert into financeiro_medico" + "(id_medico, id_franquia, id_unidade, valor, estado, data_criacao)" +
                    "values (?, ?, ?, ?, ?, current_timestamp())";
                    try (Connection connection = new DBConnect().getConnection(); 
                            PreparedStatement stmt = connection.prepareStatement(sql)){
                        stmt.setInt(1, (int) p.getIdMedico());
                        stmt.setInt(2, idFranquia);
                        stmt.setInt(3, (int) p.getIdUnidade());
                        stmt.setDouble(4, p.getValor()*0.5);
                        stmt.setInt(5, 1);

                        stmt.execute();
                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    
    public static void pagandoAdministradora() {
        double valorParaAdministradora = 0;
        for (Franquia f : FranquiaController.listarFranquias()) {
            try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select valor from financeiro_medico where id_franquia = ?")){
                stmt.setInt(1, (int) f.getId());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    double valor = rs.getDouble("valor");
                    valorParaAdministradora = valorParaAdministradora + (valor * .05);
                    try(Connection con2 = new DBConnect().getConnection(); 
                            PreparedStatement stmt2 = con2.prepareStatement("update financeiro_medico set estado = 2 where id_franquia = ?")){
                        stmt2.setInt(1, (int) f.getId());
                        stmt2.execute();
                    }catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                }
                String sql = "insert into registro_pagamento_administradora" + "(id_franquia, valor, data_pagamento, data_criacao)" +
                    "values (?, ?, ?, current_timestamp())";
                try(Connection con2 = new DBConnect().getConnection(); 
                    PreparedStatement stmt2 = con2.prepareStatement(sql)){
                    stmt2.setInt(1, (int) f.getId());
                    stmt2.setDouble(2, (valorParaAdministradora + 1000.00));
                    java.sql.Date sqlDate = Date.valueOf(data);
                    stmt2.setDate(3, sqlDate);
                    stmt2.execute();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Data do pagamenta para Administradora: " + data + "\nValor:" + (valorParaAdministradora + 1000.00));
            valorParaAdministradora = 0;
        }
    }
    
    public static void setFinanceiroMedico(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from financeiro_medico")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                FinanceiroMedico fm = new FinanceiroMedico();
                fm.setId(rs.getInt("id"));
                fm.setIdMedico(rs.getInt("id_medico"));
                fm.setIdFranquia(rs.getInt("id_franquia"));
                fm.setIdUnidade(rs.getInt("id_unidade"));
                fm.setValor(rs.getDouble("valor"));
                fm.setEstado(rs.getInt("estado"));
                               
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                fm.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    fm.setDataModificacao(dataMod.toLocalDateTime());
                salvaFinanceiroMedico(fm);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<FinanceiroMedico> it = financasMedico.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }

}