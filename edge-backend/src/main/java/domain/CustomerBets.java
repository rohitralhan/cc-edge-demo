package domain;

import java.util.List;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CustomerBets {

	List<CustomerBet> cb;

	public CustomerBets(List<CustomerBet> cb) {
		super();
		this.cb = cb;
	}

	public CustomerBets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CustomerBet> getCb() {
		return cb;
	}

	public void setCb(List<CustomerBet> cb) {
		this.cb = cb;
	}
	
}
