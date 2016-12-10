package tab.os.yana.santa.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tab.os.db.DBSession;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tab on 10.12.2016.
 */
@Entity
@Table
public class RadikLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column
    Date date;
    @Column
    String msg;

    public static void addMsg(String msg) {
        Session session = DBSession.getSession();
        Transaction transaction = session.beginTransaction();

        RadikLog log = new RadikLog();
        log.date = new Date();
        log.msg = msg;

        session.save(log);
        transaction.commit();

    }


}
