package com.bosonit.tripbackend.controllers;

import com.bosonit.tripbackend.application.services.interfaces.ViajeService;
import com.bosonit.tripbackend.domain.entities.Viaje;
import com.bosonit.tripbackend.dtos.ViajeInput;
import com.bosonit.tripbackend.dtos.ViajeOutput;
import com.bosonit.tripbackend.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {
    
    @Autowired
    ViajeService viajeService;
    @Autowired
    private ViajeRepository viajeRepository;

    @PostMapping("/addTrip")
    public ResponseEntity<ViajeOutput> addViaje(@RequestBody ViajeInput viajeInput) throws Exception{
        ViajeOutput pO= viajeService.addViaje(viajeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }


    @GetMapping("/allTrips")
    public Iterable<ViajeOutput> getAllViajes(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {
        System.out.println("Entrando a la función obtener todas las Viajes");
        Iterable<ViajeOutput>  viajesO = viajeService.getAllViajes(pageNumber,pageSize);
        return viajesO;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getViajeById(@PathVariable int id)
    {
            ViajeOutput vO = viajeService.getViajeById(id);
            return ResponseEntity.ok().body(vO);
    }


    @PutMapping("/updateTrip")
    public ResponseEntity<ViajeOutput> updateViaje(@RequestBody ViajeInput viajeInput) {
            ViajeOutput pO = viajeService.updateViaje(viajeInput.getIdViaje(),viajeInput);
            return ResponseEntity.ok().body(pO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ViajeOutput> deleteViaje(@PathVariable int id) {
        ViajeOutput po = viajeService.getViajeById(id);
        viajeService.deleteViajeById(id);
        return ResponseEntity.ok().body(po);
    }

    @PostMapping("/addPassenger/{idTrip}/{idPassenger}")
    public ResponseEntity<?> addPassenger (@PathVariable int idTrip, @PathVariable int idPassenger) throws Error{
        ViajeOutput viaje = viajeService.addPassenger(idTrip, idPassenger);
        if(viaje==null){
            return ResponseEntity.badRequest().body("El viaje alcanzó el número máximo de pasajeros");
        }
        return ResponseEntity.ok(viaje);
    }

    @PutMapping("/updateStatus/{idTrip}/{status}")
    public ResponseEntity<String> updateStatus (@PathVariable int idTrip, @PathVariable boolean status){
        viajeService.updateStatus(idTrip, status);
        return ResponseEntity.ok().body("El estatus para el viaje con id "+idTrip+" ha cambiado");
    }

    @GetMapping("/countPassenger/{idTrip}")
    public ResponseEntity<String> countPassenger (@PathVariable int idTrip){
        int number = viajeService.countPassengers(idTrip);
        return ResponseEntity.ok().body("El viaje con id "+idTrip+" tiene "+number+" pasajeros");
    }
}
