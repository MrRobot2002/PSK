package lt.vu.transactions;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.TransactionSynchronizationRegistry;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lt.vu.transactions.AntrasKomponentas;

import java.io.Serializable;
import java.util.Date;

@Named
//@Transactional
@RequestScoped
//@SessionScoped
//@ApplicationScoped
public class PirmasKomponentas implements Serializable {

    @Inject
    private AntrasKomponentas antras;

    @Resource
    private TransactionSynchronizationRegistry tx;

    @Transactional
    public String getTransactionID(){
        return "First Component txID: " + tx.getTransactionKey();
    }
    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString() + " " + antras.getClass().getName();
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }


    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}