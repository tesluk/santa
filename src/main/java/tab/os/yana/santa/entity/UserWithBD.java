package tab.os.yana.santa.entity;

import tab.os.yana.santa.INNValidator;
import tab.os.yana.santa.service.InnService;

import javax.persistence.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author andrew.tesliuk
 */
@Entity @Table public class UserWithBD {

    private static Date START = new Date(631152000000L);

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;

    @Column private String name;

    @Column private String bd;

    public long getId() {
        return id;
    }

    public UserWithBD setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserWithBD setName(String name) {
        this.name = name;
        return this;
    }

    public String getBd() {
        return bd;
    }

    public UserWithBD setBd(String bd) {
        this.bd = bd;
        return this;
    }

    public static void main(String[] args) throws ParseException, IOException {
        System.out.println(START);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        System.out.println(InnService.analizeINN("3349817717"));

        System.out.println(INNValidator.isValidINN("3349817717"));
        System.out.println(INNValidator.isValidCorrespondingDate("3349817717", "1991.09.18"));
    }

}
