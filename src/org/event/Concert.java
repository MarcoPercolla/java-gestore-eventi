package org.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert extends Event {
    private LocalTime hour;
    private BigDecimal price;

    public Concert(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) {
        super(titolo, data, postiTotali);
        this.hour = ora;
        this.price = prezzo;
    }

    public LocalTime getOra() {
        return hour;
    }

    public void setOra(LocalTime ora) {
        this.hour = ora;
    }

    public BigDecimal getPrezzo() {
        return price;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.price = prezzo;
    }

    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return getData().format(dateFormatter) + " " + hour.format(timeFormatter);
    }

    public String getPrezzoFormattato() {
        return String.format(Locale.ITALY, "%,.2f€", price);
    }

    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - prezzo: " + getPrezzoFormattato() + " - posti prenotati: " + getPostiPrenotati() + " - posti disponibili: " + (getPostiTotali() - getPostiPrenotati());
    }
}
