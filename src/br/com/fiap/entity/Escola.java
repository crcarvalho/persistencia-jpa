package br.com.fiap.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="escola")
public class Escola {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int Id;

	@Column(name = "RAZAO_SOCIAL", length=100)
	private String razaoSocial;
	
	@Column(name = "NOME_FANTASIA", length=100)
	private String nomeFantasia;
	
	@Column(name = "DT_FUNDACAO")
	private Date dataFundacao;
	
	@Column(name = "ENDERECO", length=100)
	private String endereco;
	
	@Column(name = "CNPJ", length=14)
	private String cnpj;
    

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="escola")
	private List<CursoCapacitacao> cursos = new ArrayList<>();
	
	public List<CursoCapacitacao> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoCapacitacao> cursos) {
		this.cursos = cursos;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return this.getNomeFantasia() + " - "+ this.getCnpj();
	}
	
	// propriedade de conveniencia
	public void setDataString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataFundacao(df.parse(data));
		} catch (Exception e) {
			this.setDataFundacao(new Date());
		}
	}

}
