package tab.os.entities;

import javax.persistence.*;

/**
 * Created by andrey.tesluk on 01.12.2014.
 */
@Entity
@Table
public class ShuffleResult {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(columnDefinition="TEXT")
    private String value;

    public ShuffleResult(String str) {
        value=str;
    }

    public ShuffleResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        s
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
