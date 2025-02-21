import java.io.*;
import java.nio.file.*;
import java.util.List;

public class FileHandlingDemo {
    
    public static void writeToFile(String filename, String data) {
        // Writes data to a file, overwriting existing content.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
            System.out.println("Data successfully written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String filename) {
        // Reads and returns content from a file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Content of " + filename + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, String data) {
        // Appends data to a file without overwriting existing content.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.newLine();
            writer.write(data);
            System.out.println("Data successfully appended to " + filename);
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void modifyFile(String filename, String oldString, String newString) {
        // Modifies a file by replacing occurrences of a string.
        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path);
            
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, lines.get(i).replace(oldString, newString));
            }
            
            Files.write(path, lines);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "certificate.txt";
        writeToFile(fileName, "COMPLETION CERTIFICATE WILL BE ISSUED ON YOUR INTERNSHIP");
        appendToFile(fileName, "Issued by: Your Organization");
        readFromFile(fileName);
        modifyFile(fileName, "YOUR", "YOUR SUCCESSFUL");
        readFromFile(fileName);
    }
}
