import java.util.Scanner;

public class Example implements Runnable {
    public void run() {
        int temp = 0;
        System.out.println("Iniciar contador:");
        try {
            if(!Thread.interrupted()){
                while(temp < 10) {
                    System.out.println("Tiempo restante " + (10 - temp) + " segundos");
                    Thread.sleep(1000);
                    temp++;
                }
            }
        }
        catch (InterruptedException x) {
            if(temp > 5){
                System.out.println("Interrumpido despues de 5 segundos, cerramos el programa");
            }else{
                System.out.println("Interrumpido antes de 5 segundos, el programa se cerrar� en 10 segundos");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {}
                System.out.println("Fin 10 segundos");
            }
            return;
        }
        System.out.println("Finalizado");
        return;
    }

    public static void main(String[] args) throws InterruptedException {
    	Example i = new Example();
        Thread t = new Thread(i);
        t.start();
        System.out.println("Desea interrumpir el programa");

        Scanner s = new Scanner(System.in);

        if(s.hasNextLine()){
            System.out.println("Fuera");
            t.interrupt();
        }
        t.join();
        System.out.println("Program of excercise 3.3 has terminated");
    }
}