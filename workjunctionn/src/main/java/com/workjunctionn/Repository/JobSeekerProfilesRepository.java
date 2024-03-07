package com.workjunctionn.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workjunctionn.Entity.JobSeekerProfiles;

@Repository
public interface JobSeekerProfilesRepository extends JpaRepository<JobSeekerProfiles, Long> {
    
	Optional<JobSeekerProfiles> findById(long jobseekerprofileid);




	
}
