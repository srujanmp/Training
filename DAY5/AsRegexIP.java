import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

// Simple custom exception
class InvalidIPException extends Exception {
    public InvalidIPException(String message) {
        super(message);
    }
}

public class AsRegexIP {
    private static final Logger logger = Logger.getLogger(AsRegexIP.class.getName());
    
    // Simple regex for matching 0-255 octets separated by dots
    private static final String IP_PATTERN = 
        "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    public static void validateIP(String ip) throws InvalidIPException {
        if (ip == null || !Pattern.matches(IP_PATTERN, ip.trim())) {
            throw new InvalidIPException("IP Address format is invalid.");
        }
        logger.info("IP Address: " + ip + " is valid.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        logger.info("Enter IPv4 Address: ");
        String ip = sc.nextLine();

        try {
            validateIP(ip);
        } catch (InvalidIPException e) {
            logger.warning("Validation failed: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
