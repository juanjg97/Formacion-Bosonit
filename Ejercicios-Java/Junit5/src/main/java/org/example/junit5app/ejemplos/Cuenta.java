package org.example.junit5app.ejemplos;

import java.math.BigDecimal;

public class Cuenta {
    //Atributos
    private String nombre;
    private BigDecimal saldo;
    private  Banco banco;

    //Constructor
    public Cuenta(String nombre, BigDecimal saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    //Getters y Setters

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    //Métodos
    public void agregarSaldo(BigDecimal monto){
        BigDecimal nuevo_saldo = this.saldo.add(monto);
        this.saldo = nuevo_saldo;
    }

    public void quitarSaldo(BigDecimal monto){
        BigDecimal nuevo_saldo = this.saldo.subtract(monto);
        if(nuevo_saldo.compareTo(BigDecimal.ZERO)<0){
            throw new DineroInsuficienteException("Dinero insuficiente");
        }
        this.saldo = nuevo_saldo;
    }

    //Sobre escritura
    @Override
    public boolean equals(Object obj) {
        Boolean valor = false;

        if(!(obj instanceof Cuenta)){
            return false;
        }
        //Es necesario hacer el cast
        Cuenta cuenta2 = (Cuenta)obj;

        if(this.nombre == null || this.saldo == null) {
          return false;
        }

        if(this.nombre.equals(cuenta2.getNombre()) && this.saldo.equals(cuenta2.getSaldo())) {
            valor = true;
        }
        return valor;
    }


}
