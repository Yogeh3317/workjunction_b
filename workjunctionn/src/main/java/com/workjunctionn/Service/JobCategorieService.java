package com.workjunctionn.Service;

import java.util.List;
import java.util.Optional;

import com.workjunctionn.Entity.JobCategories;

public interface JobCategorieService {

    JobCategories saveJobCategories(JobCategories jobCategories);	
	public Optional<JobCategories> getJobCategoriesById(long jobcategoriesId);
	
    void updateJobCategoriesById(JobCategories jobCategories , long jobcategoriesId);
    
	void daleteJobCategoriesBuId(long jobcategoriesId);
	
	public List<JobCategories> getAllJobCategories();
	
	
	
}
