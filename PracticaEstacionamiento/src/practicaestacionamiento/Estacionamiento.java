package practicaestacionamiento;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Estacionamiento{
    int espacios_disponibles;
    int carros_esperando_entrar;
    int carros_esperando_salir;
    public Estacionamiento(int lugares){
        this.espacios_disponibles = lugares;
        this.carros_esperando_entrar = 0;
        this.carros_esperando_salir = 0;
    }
    public void estacionar(int id) throws InterruptedException{
        System.out.println("El carro " + id + " esta intentando entrar al estacionamiento");
        System.out.println("Carros esperando entrar => " + carros_esperando_entrar);
        synchronized(this)
        {
            while(carros_esperando_salir > 0){
                System.out.println("Hay carros saliendo, por lo que se pone en espera");
                carros_esperando_entrar++;
                System.out.println("Carros esperando entrar => " + carros_esperando_entrar + " ; Carros esperando salir => " + carros_esperando_salir);
                wait();
            }
        }
        
        //ocupar un lugar
        synchronized(this){
            if(espacios_disponibles > 0){
                espacios_disponibles--;
                System.out.println("Espacios disponibles [ " + espacios_disponibles + " ]");
                if(carros_esperando_entrar > 0)
                {
                    carros_esperando_entrar--;
                    System.out.println("Carros esperando entrar => " + carros_esperando_entrar + " ; Carros esperando salir => " + carros_esperando_salir);
                }
            }else{
                carros_esperando_entrar++;
            }
            
            notifyAll();
        }
        
    }
    
    public void dejarLugar(int id) throws InterruptedException{
        synchronized(this){
            while(carros_esperando_entrar > 0){
                carros_esperando_salir++;
                wait();
            }
        }
        //salir
        synchronized(this){
            System.out.println("El carro " + id + " esta saliendo del estacionamiento");
            espacios_disponibles++;
            if(espacios_disponibles > 0)
            {
                if(carros_esperando_salir > 0 )
                {
                    carros_esperando_salir--;
                }
            }
            notifyAll();
        }
    }
}
