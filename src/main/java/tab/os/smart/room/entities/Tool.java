package tab.os.smart.room.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrey.tesluk on 28.01.2015.
 */
@Entity
@Table(name = "tool")
public class Tool {

    @Id
    //@Contended("tool_id")
    private String id;

    private String pass;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_id")
    private List<Indication> indications;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Indication> getIndications() {
        return indications;
    }

    public void setIndications(List<Indication> indications) {
        this.indications = indications;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
