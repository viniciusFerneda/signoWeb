package br.com.vinicius.signo.model;

public class Signo {

	private Integer codigo;
	private String nome;
	private Integer diaInicial;
	private Integer diaFinal;
	private Integer mesInicial;
	private Integer mesFinal;

	public Signo() {
	}

	public Signo(Integer codigo, String nome, Integer diaInicial, Integer diaFinal, Integer mesInicial,
			Integer mesFinal) {
		this.codigo = codigo;
		this.nome = nome;
		this.diaInicial = diaInicial;
		this.diaFinal = diaFinal;
		this.mesInicial = mesInicial;
		this.mesFinal = mesFinal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDiaInicial() {
		return diaInicial;
	}

	public void setDiaInicial(Integer diaInicial) {
		this.diaInicial = diaInicial;
	}

	public Integer getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(Integer diaFinal) {
		this.diaFinal = diaFinal;
	}

	public Integer getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	public Integer getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

}
