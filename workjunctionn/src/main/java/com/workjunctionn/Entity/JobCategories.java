package com.workjunctionn.Entity;

import java.util.Date;
import java.util.List;

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
@Table(name = "jobcategories_tb")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JobCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long jobcategoriesId;

	
	@Column(name = "Category_Name", nullable = false, length = 255)
	@NotNull(message = "Category Name can not be empty ")
	private String categoryName;

	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "Created_Time")
	private Date createdAt;

	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "Updated_Time", nullable = false)
	private Date updatedAt;

	
	/*
	 * @OneToOne(mappedBy = "jobcategories",orphanRemoval = true,cascade =
	 * CascadeType.ALL) private Jobs jobs;
	 */
}
