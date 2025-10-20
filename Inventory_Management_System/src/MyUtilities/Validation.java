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

    public static boolean isValidSSN(String ssn) {
        return (ssn != null && ssn.matches("\\d{9}"));
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return (phoneNumber != null && phoneNumber.matches("\\d{11}"));
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        } else if (!email.contains("@") || !email.contains(".")) {
            return false;
        }

        int ATindex = email.indexOf("@");
        int LastATindex = email.lastIndexOf("@");
        int DOTindex = email.lastIndexOf(".");

        if (ATindex > DOTindex) {
            return false;
        } else if (email.startsWith("@") || email.startsWith(".") ||
                email.endsWith("@") || email.endsWith(".")) {
            return false;
        } else if (ATindex != LastATindex) {
            return false;
        } else if (DOTindex - ATindex == 1) {
            return false;
        }

        return true;
    }
}
