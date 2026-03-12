import java.util.Random;

public class AccountGenerator {

    public static int generateAccountNumber(){

        Random rand = new Random();

        int acc = 100000 + rand.nextInt(900000);

        return acc;
    }
}