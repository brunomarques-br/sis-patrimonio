package br.com.patrimonio.dto;

public class Objeto {

	private int id;
	private int dia;
	private int mes;
	private int ano;
	private String descricao;
	private double valor;
	private boolean pg;
	
	public Objeto(){
		dia = 0;
		mes = 0;
		ano = 0;
		descricao = "";
		valor = 0.0;
		pg = true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public boolean isPg() {
		return pg;
	}
	public void setPg(boolean pg) {
		this.pg = pg;
	}
	
	
}	
