package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/*
 * Remember to add mapping to persistence.xml
 * */
@Entity
@Table(name="incident_map")
public class IncidentMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
//	@Id
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
 	@Column(name="ID")
	private Long id;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="APPLICATION_CODE")
	private String applicationCode;
	
	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INCIDENT_ID")
    private Incident incident;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncidentMap )) return false;
        return id != null && id.equals(((IncidentMap) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
//	
//    @ManyToOne
//    @JoinColumn(name = "INCIDENT_ID", insertable = false, updatable = false) 
//    private Incident inicedentmap ;
//    
//      
//
//
//	public Incident getInicedentmap() {
//		return inicedentmap;
//	}
//
//
//
//	public void setInicedentmap(Incident inicedentmap) {
//		this.inicedentmap = inicedentmap;
//	}
//





	public Incident getIncident() {
		return incident;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setIncident(Incident incident) {
		this.incident = incident;
	}



//	public long getIncidentId() {
//		return incidentId;
//	}
//
//
//
//	public void setIncidentId(long incidentId) {
//		this.incidentId = incidentId;
//	}
//


	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getApplicationCode() {
		return applicationCode;
	}



	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}


 