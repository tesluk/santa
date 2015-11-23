package tab.os.smart.room.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by andrey.tesluk on 28.01.2015.
 */
@Entity
@Table
public class Indication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    private long value;

    @ManyToOne
    private Tool tool;

    public Indication(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @PrePersist
    @PreUpdate
    public void resetTime(){
        time=new Date();
    }
}
