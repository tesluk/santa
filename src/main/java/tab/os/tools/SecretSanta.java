package tab.os.tools;

import tab.os.entities.HappyUser;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * Created by andrey.tesluk on 18.11.2014.
 */
public class SecretSanta {

    private static List<Set> pairs = new ArrayList<Set>();

    static {
        pairs.add(new HashSet(Arrays.asList("Andrew Tesluk", "Tanya Tesluk")));
        pairs.add(new HashSet(Arrays.asList("Eugenia Gritsenko", "Bogdan Potapskyy")));
        pairs.add(new HashSet(Arrays.asList("Alexandra Usova", "Andrey Usov")));
        pairs.add(new HashSet(Arrays.asList("Vladislav Stepanov", "Anastasia Smakovska ")));
    }

    private static Random random = new Random();

    public static List<String> get(List<HappyUser> users) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        List<HappyUser> from = users, to = users;
        while (!validate(from, to)) {
            from = shuffle(from);
            to = shuffle(to);
        }

        List<String> names = new ArrayList<String>();
        List<PublicKey> keys = new ArrayList<PublicKey>();

        for (int i = 0; i < users.size(); i++) {
            names.add(to.get(i).getName());
            keys.add(SantaCipher.parsePublicKey(from.get(i).getPublicKey()));
        }

//        names = addRandomPart(names);

        List<String> res = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            res.add(SantaCipher.encryptRSA(names.get(i), keys.get(i)));
        }

        return res;
    }

    private static boolean validate(List<HappyUser> from, List<HappyUser> to) {
        for (int i = 0; i < from.size(); i++) {
            if (from.get(i).equals(to.get(i))) {
                return false;
            }
            for (Set pair : pairs) {
                if (pair.contains(from.get(i).getName()) && pair.contains(to.get(i).getName())) {
                    System.out.println(String.format("Conflict pair: %s & %s", from.get(i).getName(), to.get(i).getName()));
                    return false;
                }
            }
        }

        return true;
    }

    private static List<HappyUser> shuffle(List<HappyUser> users) {
        List<HappyUser> inp = new ArrayList<HappyUser>(users);
        List<HappyUser> res = new ArrayList<HappyUser>();
        while (!inp.isEmpty()) {
            res.add(inp.remove(random.nextInt(inp.size())));
        }
        return res;
    }

//    private static List<String> addRandomPart(List<String> names) {
//        int max = 0;
//        for (String name : names) {
//            max = max < name.length() ? name.length() : max;
//        }
//        max *= 2;
//
//        List<String> res = new ArrayList<String>();
//        for (String name : names) {
//            res.add(addRandomPart(name, max));
//        }
//        return res;
//    }
//
//    private static String addRandomPart(String name, int size) {
//        name = "_" + name + "_";
//        while (name.length() < size) {
//            if (random.nextBoolean()) {
//                name = name + random.nextInt(10);
//            } else {
//                name = random.nextInt(10) + name;
//            }
//        }
//        return name;
//    }

}
