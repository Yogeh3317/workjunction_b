package com.workjunctionn.Repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workjunctionn.Entity.Recruiters;

@Repository
public interface RecruitersRepository extends JpaRepository<Recruiters, Long>{


	boolean existsByEmail(String email);
	Optional<Recruiters> findByEmail(String email);
	Optional<Recruiters> getJobByCompanyname(String companyname);
    //Optional<Recruiters> findByUsername(String username);
    	

}
