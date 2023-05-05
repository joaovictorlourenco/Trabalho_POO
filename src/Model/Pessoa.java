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
public class Pessoa {
    private long id;
    private String nome;
    private String endereco;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;
    private int tipoUsuario;
    private Date dataCriacao;
    private Date dataModificacao;
    
    
    Scanner scan = new Scanner(System.in);
    
        public Pessoa() {
            System.out.println("digite seu nome:");
            String nome = scan.nextLine();
            this.setNome(nome);
//            System.out.println(this.nome);
            
            System.out.println("digite seu endereço:");
            String end = scan.nextLine();
            this.setEndereco(cpf);
//            System.out.println(this.endereco);
            
            System.out.println("digite seu CPF:");
            String cpf = scan.nextLine();
            this.setCpf(cpf);
//            System.out.println(this.cpf);
            
            System.out.println("digite seu telefone:");
            String tel = scan.nextLine();
            this.setTelefone(telefone);
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
            this.tipoUsuario = 1;
            
//            0 = ADM(dono da franquia?)
//            1 = paciente
//            2 = medico  
//            3 = dono da franquia
//            4 = dono da unidade

        }

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
        
        public String novoLogin(Pessoa pessoa){
            System.out.println("Insira um novo login que não esteja em uso:");
            this.login = scan.nextLine();
            return login;
        }
}
