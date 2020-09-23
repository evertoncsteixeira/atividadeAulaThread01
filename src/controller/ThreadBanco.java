package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private int idthread;
	private Semaphore semaforo;

	public ThreadBanco(int idthread, Semaphore semaforo) {
		this.idthread = idthread;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		int v = 0;
		switch (this.idthread % 3) {
		case 1:
			v = 2;
			break;
		case 2:
			v = 3;
			break;
		case 0:
			v = 3;
			break;
		}
		
		for (int i = 0; i < v; i++) {
			calcular();
			try {
				semaforo.acquire();
				calculaBanco(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}
	public void calcular() {
		int tempo = 0;
		switch (this.idthread % 3) {
		case 1:
			tempo = (int) ((Math.random() * 801) + 200);
			break;
		case 2:
			tempo = (int) ((Math.random() * 1001) + 500);
			break;
		case 0:
			tempo = (int) ((Math.random() * 1001) + 1000);
			break;
		}
		System.out.println("Calculando... ");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Calculo finalizado");
		}
	}

	public void calculaBanco(int tempo) {
		System.out.println("Transação BD....");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Transação BD finalizada");
		}
	}
}
