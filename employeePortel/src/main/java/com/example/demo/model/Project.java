package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import java.util.Date;

@Entity
@Data 
@Valid
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;

    private String pName;
    private Long pManagerId;

    private String pStatus;
    private Date pStartDate;
    private Date pEndDate;
    

	public Project(String pName, Long pManagerId, String pStatus, Date pStartDate, Date pEndDate) {
		this.pName = pName;
		this.pManagerId = pManagerId;
		this.pStatus = pStatus;
		this.pStartDate = pStartDate;
		this.pEndDate = pEndDate;
	}

	public Project(Long pId, String pName, Long pManagerId, String pStatus, Date pStartDate, Date pEndDate) {

		this.pId = pId;
		this.pName = pName;
		this.pManagerId = pManagerId;
		this.pStatus = pStatus;
		this.pStartDate = pStartDate;
		this.pEndDate = pEndDate;
	}

	public Project() {
		
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Long getpManagerId() {
		return pManagerId;
	}

	public void setpManagerId(Long pManagerId) {
		this.pManagerId = pManagerId;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public Date getpStartDate() {
		return pStartDate;
	}

	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}

	public Date getpEndDate() {
		return pEndDate;
	}

	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}
    
	
}
