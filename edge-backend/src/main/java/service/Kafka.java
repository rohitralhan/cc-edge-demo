package service;

import domain.Customer;
import domain.CustomerBet;
import domain.CustomerBets;

import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class Kafka {
	
	private static final Logger LOG = Logger.getLogger(Customer.class);
	
	@Inject
	Postgres pg;
	

    @Incoming("bets-in")
    public void receiveBet(CustomerBets cbs) {
    	try {
    		for (CustomerBet c : cbs.getCb()) {
    			pg.addCustomerBet(c);
    		System.out.println("Cust Id" + c.getCustUid());
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

    }
    /*public void receiveBets(String js) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<CustomerBet> bets = mapper.readValue(js, new TypeReference<List<CustomerBet>>(){});
            for (CustomerBet bet : bets) { System.out.println(bet.getCustUid()); }
        } catch (IOException e) {
            // handle exception
        }    	
    }*/
    
        
    
    
}
