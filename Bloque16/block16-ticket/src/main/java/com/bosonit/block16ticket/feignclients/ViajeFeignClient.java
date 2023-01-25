package com.bosonit.block16ticket.feignclients;

import com.bosonit.block16ticket.models.Cliente;
import com.bosonit.block16ticket.models.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "trip-service",url="http://localhost:8080")
@RequestMapping("/viaje")
public interface ViajeFeignClient {
    @GetMapping("/id/{idViaje}")
    public Viaje getViajeFromFeign(@PathVariable int idViaje);
}
