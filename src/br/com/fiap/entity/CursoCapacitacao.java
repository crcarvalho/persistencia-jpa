package br.com.fiap.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="curso_capacitacao")
public class CursoCapacitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOME", length=100)
	private String nome;

	@Column(name = "DURACAO", length=2)
	private int duracao;
	
	@Column(name = "AREA", length=50)
	private String area;
	
	@Column(name = "DESCRICAO", length=255)
	private String descricao;
	
	@Column(name = "CUSTO")
	private float custo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESCOLA")
	private Escola escola;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="curso")
    private Set<CursoAluno> cursosAluno = new HashSet<>();
	
	public Set<CursoAluno> getCursosAluno() {
		return cursosAluno;
	}

	public void setCursosAluno(Set<CursoAluno> cursosAluno) {
		this.cursosAluno = cursosAluno;
	}

	public int getId() {
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

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getEscola().getNomeFantasia() + " - " + this.getNome();
	}
}
