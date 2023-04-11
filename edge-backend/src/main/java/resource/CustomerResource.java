package resource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.RestPath;

import domain.Customer;
import domain.CustomerBet;
import service.Postgres;

@ApplicationScoped
@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	@Inject
	Postgres pg;

    @POST
    public String addCust(Customer cust) {
    	try {
			pg.addCustomer(cust);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Customer added successfully";
    }

    @POST
    @Path("/bets")
    public List<CustomerBet> custBets(Map<String, String> request){
    	int custId = Integer.parseInt(request.get("custId"));
    	List<CustomerBet> cb = pg.getCustomerBets(custId);
		return cb;
    	
    }
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/membership")    
    public String getCustMemType(Map<String, String> request) {
    	return pg.getCustomerMemType(request.get("email"));
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/membership/{email}")    
    public String getCustMemType(@RestPath String email) {
    	return pg.getCustomerMemType(email);
    }    

}
