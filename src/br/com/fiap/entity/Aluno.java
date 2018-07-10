package br.com.fiap.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME", length=100)
	private String nome;
	
	@Column(name = "DT_NASCIMENTO")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column(name = "RG", length=11)
	private String rg;
	
	@Column(name = "CPF", length=11)
	private String cpf;
	
	@Column(name = "ENDERECO", length=100)
	private String endereco;
	
	@Column(name = "SEXO", length=1)
	private String sexo;
	
	@Column(name = "TELEFONE", length=10)
	private String telefone;
	
	@Column(name = "CELULAR", length=11)
	private String celular;
	
	@Column(name = "EMAIL", length=50)
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="aluno")
    private Set<CursoAluno> cursosAluno = new HashSet<CursoAluno>();
	
	public Set<CursoAluno> getCursosAluno() {
		return cursosAluno;
	}

	public void setCursosAluno(Set<CursoAluno> cursosAluno) {
		this.cursosAluno = cursosAluno;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getCpf();
	}
	
	// propriedade de conveniencia
	public void setDataString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataNascimento(df.parse(data));
		} catch (Exception e) {
			this.setDataNascimento(new Date());
		}
	}
	
}