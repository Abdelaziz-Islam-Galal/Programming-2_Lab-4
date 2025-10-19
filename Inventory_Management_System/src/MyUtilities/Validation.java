package MyUtilities;

public class Validation {
    public static boolean isValidString(String str) {
        return (str != null && !str.trim().isEmpty());
    }
    
    public static boolean isValidPositiveNumber(double number) {
        return number >= 0;
    }
    
    public static boolean isValidPositiveInteger(int number) {
        return number >= 0;
    }
}
