package tab.os.miss.me;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tab.os.db.DBSession;

import javax.persistence.*;
import java.util.Date;

/**
 * @author andrew.tesliuk
 */
@Entity @Table public class Access {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
    @Column public String ip;
    @Column public String code;
    @Column public String method;
    @Column public Date timestamp;

    public static void logAccess(String ip, String code, String method) {
        Session session = DBSession.getSession();
        Transaction transaction = session.beginTransaction();

        Access access = new Access();
        access.ip = ip;
        access.code = code;
        access.method = method;
        access.timestamp = new Date();

        session.save(access);
        transaction.commit();
    }

}
