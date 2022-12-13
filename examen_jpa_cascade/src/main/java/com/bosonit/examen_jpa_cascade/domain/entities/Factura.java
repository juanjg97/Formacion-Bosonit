package com.bosonit.examen_jpa_cascade.domain.entities;

import com.bosonit.examen_jpa_cascade.controllers.dtos.ClienteOutput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.FacturaInput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.FacturaOutput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaOutput;
import com.bosonit.examen_jpa_cascade.mappers.ClienteMapper;
import com.bosonit.examen_jpa_cascade.mappers.FacturaMapper;
import com.bosonit.examen_jpa_cascade.mappers.LineaMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Factura {
    @Id
    @GeneratedValue
    @Column(name="id_factura")
    int id_factura;

    @Column(name="importe_total_factura")
    Double importe_total_factura;

    //Relaciones

    //Relación con la entidad cliente Muchas facturas tienen un cliente
    //@JoinColumn => nombre de la nueva columna que tendrá la primary key del objeto cliente almacenado.
    @ManyToOne
    @JoinColumn(name="id_cliente")
    Cliente cliente;

    //Relacion con la entidad Linea, una factura tiene muchas lineas
    //mappedBy, indica,  quien está al cargo de manejar las primary key de la relación es  @ManyToOne
    //"factura" tiene el mismo nombre que el objeto que tiene JoinColumn en Linea
    //Debemos poner un ManyToOne en Linea
    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL)
    List<Linea> lineasFactura = new ArrayList<>();

    //---------------------------------------------------------Conversiones porque no funciona el mapper
    public FacturaOutput facturaToFacturaOutput(){

        FacturaOutput facturaOutput = new FacturaOutput();

        facturaOutput.setId_factura(this.id_factura);
        facturaOutput.setImporte_total_factura(this.importe_total_factura);

        ClienteOutput clienteOutput = ClienteMapper.cMapper.clienteToClienteOutput(this.cliente);
        facturaOutput.setClienteOutput(clienteOutput);

        List<LineaOutput> lineasFacturaOutput = this.lineasFactura.stream()// Permite tratar las colecciones como si de etapas de un proceso ETL, convierte de lista a stream
                                                                  .map(linea->LineaMapper.lMapper.lineaToLineaOutput(linea))// Permite tratar las colecciones como si de etapas de un proceso ETL, convierte de lista a stream
                                                                  .toList();//Conviertelo de stream a una lista

        facturaOutput.setLineaOutputList(lineasFacturaOutput);

        return facturaOutput;
    }

     public Factura facturaInputToFactura(FacturaInput facturaInput){
        Factura factura = new Factura();

        factura.setImporte_total_factura(facturaInput.getImporte_total_factura());
        return factura;
     }

    @Override
    public String toString() {
        return "Factura{" +
                "id_factura=" + id_factura +
                ", cliente=" + cliente +
                ", importe_factura=" + importe_total_factura +
                ", lineasFactura=" + lineasFactura +
                '}';
    }
}
