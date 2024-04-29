package lt.vu.transactions;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Named
@RequestScoped
public class TransakcijosBean {
    @Inject
    private PirmasKomponentas pirmas;
    @Inject
    private AntrasKomponentas antras;

    @Getter
    private String transactionMessage;


    public void vykdytiTransakcija() {
        // Include a <br/> tag to create a new line between the transaction IDs
        this.transactionMessage = "First TransactionID: " + pirmas.getTransactionID() +
                "<br/>Second TransactionID: " + antras.getTransactionID();
    }


}
