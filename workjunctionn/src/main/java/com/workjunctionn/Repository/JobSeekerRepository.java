package com.workjunctionn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workjunctionn.Entity.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {


	boolean existsByEmail(String email);

	Optional<JobSeeker> findByEmail(String email);
	
	boolean existsById(long jobseekerId);

	Optional<JobSeeker> findById(long jobseekerId);


	  
	
}
