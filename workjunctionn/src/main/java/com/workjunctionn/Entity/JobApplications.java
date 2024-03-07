package com.workjunctionn.Entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobapplications_tb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JobApplications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long jobapplicationId;
	
	
	@Column(nullable = false, length = 255, unique = true, name = "job_type")
	@NotNull(message = "Job type can not be empty")
	private String jobType;

	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(nullable = false, name = "Created_Time")
	private Date createdAt;

	
	@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	@Column(nullable = false, name = "Updated_Time")
	private Date updatedAt;

	
	
	  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH )	  
	  @JoinColumn(name = "job_id")	  
	  @JsonIgnore private Jobs jobs;
	 
	
	
	  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)  
	  @JoinColumn(name = "jobseeker_id")  
	  @JsonIgnore private JobSeeker jobseeker;
	 

}
