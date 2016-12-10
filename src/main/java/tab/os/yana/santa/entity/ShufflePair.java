package tab.os.yana.santa.entity;

import org.hibernate.Session;
import tab.os.db.DBSession;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tab on 10.12.2016.
 */
@Entity
@Table
public class ShufflePair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne
    UserWithBD who;
    @OneToOne
    UserWithBD to;

    public ShufflePair() {
    }

    public ShufflePair(UserWithBD who, UserWithBD to) {
        this.who = who;
        this.to = to;
    }

    public String getWho() {
        return who == null ? "Anonymous" : who.getName();
    }

    public String getTo() {
        return to == null ? "Anonymous" : to.getName();
    }

    public static void generate() {
        RadikLog.addMsg("Start");

        Random r = new Random();
        Session session = DBSession.getSession();

        List<UserWithBD> list = session.createCriteria(UserWithBD.class).list();
        List<UserWithBD> users = new ArrayList<>();

        RadikLog.addMsg(String.format("Users found %s", list.size()));

        for (UserWithBD u : list) {
            if (u.getGroup() >= 0) {
                users.add(u);
            }
        }
        RadikLog.addMsg(String.format("Users after filter %s", users.size()));
        boolean valid = false;
        List<ShufflePair> res = null;
        while (!valid) {
            res = new ArrayList<ShufflePair>();
            List<UserWithBD> who = new ArrayList<>(users);
            List<UserWithBD> to = new ArrayList<>(users);

            for (UserWithBD w : who) {
                UserWithBD t = to.remove(r.nextInt(to.size()));
                res.add(new ShufflePair(w, t));
            }

            valid = validate(res);
        }

        session.beginTransaction();
        for (ShufflePair p : res) {
            session.save(p);
        }
        session.getTransaction().commit();
    }

    private static boolean validate(List<ShufflePair> pairs) {
        for (ShufflePair p : pairs) {
            if (p.who.getId() == p.to.getId()) {
                return false;
            }
            if (p.who.getGroup() == p.to.getGroup() && p.who.getGroup() > 0) {
                return false;
            }
        }
        return true;
    }
}
