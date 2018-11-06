package practicaestacionamiento;

public class PracticaEstacionamiento {
    public static void main(String[] args) throws InterruptedException {
       Estacionamiento estacionamiento = new Estacionamiento(10);
       for(int i = 0; i < 500; i++){
           Carro carro = new Carro((i+1),estacionamiento);
           carro.start();
           Thread.sleep(200);
       }
    }
    
}
