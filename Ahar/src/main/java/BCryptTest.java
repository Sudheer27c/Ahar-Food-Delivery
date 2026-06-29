import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

    public static void main(String[] args) {

        String password = "admin123";

        String hash =
                BCrypt.hashpw(
                        password,
                        BCrypt.gensalt());

        System.out.println(hash);

        boolean valid =
                BCrypt.checkpw(
                        "admin123",
                        hash);

        System.out.println(valid);
    }
}