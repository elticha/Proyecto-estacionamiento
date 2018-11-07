package practicaestacionamiento;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carro implements Runnable{
    long id;
    Estacionamiento estacionamiento;
    boolean estacionado;
    public Carro(int i,Estacionamiento e){
        this.estacionamiento = e;
        this.id = i;
        this.estacionado = false;
    }

    @Override
    public void run() {
        try {
            this.estacionamiento.entrar((int)id, this);
//            this.estacionamiento.dejarLugar((int) id, this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
