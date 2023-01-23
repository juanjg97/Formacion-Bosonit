package com.bosonit.tripbackend.application.services.interfaces;

import com.bosonit.tripbackend.dtos.ViajeInput;
import com.bosonit.tripbackend.dtos.ViajeOutput;

import java.util.List;

public interface ViajeService {
    ViajeOutput addViaje(ViajeInput viajeInput) throws Exception;
    ViajeOutput getViajeById(int idViaje);
    Iterable<ViajeOutput> getAllViajes(int pageNumber, int pageSize);
    ViajeOutput updateViaje(int idViaje, ViajeInput viajeInput);
    void deleteViajeById(int idViaje);
    ViajeOutput addPassenger (int idViaje, int idPassenger);
    int countPassengers(int idViaje);
    void updateStatus(int idViaje, boolean status);


}
