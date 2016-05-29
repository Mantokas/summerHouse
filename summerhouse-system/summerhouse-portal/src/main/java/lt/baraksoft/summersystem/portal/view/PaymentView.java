package lt.baraksoft.summersystem.portal.view;

import lt.baraksoft.summersystem.model.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by LaurynasC on 2016-05-04.
 */
public class PaymentView implements Serializable{

    private static final long serialVersionUID = 3270083278902411668L;

    private int id;
    private BigDecimal amount;
    private LocalDate executionDate;
    private String purpose;
    private String sender;
    private User payer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }
}
