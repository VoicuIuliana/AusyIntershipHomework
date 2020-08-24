package Entity;

public class MyLogger {

    public  static void logInfo(String message){
        System.out.println("Info: " + message);
    }
    public static void logError(String message){
        System.out.println("Error: " + message);
    }
    public static void logWarning(String message){
        System.out.println("Warning: " + message);
    }

}
