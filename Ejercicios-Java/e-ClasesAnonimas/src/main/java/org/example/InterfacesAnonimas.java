package org.example;

interface Jugable{
    void jugar();
}

public class InterfacesAnonimas {
    public static void main(String[] args) {
        Jugable objeto2 = new Jugable() {
            @Override
            public void jugar() {
                System.out.println("Juego futbol");
            }
        };

        objeto2.jugar();
    }
}
