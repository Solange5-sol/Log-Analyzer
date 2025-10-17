import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;
public class ChatLogger {
    private  String filePath;
    private String userOneMessage;
    private String userTwoMessage;
    private Scanner scanner;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public ChatLogger(String filePath) {
        this.filePath = filePath;
        scanner = new Scanner(System.in);
    }
    public void run() {
        String user1 = "User1";
        String user2 = "User2";
        System.out.println("Chat Logger started. Type 'exit' to quit.");
        while (true) {
            System.out.print(user1 + " ");
            userOneMessage = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userOneMessage)) break;
            saveMessage(user1, userOneMessage);
            System.out.print(user2 + " ");
            userTwoMessage = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userTwoMessage)) break;
            saveMessage(user2, userTwoMessage);
        }
        System.out.println("Chat ended.");
        scanner.close();
    }
    private void saveMessage(String user, String message)
    {
        if (message == null)
            return;
        message = message.trim();
        if (message.isEmpty())
            return;
        String timestamp = LocalDateTime.now().format(dateTimeFormatter);
        String line= timestamp + user + " :" + message;
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true)))
        {
            out.write(line);
            out.newLine();
        }
        catch (IOException e)
        {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    private void ensureDirectoryExists() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            {
                parentDir.mkdir();
            }
        }
    }
    public void viewMessages() {
        System.out.println("\n--- Chat Log message---");
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error  " + e.getMessage());
        }
        System.out.println("--- End Chat ---\n");
    }
}