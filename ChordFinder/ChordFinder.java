package ChordFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChordFinder {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String userNote;
        int notePosition;
        ArrayList<String> notesArrayList =
                new ArrayList<>(Arrays.asList("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"));

        userNote = getUserInput(notesArrayList);



        notePosition = getNotePosition(notesArrayList, userNote);

        System.out.print(userNote + " major chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 4, 7);
        System.out.print(userNote + " minor chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 3, 7);
        System.out.print(userNote + " diminished chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 3, 6);
        System.out.print(userNote + " augmented chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 4, 8);
    }

    public static String getUserInput(ArrayList<String> notesArrayList) {
        while (true) {
            String userInput;
            try {
                System.out.println("Please provide any note to get the Major, Minor, Diminished, and Augmented Triads");
                userInput = sc.nextLine();
                userInput = switch (userInput.toUpperCase()) {
                    case "BB" -> "A#";
                    case "DB" -> "C#";
                    case "EB" -> "D#";
                    case "GB" -> "F#";
                    case "AB" -> "G#";
                    default -> userInput;
                };
                if (!notesArrayList.contains(userInput.toUpperCase())) {
                    System.out.println("Not a valid note.");
                    System.out.println("Here is a list of all the available notes:");
                    System.out.println(notesArrayList);
                    System.out.println("Please note that all flats will be converted to sharps");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid note, eg. C, D#");
                sc.next();
                continue;
            }
            return userInput.toUpperCase();
        }
    }

    public static int getNotePosition(ArrayList<String> notesArrayList, String userNote) {
            return notesArrayList.indexOf(userNote);
        }

    public static void getChordNotes(ArrayList<String> notesArrayList, int notePosition, int third, int fifth) {
        System.out.println(notesArrayList.get(notePosition) + " " + notesArrayList.get((notePosition + third) % 12) + " " +
                notesArrayList.get((notePosition + fifth) % 12));
        }
    }
