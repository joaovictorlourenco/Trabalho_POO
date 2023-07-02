/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.ConsultaController;
import Controller.ProcedimentoController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author yn719471
 */
public class CalendarioAno {
    private LocalDate data = LocalDate.now();
    
    private Connection connection;
    public CalendarioAno() {
        this.connection = new DBConnect().getConnection();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }        
        
                
    public LocalDate acrescentaCalendario() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getData().format(formatoData);
        System.out.println("Data atual: " + dataFormatada);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número de dias a serem adicionados: ");
        int numeroDias = scanner.nextInt();

        LocalDate novaData = getData().plusDays(numeroDias);
        setData(novaData);
        
        for (Consulta c : ConsultaController.listarConsultas()) {
            if(c.getDataConsulta().isBefore(novaData)){
                try(Connection con = new DBConnect().getConnection(); 
                    PreparedStatement stmt = con.prepareStatement("update consulta set estado = 4 where id = ?")){
                    stmt.setInt(1, (int) c.getId());
                    stmt.execute();
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (Procedimento p : ProcedimentoController.listarProcedimentos()) {
            if(p.getdataProcedimento().isBefore(novaData)){
                try(Connection con = new DBConnect().getConnection(); 
                    PreparedStatement stmt = con.prepareStatement("update procedimento set estado = 4 where id = ?")){
                    stmt.setInt(1, (int) p.getIdProcedimento());
                    stmt.execute();
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String novaDataFormatada = novaData.format(formatoData);
        System.out.println("Nova data: " + novaDataFormatada);
        
        return getData();
    }
}
