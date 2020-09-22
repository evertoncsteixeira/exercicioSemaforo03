package view;

import java.util.concurrent.Semaphore;
import controller.ThreadCarro;
import model.Carro;

public class Classificacao {

	public static void main(String[] args) throws InterruptedException  {
		Carro[] car = new Carro[14];
		Semaphore semaforo = new Semaphore(5);
		Semaphore semaforoEsc = new Semaphore(0);
		String escuderia = "";
		for (int i = 1; i < 15; i++) {
			switch (i) {
			case 1 : 
				escuderia = "Ferrari";
				break;
			case 3 : 
				escuderia = "Mercedez";
				break;
			case 5 : 
				escuderia = "McLaren";
				break;
			case 7 :
				escuderia = "RedBull";
				break;
			case 9 :
				escuderia = "Aston Martin";
				break;
			case 11 :
				escuderia = "Alpha Romeo";
				break;
			case 13 :
				escuderia = "Williams";
				break;
			default :	
			}
			
			car[i-1] = new Carro(i,escuderia);
			Thread tCarro = new ThreadCarro(car[i-1], semaforo, semaforoEsc);
			tCarro.start();
		}
	}

}
