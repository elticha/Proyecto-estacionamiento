package practicaestacionamiento;

public class PracticaEstacionamiento {
    public static void main(String[] args) throws InterruptedException {
       Estacionamiento estacionamiento = new Estacionamiento(2);
       for(int i = 0; i < 10; i++){
           Carro carrito = new Carro(i, estacionamiento);
           Thread hiloCarro = new Thread(carrito,"BMW-" + (i+1));
           hiloCarro.start();
           Thread.sleep(200);
       }
    }
    
}
