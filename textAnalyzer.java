import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class textAnalyzer {

    /**
     * This function reads the file the user inputs.
     * @param fileName the function reads the file and stores it as a string.
     * @return the function returns the text from the file.
     */
    public static String readFile(String fileName) {
        try {
            String result = "";
            Scanner fsc = new Scanner(new File(fileName));
            String line;
            while (fsc.hasNextLine()) {
                line = fsc.nextLine();
                result = result + line + " ";
            }
            result = result.trim();
            fsc.close();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * This function prints the welcome banner for the program
     */
    public static void printWelcome() {
        System.out.println("*".repeat(79));
        System.out.print(" ".repeat(26));
        System.out.println("Welcome to TextAnalyzer V1.0");
        System.out.println("*".repeat(79));
    }

    /**
     * This function prints the menu for the user to choose from for what task the user wants the program to perform
     */
    public static void printMenu() {
        System.out.println("Here are your options: ");
        System.out.println("1. Count the number of vowels.");
        System.out.println("2. Count the number of consonants.");
        System.out.println("3. Count the number of words.");
        System.out.println("4. Print a summary to a file.");
        System.out.println("5. Quit");
    }

    /**
     * This function reads the user input for their choice
     * @return the function stores the user choice as an int and returns it to the main function
     */
    public static int userChoice() {
        int userChoice;
        Scanner scanner = new Scanner(System.in);
        userChoice = scanner.nextInt();
        return userChoice;
    }

    /**
     * This function reads the text file and counts the number of vowels then stores the number as an int
     * @param text This is the text from the users file
     * @return The function returns the number of vowels as an int
     */
    public static int numOfVowel(String text) {
        return text.replaceAll("[^aeiouAEIOU]", "").length();
    }

    /**
     * This function reads the text file and counts the number of consonants then stores the number as an int
     * @param text This is the text from the users file
     * @return The function returns the number of consonants as an int
     */
    public static int numOfConsonant(String text) {
        return text.replaceAll("[aeiouAEIOU//s]","").length();
    }

    /**
     * This function reads the text file and counts the number of words then stores the number as an int
     * @param text This is the text from the users file
     * @return The function returns the number of words as an int
     */
    public static int numOfWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    /**
     * This function writes to a file the user chooses and writes a summary of the text to the file
     * @param fileName The function stores the new file the user inputs as a string
     * @param text The function starts writing in the new file by printing the text again
     * @return The function returns an error message if the file is not found
     */
    public static boolean writeSummary(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Text: " + text);
            writer.newLine();
            writer.write("Number of vowels: " + numOfVowel(text));
            writer.newLine();
            writer.write("Number of consonants: " + numOfConsonant(text));
            writer.newLine();
            writer.write("Number of words: " + numOfWords(text));
            return true;
        } catch (IOException e) {
            System.out.println("Error: Unable to write to the file.");
            return false;
        }
    }

    /**
     * This is the main function where it reads all the other functions and accepts the user choice to know which function to call on.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        printWelcome();
        System.out.print("What text file would you like to analyze? ");
        fileName = scanner.nextLine();

        String allTogether = readFile(fileName);
        if (allTogether == null) {
            System.out.println("The file could not be read");
        } else {
            System.out.println(allTogether);
        }

      String text = readFile(fileName);

       if (text != null) {
            boolean continueProgram = true;
            while(continueProgram) {
                printMenu();
                int choice = userChoice();
                switch (choice) {
                    case 1:
                        int numOfVowel = numOfVowel(text);
                        System.out.println("There are " + numOfVowel + " vowels.");
                        break;
                    case 2:
                        int numOfConsonant = numOfConsonant(text);
                        System.out.println("There are " + numOfConsonant + " consonants.");
                        break;
                    case 3:
                        int numOfWords = numOfWords(text);
                        System.out.println("There are " + numOfWords + " words.");
                        break;
                    case 4:
                        System.out.print("Enter the name of the file to write the summary: ");
                        String summaryFile = scanner.nextLine();
                        boolean success = writeSummary(summaryFile, text);
                        if (success) {
                            System.out.println("The summary was written to a file.");
                        } else {
                            System.out.println("Failed to write the summary to the file.");
                        }
                        break;
                    case 5:
                        continueProgram = false;
                        System.out.println("Thank you for using this program.");
                        break;
                    default:
                        System.out.println("That is not a valid choice.");


                 }
            }
       }
    }
}

