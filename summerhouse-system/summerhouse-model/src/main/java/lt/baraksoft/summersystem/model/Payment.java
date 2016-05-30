package lt.baraksoft.summersystem.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-03.
 */

@Entity
@Table(name = "payments")
public class Payment implements IEntity<Integer>{

    private static final long serialVersionUID = 4921658470407123725L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Digits(integer = 4, fraction = 2)
    @Column(name = "amount", precision = 4, scale = 2)
    private BigDecimal amount;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "purpose")
    private String purpose;

    @ManyToOne(optional = false)
    private User user;

    @Version
    private Integer version;

    public Payment() {
    }

    public Payment(Integer id) {
        this.id = id;
    }

    public Payment(Integer id, BigDecimal amount, LocalDate executionDate) {
        this.id = id;
        this.amount = amount;
        this.executionDate = executionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
