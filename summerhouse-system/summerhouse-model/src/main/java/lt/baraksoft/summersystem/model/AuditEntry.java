package lt.baraksoft.summersystem.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * Created by Žygimantas on 2016-05-27.
 */
@Entity
public class AuditEntry implements IEntity<Integer>{

    private static final long serialVersionUID = -8034914034750823569L;

    public AuditEntry(String entry, LocalDateTime dateCreated){
        this.entry = entry;
        this.dateCreated = dateCreated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "entry")
    private String entry;

    @Version
    private Integer version;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public AuditEntry() {
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditEntry that = (AuditEntry) o;

        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;

    }

    @Override
    public int hashCode() {
        return dateCreated != null ? dateCreated.hashCode() : 0;
    }
}
