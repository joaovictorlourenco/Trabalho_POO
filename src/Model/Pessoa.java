/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author yn719471
 */
public class Pessoa {
    protected long id;
    private static long serial;
    private String nome;
    private String endereco;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;
    private final int [] tipoUsuario = new int[4];
    private Date dataCriacao;
    private Date dataModificacao;
    
    
    Scanner scan = new Scanner(System.in);
    public Pessoa(String nome, String end, String cpf, String tel, String login, String senha, int tipoUsuario) {
        serial++;
        this.id = serial;
        this.setNome(nome);
        this.setEndereco(end);
        this.setCpf(cpf);
        this.setTelefone(tel);
        this.setSenha(senha);
        this.setLogin(login);
        this.setTipoUsuario(1);
        this.setTipoUsuario(tipoUsuario);
        this.setDataCriacao(new Date());
    }
    public Pessoa() {}
    public Pessoa(int i) {
        serial++;
        System.out.println("digite seu nome:");
        String nome = scan.nextLine();
        this.setNome(nome);
//            System.out.println(this.nome);

        System.out.println("digite seu endereço:");
        String end = scan.nextLine();
        this.setEndereco(end);
//            System.out.println(this.endereco);

        System.out.println("digite seu CPF:");
        String cpf = scan.nextLine();
        this.setCpf(cpf);
//            System.out.println(this.cpf);

        System.out.println("digite seu telefone:");
        String tel = scan.nextLine();
        this.setTelefone(tel);
//            System.out.println(this.telefone);

        System.out.println("digite seu login:");
        String login = scan.nextLine();
        this.setLogin(login);
//            System.out.println(this.login);

        System.out.println("digite seu sua senha:");
        String senha = scan.nextLine();
        this.setSenha(senha);
//            System.out.println(this.senha);

        this.setDataCriacao(new Date());
        setTipoUsuario(1);
        this.id = serial;
  
/*
 ------------ Permissões
              1 = paciente
              2 = medico  
              3 = dono da UNIDADE
              4 = dono da FRANQUIA
*/ 

    }

    public long getId() {
            return id;
    }

//    public void setId(int id) {
//            this.id = id;
//    }

    public String getNome() {
            return nome;
    }

    private void setNome(String nome) {
            this.nome = nome;
    }

    public String getEndereco() {
            return endereco;
    }

    private void setEndereco(String endereco) {
            this.endereco = endereco;
    }

    public String getCpf() {
            return cpf;
    }

    private void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public String getTelefone() {
            return telefone;
    }

    private void setTelefone(String telefone) {
            this.telefone = telefone;
    }

    public String getLogin() {
            return login;
    }

    private void setLogin(String login) {
            this.login = login;
    }

    public String getSenha() {
            return senha;
    }

    private void setSenha(String senha) {
            this.senha = senha;
    }

    public int[] getTipoUsuario() {
            return Arrays.copyOf(this.tipoUsuario, this.tipoUsuario.length);
    }

    public void setTipoUsuario(int tipoUsuario) {
        switch(tipoUsuario){
            case 1:
                this.tipoUsuario[0] = 1;
                break;
            case 2:
                this.tipoUsuario[1] = 2;
                break;
            case 3:
                this.tipoUsuario[2] = 3;
                break;
            case 4:
                this.tipoUsuario[3] = 4;
                break;
        }
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

    public void novoLogin(Pessoa pessoa) {
        System.out.println("Insira um login que nao esteja em uso:");
        this.login = scan.nextLine();
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cpf=" + cpf + ", telefone=" + telefone + ", login=" + login + ", senha=" + senha + ", tipoUsuario=" + Arrays.toString(tipoUsuario) + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }    

    public void alteraTipoUsuario(int papel, int i) {
        if(i == 0){
            switch(papel){
                case 2:
                    this.tipoUsuario[1] = 0;
                    break;
                case 3:
                    this.tipoUsuario[2] = 0;
                    break;
                case 4:
                    this.tipoUsuario[3] = 0;
                    break;
            }
        }else if(i == 1){
            switch(papel){
                case 2:
                    this.tipoUsuario[1] = 2;
                    break;
                case 3:
                    this.tipoUsuario[2] = 3;
                    break;
                case 4:
                    this.tipoUsuario[3] = 4;
                    break;
            }
        }
    }
}
