package com.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase servicios reservación
 *
 * @author user
 */
@Service
public class serviciosReservation {

    /**
     * Meétodo repositorio
     */
    @Autowired
    private ReservationRepositorio metodosCrud;

    /**
     * Método lista reservación
     *
     * @return
     */
    public List<Reservation> getAll() {
        return metodosCrud.getAll();
    }

    /**
     * método reservación opcional
     *
     * @param idReservation
     * @return
     */
    public Optional<Reservation> getReservation(int idReservation) {
        return metodosCrud.getReservation(idReservation);
    }

    /**
     * método guardar datos reservación
     *
     * @param reservation
     * @return
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return metodosCrud.save(reservation);
        } else {
            Optional<Reservation> evt = metodosCrud.getReservation(reservation.getIdReservation());
            if (evt.isEmpty()) {
                return metodosCrud.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    /**
     * Métodos actualizar datos reservación
     *
     * @param reservation
     * @return
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> evt = metodosCrud.getReservation(reservation.getIdReservation());
            if (!evt.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    evt.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    evt.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    evt.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(evt.get());
                return evt.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * Métodos eliminar datos reservación
     *
     * @param idReservation
     * @return
     */
    public boolean deleteReservation(int idReservation) {
        Boolean del = getReservation(idReservation).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return del;
    }

}
