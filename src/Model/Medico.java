/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author yn719471
 */
public class Medico extends Pessoa {
    private long id_pessoa;
    private String crm;
    private Pessoa pessoa;
    private String especialidade;
    private Date dataCriacao;
    private Date dataModificacao;
//    private int franquia;
//    private int unidade;
    
    Scanner scan = new Scanner(System.in);
    
    public Medico(Pessoa p) {
        this.id_pessoa = p.id;
        this.pessoa = p;
        
        System.out.println("digite o CRM");
        String CRM = scan.nextLine();
        this.setCrm(CRM);

        System.out.println("digite a especialidade");
        String espec = scan.nextLine();
        this.setEspecialidade(espec);

//        System.out.println("digite a franquia");
//        int franq = Integer.parseInt(scan.nextLine());
//        this.setFranquia(franquia);
//
//        System.out.println("digite a unidade");
//        int unit = Integer.parseInt(scan.nextLine());
//        this.setUnidade(unit);
        
        this.setDataCriacao(new Date());
        this.pessoa.setTipoUsuario(2);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

//    public int getFranquia() {
//        return franquia;
//    }
//
//    public void setFranquia(int franquia) {
//        this.franquia = franquia;
//    }
//
//    public int getUnidade() {
//        return unidade;
//    }
//
//    public void setUnidade(int unidade) {
//        this.unidade = unidade;
//    }

    @Override
    public String toString() {
        return "Medico{" + "crm=" + crm + ", pessoa=" + pessoa + ", especialidade=" + especialidade + '}';
    }

}
