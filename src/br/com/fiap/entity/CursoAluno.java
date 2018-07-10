package br.com.fiap.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso_aluno")
public class CursoAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDALUNO")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCURSO")
	private CursoCapacitacao curso;

	@Column(name = "DT_INICIO")
	private Date dataInicio;

	@Column(name = "FORMADO")
	private int formado;

	@Column(name = "DT_CONCLUSAO")
	private Date dataConclusao;

	@Column(name = "NOTA_FINAL")
	private float notaFinal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public CursoCapacitacao getCurso() {
		return curso;
	}

	public void setCurso(CursoCapacitacao curso) {
		this.curso = curso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getFormado() {
		return formado;
	}

	public void setFormado(int formado) {
		this.formado = formado;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public float getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(float notaFinal) {
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getAluno().toString()+this.getCurso().toString();
	}
	
	// propriedade de conveniencia
	public void setDataConclusaoString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setDataConclusao(setDataString(data));
	}
	
	// propriedade de conveniencia
	public void setDataInicioString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setDataInicio(this.setDataString(data));
	}
	
	// propriedade de conveniencia
	private Date setDataString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return df.parse(data);
		} catch (Exception e) {
			return new Date();
		}
	}

}
