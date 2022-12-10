package com.bosonit.examen_jpa_cascade.domain.entities;

import com.bosonit.examen_jpa_cascade.controllers.dtos.FacturaOutput;
import com.bosonit.examen_jpa_cascade.mappers.ClienteMapper;
import com.bosonit.examen_jpa_cascade.mappers.LineaMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Factura {
    @Id
    @GeneratedValue
    @Column(name="id_factura")
    int id_factura;

    @ManyToOne
    @JoinColumn(name="codigo_cliente")
    Cliente cliente;

    @Column(name="importe_factura")
    Double importe_factura;

    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL)
    List<Linea> lineasFactura = new ArrayList<>();

    public FacturaOutput facturaToFacturaOutput(){
        return new FacturaOutput(
                this.id_factura,
                this.importe_factura,
                ClienteMapper.mapper.clienteToClienteOutput(this.cliente),
                this.lineasFactura.stream().map(lineasFactura -> LineaMapper.mapper.lineaToLineaOutput(lineasFactura)).toList()
        );
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id_factura=" + id_factura +
                ", cliente=" + cliente +
                ", importe_factura=" + importe_factura +
                ", lineasFactura=" + lineasFactura +
                '}';
    }
}
