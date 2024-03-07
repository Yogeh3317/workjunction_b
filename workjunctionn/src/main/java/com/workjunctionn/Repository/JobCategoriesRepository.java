package com.workjunctionn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workjunctionn.Entity.JobCategories;
@Repository
public interface JobCategoriesRepository extends JpaRepository<JobCategories, Long>{

	Optional<JobCategories> getJobByCategoryName(long categoryName);

}
