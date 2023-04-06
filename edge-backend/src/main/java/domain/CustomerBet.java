package domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CustomerBet {

	Integer custUid;
	Integer contestUid;
	String timestamp;
	String description;
	String betType;
	Integer betAmt;
	String betStatus;
	Integer custPayout;
	String betStartTime;
	String betEndTime;
	


	public CustomerBet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomerBet(Integer custUid, Integer contestUid, String timestamp, String description, String betType,
			Integer betAmt, String betStatus, Integer custPayout, String betStartTime, String betEndTime) {
		super();
		this.custUid = custUid;
		this.contestUid = contestUid;
		this.timestamp = timestamp;
		this.description = description;
		this.betType = betType;
		this.betAmt = betAmt;
		this.betStatus = betStatus;
		this.custPayout = custPayout;
		this.betStartTime = betStartTime;
		this.betEndTime = betEndTime;
	}


	public Integer getCustUid() {
		return custUid;
	}


	public void setCustUid(Integer custUid) {
		this.custUid = custUid;
	}


	public Integer getContestUid() {
		return contestUid;
	}


	public void setContestUid(Integer contestUid) {
		this.contestUid = contestUid;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Integer getBetAmt() {
		return betAmt;
	}

	public void setBetAmt(Integer betAmt) {
		this.betAmt = betAmt;
	}

	public String getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}

	public Integer getCustPayout() {
		return custPayout;
	}

	public void setCustPayout(Integer custPayout) {
		this.custPayout = custPayout;
	}

	public String getBetStartTime() {
		return betStartTime;
	}

	public void setBetStartTime(String betStartTime) {
		this.betStartTime = betStartTime;
	}

	public String getBetEndTime() {
		return betEndTime;
	}

	public void setBetEndTime(String betEndTime) {
		this.betEndTime = betEndTime;
	}
		
	
	
}
