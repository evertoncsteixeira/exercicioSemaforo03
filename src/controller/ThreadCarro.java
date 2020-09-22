package controller;

import java.util.concurrent.Semaphore;
import model.Carro;

public class ThreadCarro extends Thread {
	private Semaphore semaforo;
	private Semaphore semaforoEsc;
	private Carro c;
	private double menorTempo;
	private static Carro[] car = new Carro[14];
	private static int cv;
	private static String[] escpista = new String[5];

	public ThreadCarro(Carro c, Semaphore semaforo, Semaphore semaforoEsc) {
		this.c = c;
		this.semaforo = semaforo;
		this.semaforoEsc = semaforoEsc;
	}

	@Override
	public void run() {
		// Inicio seção critica

		try {
			semaforo.acquire();		
			this.c.setMelhorvolta(carroNaPista());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			if (cv == 14) {
				car = classificaCarro(car);
				griLargada(car);
				cv = 0;
			}
		}

	}

	public void griLargada(Carro[] car) {
		int pos = 1;
		System.out.println("\nO GRID DE LARGADA SERÁ....\n");
		for (Carro c : car) {
			System.out.println("Carro n# " + c.getNumero() + " da escuderia " + c.getEscuderia() + "\nFoi o  " + pos
					+ "º com a melhor volta de " + (c.getMelhorvolta() / 60000));
			pos++;
		}
	}
	public void adicionarEscPista (String escuderia) {
		for (int i = 0; i < escpista.length; i++) {
			if (escpista[i] == "") {
				escpista[i] = escuderia;
				break;
			}
		}
	}
	public void removeEscPista(String escuderia) {
		for (int i = 0; i < escpista.length; i++) {
			if (escpista[i] == escuderia) {
				escpista[i] = "";
				break;
			}
		}
	}

	public boolean verificarEscPista(String escuderia) {
		boolean v = false;
		for (int i = 0; i < escpista.length; i++) {
			if (escpista[i] == escuderia) {
				v = true;
				break;
			}
		}
		return v;
	}

	public double carroNaPista() {
		double tempo = (Math.random() * 60001 + 60000);
		menorTempo = tempo;
		
	   // adicionarEscPista(c.getEscuderia());
	   // System.out.println("Carro " + c.getNumero() + " da " + c.getEscuderia() + " esta na pista.");
		for (int i = 2; i <= 3; i++) {
			tempo = (Math.random() * 60001 + 60000);
			if (menorTempo >= tempo) {
				menorTempo = tempo;
			}
		}
		System.out.println("O menor tempo do carro " + this.c.getNumero() + " foi o " + (menorTempo / 60000) + ".");
		car[cv] = c;
		cv++;
		//removeEscPista(c.getEscuderia());
		//System.out.println("Carro " + c.getNumero() + " da " + c.getEscuderia() + " saiu da pista.");
		return menorTempo;
	}

	public static Carro[] classificaCarro(Carro[] car) {
		int cont1, cont2;
		int num;
		double vol;
		String escude;
       
		for (cont1 = 0; cont1 < car.length; cont1++) {
			for (cont2 = (cont1 + 1); cont2 < car.length; cont2++) {
				if (car[cont1].getMelhorvolta() > car[cont2].getMelhorvolta()) {
					vol = car[cont1].getMelhorvolta();
					car[cont1].setMelhorvolta(car[cont2].getMelhorvolta());
					car[cont2].setMelhorvolta(vol);

					num = car[cont1].getNumero();
					car[cont1].setNumero(car[cont2].getNumero());
					car[cont2].setNumero(num);

					escude = car[cont1].getEscuderia();
					car[cont1].setEscuderia(car[cont2].getEscuderia());
					car[cont2].setEscuderia(escude);
				}
			}
		}
		return car;
	}

}
