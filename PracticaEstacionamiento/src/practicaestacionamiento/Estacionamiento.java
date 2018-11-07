package practicaestacionamiento;

import java.util.concurrent.ThreadLocalRandom;


public class Estacionamiento{
    int espacios_disponibles;
    int carros_esperando_entrar;
    int carros_esperando_salir;
    int MAX;
    public Estacionamiento(int lugares){
        this.espacios_disponibles = lugares;
        this.carros_esperando_entrar = 0;
        this.carros_esperando_salir = 0;
        this.MAX = lugares;
        System.out.println("Espacios disponibles [ " + espacios_disponibles + " ]");
    }
    
    public synchronized void entrar(int id, Carro c) throws InterruptedException{
        System.out.println("El carro con placa [ " + Thread.currentThread().getName() + " ] esta intentando entrar");
        while(carros_esperando_salir > 0){
            System.out.println("=== Hay " + carros_esperando_salir + " carros esperando salir ===");
            this.wait();
        }
        if(espacios_disponibles > 0)
        {
            c.estacionado = true;
            estacionar(id);
            while(c.estacionado){
                if(tiempo_aleatorio_para_salir(1, 10) < 5)
                {
                    c.estacionado = false;
                    dejarLugar(id, c);
                }
                else
                {
                    if(espacios_disponibles == 0)
                        notify();
                    else
                        this.wait();
                }
            }
        }
        else
        {
            System.out.println("El carro con placa [ " + Thread.currentThread().getName() + " ] no pudo entrar porque no hay lugar");
            carros_esperando_entrar++;
            System.out.println("Hay " + carros_esperando_entrar + " carros esperando entrar");
            this.wait();
        }
        if(espacios_disponibles==0)
        {
            this.wait();
        }
    }
    private void estacionar(int id) throws InterruptedException{
        
        System.out.println("El carro con placa [ " + Thread.currentThread().getName() + " ] se esta estacionando");
        espacios_disponibles--;
        System.out.println("Espacios disponibles [ " + espacios_disponibles + " ]");
    }
    
    public synchronized void dejarLugar(int id, Carro c) throws InterruptedException{
        while(carros_esperando_entrar > 0){
            System.out.println("=== Hay " + carros_esperando_entrar + " carros esperando entrar ===");
            this.wait();
        }
        if(espacios_disponibles < MAX)
        {
            Thread.currentThread().sleep(tiempo_aleatorio_para_salir(100, 2000));
            espacios_disponibles++;
            System.out.println("El carro con placa [ " + Thread.currentThread().getName() + " ] esta saliendo, hay [ " + espacios_disponibles + " ] espacios disponibles");
        }
        
        if(carros_esperando_entrar==0)
            this.notify();
        else
            carros_esperando_salir++;
    }
    
    private int tiempo_aleatorio_para_salir(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
