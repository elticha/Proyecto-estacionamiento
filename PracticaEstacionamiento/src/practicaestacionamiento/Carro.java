package practicaestacionamiento;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carro extends Thread{
    int id;
    Estacionamiento estacionamiento;
    public Carro(int id, Estacionamiento e){
        this.estacionamiento = e;
        this.id = id;
    }
    
    @Override
    public void start(){
        try {
            this.estacionamiento.estacionar(id);
//            Thread.sleep(1000);
//            this.estacionamiento.dejarLugar(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
