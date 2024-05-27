package org.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProgrammEventi {
    private String titolo;
    private List<Event> eventi;

    public ProgrammEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Event evento) {
        eventi.add(evento);
    }

    public List<Event> getEventiByData(LocalDate data) {
        List<Event> eventiFiltrati = new ArrayList<>();
        for (Event evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiFiltrati.add(evento);
            }
        }
        return eventiFiltrati;
    }

    public int getNumeroEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        eventi.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(titolo + "\n");
        List<Event> eventiOrdinati = new ArrayList<>(eventi);
        ordinaEventiPerData(eventiOrdinati);

        for (Event evento : eventiOrdinati) {
            sb.append(evento.toString()).append("\n");
        }

        return sb.toString();
    }

    private void ordinaEventiPerData(List<Event> eventi) {
        for (int i = 0; i < eventi.size() - 1; i++) {
            for (int j = 0; j < eventi.size() - i - 1; j++) {
                if (eventi.get(j).getData().isAfter(eventi.get(j + 1).getData())) {
                    Event temp = eventi.get(j);
                    eventi.set(j, eventi.get(j + 1));
                    eventi.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        ProgrammEventi programma = new ProgrammEventi("Programma Eventi Estivi");


        Event evento1 = new Event("Conferenza sulla tecnologia", LocalDate.of(2024, 6, 15), 100);
        Concert concerto1 = new Concert("Marcia su Washington", LocalDate.of(2024, 7, 20), 200, LocalTime.of(21, 0), new BigDecimal("25.50"));
        Event evento2 = new Event("Mostra su Tolkien", LocalDate.of(2024, 8, 8), 50);
        Concert concerto2 = new Concert("One Piece Finale live", LocalDate.of(2024, 8, 8), 150, LocalTime.of(19, 30), new BigDecimal("30.00"));


        programma.aggiungiEvento(evento1);
        programma.aggiungiEvento(concerto1);
        programma.aggiungiEvento(evento2);
        programma.aggiungiEvento(concerto2);


        System.out.println(programma.toString());


        LocalDate data = LocalDate.of(2024, 8, 10);
        System.out.println("Eventi il " + data + ":");
        for (Event e : programma.getEventiByData(data)) {
            System.out.println(e);
        }


        System.out.println("Numero di eventi : " + programma.getNumeroEventi());


        programma.svuotaEventi();
        System.out.println("Numero di eventi dopo aver svuotato la lista: " + programma.getNumeroEventi());
    }
}
