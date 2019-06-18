package br.com.traderbacktestapp.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
public class Trader extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 647389718835403860L;
	
	//@NotEmpty(message = "O campo motivo do Trader é obrigatório!")
	private String motivo;
	
	@NotEmpty(message = "O campo papel do Trader é obrigatório!")
	private String papel;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.TIME)
	private Date abertura;
	
	@Temporal(TemporalType.TIME)
	private Date fechamento;
	
	@Temporal(TemporalType.TIME)
	private Date tempo;
	
	private Integer quantidadeContrato;
	
	private String posicao;
	
	private Double prcMedioCpa;
	
	private Double prcMedioVda;
	
	private Double valor;
	

	
	
	

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	public Date getTempo() {
		return tempo;
	}

	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}

	public Integer getQuantidadeContrato() {
		return quantidadeContrato;
	}

	public void setQuantidadeContrato(Integer quantidadeContrato) {
		this.quantidadeContrato = quantidadeContrato;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Double getPrcMedioCpa() {
		return prcMedioCpa;
	}

	public void setPrcMedioCpa(Double prcMedioCpa) {
		this.prcMedioCpa = prcMedioCpa;
	}

	public Double getPrcMedioVda() {
		return prcMedioVda;
	}

	public void setPrcMedioVda(Double prcMedioVda) {
		this.prcMedioVda = prcMedioVda;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}