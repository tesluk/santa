package tab.os.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by andrey.tesluk on 18.11.2014.
 */
@Entity
@Table
public class HappyUser {

    @Id
    private String invite;
    private String name;
    private String publicKey;

    public HappyUser(String name, String publicKey) {
        this.name = name;
        this.publicKey = publicKey;
    }

    public HappyUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
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

    @Override
    public String toString() {
        return "HappyUser{" +
                "invite='" + invite + '\'' +
                ", name='" + name + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }
}
