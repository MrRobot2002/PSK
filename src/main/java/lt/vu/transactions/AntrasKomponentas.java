package lt.vu.transactions;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.transaction.TransactionSynchronizationRegistry;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
public class AntrasKomponentas implements Serializable{

    @Resource
    private TransactionSynchronizationRegistry tx;

    public AntrasKomponentas(){
    }

//    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Transactional
    public String getTransactionID() {
        return "Second Component txID: " + tx.getTransactionKey();
    }


    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();
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
