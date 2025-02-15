package chordfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ChordFinder is a simple Java program that determines the major, minor,
 * diminished, and augmented triads for a given musical note.
 * @version 1
 */
public class ChordFinder {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String userNote;
        int notePosition;

        // List of musical notes including sharps
        ArrayList<String> notesArrayList =
                new ArrayList<>(Arrays.asList("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"));

        // Get user input and determine the note's position in the scale
        userNote = getUserInput(notesArrayList);
        notePosition = getNotePosition(notesArrayList, userNote);

        // Print chord compositions
        System.out.print(userNote + " major chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 4, 7);
        System.out.print(userNote + " minor chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 3, 7);
        System.out.print(userNote + " diminished chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 3, 6);
        System.out.print(userNote + " augmented chord consists of: ");
        getChordNotes(notesArrayList, notePosition, 4, 8);
    }

    /**
     * Gets user input and validates it against available musical notes.
     * Converts flat notes to their equivalent sharps.
     *
     * @param notesArrayList The list of valid musical notes.
     * @return A valid musical note entered by the user.
     */
    public static String getUserInput(ArrayList<String> notesArrayList) {
        while (true) {
            String userInput;
            try {
                System.out.println("Please provide any note to get the Major, Minor, Diminished, and Augmented Triads");
                userInput = sc.nextLine().toUpperCase();

                // Convert flat notes to their sharp equivalents
                userInput = switch (userInput) {
                    case "BB" -> "A#";
                    case "DB" -> "C#";
                    case "EB" -> "D#";
                    case "GB" -> "F#";
                    case "AB" -> "G#";
                    default -> userInput;
                };

                // Validate user input
                if (!notesArrayList.contains(userInput)) {
                    System.out.println("Not a valid note.");
                    System.out.println("Here is a list of all the available notes:");
                    System.out.println(notesArrayList);
                    System.out.println("Please note that all flats will be converted to sharps");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid note, e.g., C, D#");
                sc.next();
                continue;
            }
            return userInput;
        }
    }

    /**
     * Finds the position of the given note in the musical scale.
     *
     * @param notesArrayList The list of valid musical notes.
     * @param userNote The note entered by the user.
     * @return The index of the note in the list.
     */
    public static int getNotePosition(ArrayList<String> notesArrayList, String userNote) {
        return notesArrayList.indexOf(userNote);
    }

    /**
     * Prints the notes that form a given chord based on the intervals provided.
     *
     * @param notesArrayList The list of valid musical notes.
     * @param notePosition The index of the root note in the list.
     * @param third The interval for the third note.
     * @param fifth The interval for the fifth note.
     */
    public static void getChordNotes(ArrayList<String> notesArrayList, int notePosition, int third, int fifth) {
        System.out.println(notesArrayList.get(notePosition) + " " +
                notesArrayList.get((notePosition + third) % 12) + " " +
                notesArrayList.get((notePosition + fifth) % 12));
    }
}
