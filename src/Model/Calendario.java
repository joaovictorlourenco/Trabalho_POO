/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author yn719471
 */
public class Calendario {
    private LocalDate data = LocalDate.now();
    

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

        String novaDataFormatada = novaData.format(formatoData);
        System.out.println("Nova data: " + novaDataFormatada);
        
        return getData();
    }
}
