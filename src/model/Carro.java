package model;

public class Carro {
	private  int numero;
	private  String escuderia;
	private  double melhorvolta;
	
	public Carro (int numero, String escuderia) {
		this.numero = numero;
		this.escuderia = escuderia;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}

	public double getMelhorvolta() {
		return melhorvolta;
	}

	public void setMelhorvolta(double melhorvolta) {
		this.melhorvolta = melhorvolta;
	}
}
