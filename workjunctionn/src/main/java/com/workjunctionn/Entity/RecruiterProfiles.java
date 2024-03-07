package com.workjunctionn.Entity;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recruiterprofiles_tb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RecruiterProfiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long recruiterprofileId;

	@Column(nullable = false, length = 255, name = "Company_Website")
	@NotNull(message = "Website can not be empty")
	private String website;

	@Column(nullable = false, length = 12, name = "Company_PhoneNo")
	@NotNull(message = "Phone number must be 10 digit")
	private Long phone;

	@Column(length = 1000, name = "Company_Logo")
	private String company_logo;

	@Column(nullable = false, length = 1000, name = "Description")
	@NotNull(message = "Description can not be empty")
	private String description;

	@Column(nullable = false, length = 500, name = "Company_Address")
	@NotNull(message = "Company address can not be empty")
	private String company_address;

	@Column(nullable = false, length = 50, name = "Country_Name")
	@NotNull(message = "Country name can not be empty")
	private String countryname;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "Created_Time")
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "Updated_Time")
	private Date updated;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "recruiters_id")
	private Recruiters recruiters;

}
