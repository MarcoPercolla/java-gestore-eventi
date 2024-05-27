package org.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private final int postiTotali;
    private int postiPrenotati;

    public Event(String titolo, LocalDate data, int postiTotali) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
        }


        this.title = titolo;
        this.date = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }


    public String getTitolo() {
        return title;
    }

    public void setTitolo(String titolo) {
        this.title = titolo;
    }

    public LocalDate getData() {
        return date;
    }

    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        this.date = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }





    public void prenota(int posti) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato.");
        }
        if (postiPrenotati + posti > postiTotali) {
            throw new IllegalStateException("Non ci sono abbastanza posti disponibili.");
        }
        postiPrenotati += posti;
    }



    public void disdici(int posti) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato.");
        }
        if (postiPrenotati < posti) {
            throw new IllegalStateException("Non ci sono abbastanza prenotazioni da disdire.");
        }
        postiPrenotati -= posti;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter) + " - " + title + " - posti prenotati: "  + postiPrenotati + " - posti disponibili: "  + (postiTotali-postiPrenotati);
    }


}

