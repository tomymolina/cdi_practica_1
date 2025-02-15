import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class Example implements Runnable{

    private int numHilo;
    private long tiempoEjecuccion;

    public Example(int numHilo){
        this.numHilo = numHilo;
    }

    public long getTiempoEjecuccion() {
        return this.tiempoEjecuccion;
    }


    public void run(){
        long inicio = System.currentTimeMillis();
        System.out.println("Hello, I'm thread number " + this.numHilo);
        System.out.println("Bye, this was thread number " + this.numHilo);
        this.tiempoEjecuccion = System.currentTimeMillis() - inicio;
    }

    public static void main (String args[]) throws InterruptedException{
        long inicio = System.currentTimeMillis();
        long inicio_creacion, total_creacion = 0, inicio_ejecuccion, total_ejecuccion = 0,
                inicio_sincronizacion, total_sincronizacion = 0;

        int numHilos = 0;

        if(args.length == 1){
            numHilos = Integer.parseInt(args[0]);
        }else{
            System.out.print("Numero incorrecto de parametros de entrada");
            return;
        }

        Vector<Thread> j = new Vector<Thread>();
        Vector<Example> runnables = new Vector<Example>();

        //Creación
        //Se cuenta el tiempo de creación
        inicio_creacion = System.currentTimeMillis();
        for(int i = 0; i < numHilos; i++){
            runnables.add(new Example(i));
            //Crear Thread
            j.add(new Thread(runnables.get(i)));
        }
        total_creacion = System.currentTimeMillis() - inicio_creacion;

        //Ejecucción
        //El tiempo de ejecucción es el tiempo desde el inicio de ejecucción hasta el final de sincronización menos el
        //tiempo total de sincronización
        inicio_ejecuccion = System.currentTimeMillis();
        for(int i = 0; i < numHilos; i++){
            j.get(i).start();
        }

        //Contador de tiempo de sincronización
        inicio_sincronizacion = System.currentTimeMillis();
        for(int i = 0; i < numHilos; i++){
            j.get(i).join();
        }
        total_sincronizacion = System.currentTimeMillis() - inicio_sincronizacion;

        total_ejecuccion = (System.currentTimeMillis() - inicio_ejecuccion) - total_sincronizacion;

        System.out.println("Tiempo de creación de hilos: " + total_creacion);
        System.out.println("Tiempo de ejecucción de hilos: " + total_ejecuccion);
        System.out.println("Tiempo de sincronización de hilos: " + total_sincronizacion);

        long fin = System.currentTimeMillis();

        System.out.println("Total " + (fin - inicio));

        System.out.println("Program 2.5 has finished");

    }

}