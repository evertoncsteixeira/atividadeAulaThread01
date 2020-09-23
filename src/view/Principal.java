package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 21; i++){
			Thread tBanco = new ThreadBanco(i+1,semaforo);
			tBanco.start();
		}

	}

}
