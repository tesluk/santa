package tab.os.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by andrey.tesluk on 01.12.2014.
 */
@Entity
@Table
public class ShuffleResult {

    @Id
    private String value;

    public ShuffleResult(String str) {
        value=str;
    }

    public ShuffleResult() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
