package org.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Event event = null;

        try {
            System.out.println("Inserisci il titolo dell'evento:");
            String titolo = scanner.nextLine();

            System.out.println("Inserisci la data dell'evento (formato: dd/MM/yyyy):");
            String dataInput = scanner.nextLine();
            LocalDate data = parseDate(dataInput);

            System.out.println("Inserisci il numero di posti totali:");
            int postiTotali = scanner.nextInt();
            scanner.nextLine();

            event = new Event(titolo, data, postiTotali);
            System.out.println("Evento creato con successo: " + event.toString());
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Errore: " + e.getMessage());
            System.exit(1);
        }

        boolean continua = true;

        while (continua) {
            try {
                System.out.println(event.toString());
                System.out.println("Vuoi fare una prenotazione? (y(yes)/other)");
                String risposta = scanner.nextLine();

                if (risposta.equalsIgnoreCase("y")) {
                    System.out.println("Quanti posti vuoi prenotare?");
                    int postiDaPrenotare = Integer.parseInt(scanner.nextLine());
                    event.prenota(postiDaPrenotare);
                    System.out.println("Prenotazione effettuata. Posti prenotati: " + event.getPostiPrenotati() +
                            ", Posti disponibili: " + (event.getPostiTotali() - event.getPostiPrenotati()));
                }

                System.out.println("Vuoi disdire una prenotazione? (y(yes)/other)");
                risposta = scanner.nextLine();

                if (risposta.equalsIgnoreCase("y")) {
                    System.out.println("Quanti posti vuoi disdire?");
                    int postiDaDisdire = Integer.parseInt(scanner.nextLine());
                    event.disdici(postiDaDisdire);
                    System.out.println("Disdetta effettuata. Posti prenotati: " + event.getPostiPrenotati() +
                            ", Posti disponibili: " + (event.getPostiTotali() - event.getPostiPrenotati()));
                }

                System.out.println("Vuoi continuare? (y(yes)/other)");
                risposta = scanner.nextLine();
                if (!risposta.equalsIgnoreCase("y")) {
                    System.out.println(event.toString());
                    continua = false;
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
                System.out.println(event.toString());
            }
        }

        scanner.close();
    }

    private static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
