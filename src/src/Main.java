public class Main {
    public static void main(String[] args) {
        ChatLogger chatLogger = new ChatLogger("resources/chat_log.txt");
        chatLogger.run();
        chatLogger.viewMessages();
    }
}