package com.workjunctionn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.workjunctionn.Entity.JobApplications;

@Repository
public interface JobApplicationsRepository extends JpaRepository<JobApplications, Long> {
	
	@Query("SELECT SUM(jobapplicationId) FROM JobApplications")
	int totalId();

}
