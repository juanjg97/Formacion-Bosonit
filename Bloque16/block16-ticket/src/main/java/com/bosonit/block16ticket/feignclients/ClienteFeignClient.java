package com.bosonit.block16ticket.feignclients;

import com.bosonit.block16ticket.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "trip-service",url="http://localhost:8080")
@RequestMapping("/cliente")
public interface ClienteFeignClient {
    @GetMapping("/id/{idCliente}")
    public Cliente getClienteFromFeign(@PathVariable int idCliente);
}
