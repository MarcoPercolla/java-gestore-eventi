package org.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Event event = null;

        try {
            System.out.println("Vuoi creare un evento o un concerto? (1 per concerto/qualsiasi per evento)");
            String tipoEvento = scanner.nextLine();

            System.out.println("Inserisci il titolo:");
            String titolo = scanner.nextLine();

            System.out.println("Inserisci la data (formato: dd/MM/yyyy):");
            String dataInput = scanner.nextLine();
            LocalDate data = parseDate(dataInput);

            System.out.println("Inserisci il numero di posti totali:");
            int postiTotali = Integer.parseInt(scanner.nextLine());

            if (tipoEvento.equalsIgnoreCase("1")) {
                System.out.println("Inserisci l'ora del concerto (formato: HH:mm):");
                String oraInput = scanner.nextLine();
                LocalTime ora = parseTime(oraInput);

                System.out.println("Inserisci il prezzo del biglietto:");
                BigDecimal prezzo = new BigDecimal(scanner.nextLine());

                event = new Concert(titolo, data, postiTotali, ora, prezzo);
                System.out.println("Concerto creato con successo: " + event.toString());
            } else {
                event = new Event(titolo, data, postiTotali);
                System.out.println("Evento creato con successo: " + event.toString());
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Errore: " + e.getMessage());
            System.exit(1);
        }

        boolean continua = true;

        while (continua) {
            try {
                System.out.println(event.toString());
                System.out.println("Vuoi fare una prenotazione? (y/other)");
                String risposta = scanner.nextLine();

                if (risposta.equalsIgnoreCase("y")) {
                    System.out.println("Quanti posti vuoi prenotare?");
                    int postiDaPrenotare = Integer.parseInt(scanner.nextLine());
                    event.prenota(postiDaPrenotare);
                    System.out.println("Prenotazione effettuata. Posti prenotati: " + event.getPostiPrenotati() +
                            ", Posti disponibili: " + (event.getPostiTotali() - event.getPostiPrenotati()));
                }

                System.out.println("Vuoi disdire una prenotazione? (y/other)");
                risposta = scanner.nextLine();

                if (risposta.equalsIgnoreCase("y")) {
                    System.out.println("Quanti posti vuoi disdire?");
                    int postiDaDisdire = Integer.parseInt(scanner.nextLine());
                    event.disdici(postiDaDisdire);
                    System.out.println("Disdetta effettuata. Posti prenotati: " + event.getPostiPrenotati() +
                            ", Posti disponibili: " + (event.getPostiTotali() - event.getPostiPrenotati()));
                }

                System.out.println("Vuoi continuare? (y/other)");
                risposta = scanner.nextLine();
                if (!risposta.equalsIgnoreCase("y")) {
                    System.out.println(event.toString());
                    continua = false;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static LocalDate parseDate(String date) throws DateTimeParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato data non valido. Inserisci la data nel formato corretto (dd/MM/yyyy).");
            throw e;
        }
    }

    private static LocalTime parseTime(String time) throws DateTimeParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato ora non valido. Inserisci l'ora nel formato corretto (HH:mm).");
            throw e;
        }
    }
}

