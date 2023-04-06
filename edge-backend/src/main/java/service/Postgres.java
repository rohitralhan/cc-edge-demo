package service;

import domain.Customer;
import domain.CustomerBet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class Postgres {

	private static final Logger LOG = Logger.getLogger(Customer.class);

	@Inject
	DataSource dataSource;
 
    
	public void addCustomer(Customer cust) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // set auto-commit to false for transactional behavior
            // First insert statement to insert data into t1 and return uid
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO edgedemo.customer (fname, lname, email, icon, subtype) VALUES (?, ?, ?, ?, ?) RETURNING uid")) {
            	statement.setString(1, cust.getfName());
				statement.setString(2, cust.getlName());
				statement.setString(3, cust.getEmail());
				statement.setString(4, cust.getIcon());
				statement.setString(5, cust.getSubType());
				
                ResultSet rs1 = statement.executeQuery();
                Long uid = null;
                if (rs1.next()) {
                    uid = rs1.getLong("uid");
                    System.out.println(uid);
                } else {
                    conn.rollback(); 
                }		
                conn.commit();
	        } catch (SQLException e) {
	            conn.rollback(); // rollback transaction if an exception is thrown
	            LOG.error("Failed to insert data into database: " + e.getMessage());
	            //return null;
	        }
	    } catch (SQLException e) {
	        LOG.error("Failed to connect to database: " + e.getMessage());
	        //return null;
	    }
	}
    
	
	public void addCustomerBet(CustomerBet custbet) throws SQLException {
		
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // set auto-commit to false for transactional behavior
            // First insert statement to insert data into t1 and return uid
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO edgedemo.bets_contest "
            		+ "(description, bet_type, bet_amt, bet_status, cust_payout, bet_start_time, bet_end_time) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING uid")) {
            	statement.setString(1, custbet.getDescription());
				statement.setString(2, custbet.getBetType());
				statement.setInt(3, custbet.getBetAmt());
				statement.setString(4, custbet.getBetStatus());
				statement.setInt(5, custbet.getCustPayout());
				statement.setString(6, custbet.getBetStartTime());
				statement.setString(7, custbet.getBetEndTime());
				
                ResultSet rs = statement.executeQuery();
                int uid = 0;
                if (rs.next()) {
                    uid = rs.getInt("uid");
                    System.out.println(uid);
                } else {
                    conn.rollback();
                    //throw
                }
                
            // Second insert statement to insert data into t1 and return uid
            try (PreparedStatement statement1 = conn.prepareStatement("INSERT INTO edgedemo.customer_bets "
            		+ "(cust_uid, contest_uid, timestamp)"
                	+ "VALUES (?, ?, ?) RETURNING uid")) {
            	statement1.setInt(1, custbet.getCustUid());
            	statement1.setInt(2, uid);
            	statement1.setString(3, custbet.getTimestamp());
    				
                ResultSet rs1 = statement1.executeQuery();
                int uid1 = 0;
                if (rs1.next()) {
                    uid1 = rs1.getInt("uid");
                    System.out.println(uid1);
                } else {
                    conn.rollback();
                    //throw
                }                
                conn.commit();
                
	        } catch (SQLException e) {
	            conn.rollback(); // rollback transaction if an exception is thrown
	            LOG.error("Failed to insert data into database: " + e.getMessage());
	            //return null;
	        }
	    } catch (SQLException e) {
	        LOG.error("Failed to connect to database: " + e.getMessage());
	        //return null;
	    }
       }	
	}
	
		
	public List<CustomerBet> getCustomerBets(int custId){
		List<CustomerBet> lcbs = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement("select cust_uid, contest_uid, timestamp, description,bet_type,bet_amt,bet_status,cust_payout,bet_start_time,bet_end_time \n"
            		+ "from customer_bets cb\n"
            		+ "join bets_contest bc\n"
            		+ "ON cb.cust_uid=? and bc.uid=cb.contest_uid and bet_end_time::timestamp > Now()::timestamp;")) {
            	statement.setInt(1, custId);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                	lcbs.add(new CustomerBet(rs.getInt("cust_uid"), rs.getInt("contest_uid"), rs.getString("timestamp"), 
                			rs.getString("description"), rs.getString("bet_type"), rs.getInt("bet_amt"), rs.getString("bet_status"), 
                			rs.getInt("cust_payout"), rs.getString("bet_start_time"), rs.getString("bet_end_time")));
                }              
                
	        } catch (SQLException e) {
	            LOG.error(e.getMessage());
	            //return null;
	        }
	    } catch (SQLException e) {
	        LOG.error("Connection Error: " + e.getMessage());
	        //return null;
	    }
		return lcbs;
	}
	
	public String getCustomerMemType(String email){
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement("select subtype from edgedemo.customer where email=?")) {
            	statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                	
                	return rs.getString("subtype");
                }else {
                	return "No Record Found";
                }
                
	        } catch (SQLException e) {
	            LOG.error(e.getMessage());
	        }
	    } catch (SQLException e) {
	        LOG.error("Connection Error: " + e.getMessage());
	    }
		return null;
	}	
	
}

