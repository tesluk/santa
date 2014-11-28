package tab.os.tools;

/**
 * Created by andrey.tesluk on 18.11.2014.
 */
public class HappyUser {

    private String name;
    private String publicKey;

    public HappyUser(String name, String publicKey) {
        this.name = name;
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HappyUser)) {
            return false;
        }
        HappyUser other = (HappyUser) obj;
        return other.name.equals(name) && other.publicKey.equals(publicKey);
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ publicKey.hashCode();
    }
}
