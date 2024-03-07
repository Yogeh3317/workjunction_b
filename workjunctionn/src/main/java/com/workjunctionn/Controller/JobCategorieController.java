package com.workjunctionn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workjunctionn.Entity.JobCategories;
import com.workjunctionn.Entity.Jobs;
import com.workjunctionn.Entity.Recruiters;
import com.workjunctionn.Exception.UserIdNotFoundException;
import com.workjunctionn.Repository.JobCategoriesRepository;
import com.workjunctionn.Repository.JobsRepository;
import com.workjunctionn.Repository.RecruitersRepository;
import com.workjunctionn.Service.JobCategorieService;


@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JobCategorieController {

	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private RecruitersRepository recruitersRepository;
	
	@Autowired
	private JobCategoriesRepository jobCategoriesRepository; 
	
	@Autowired
	private JobCategorieService jobCategorieService;

	
	@GetMapping("/jobcategory/{jobTitle}")
	 public ResponseEntity<?> getJobByJobTitle(@PathVariable("jobTitle") String jobTitle) throws UserIdNotFoundException
	 {
	
			Jobs jobs=this.jobsRepository.getJobByJobTitle(jobTitle)
			.orElseThrow(()-> new UserIdNotFoundException("User not found for this job title :"+jobTitle));
				return new ResponseEntity<Jobs>(jobs,HttpStatus.OK);
		 
	 }
	
	@PostMapping("/jobcategory")
	public ResponseEntity<JobCategories> createJobCategory(@RequestBody JobCategories jobCategory) {
	    JobCategories createdCategory = jobCategorieService.saveJobCategories(jobCategory);
	    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}
	@GetMapping("/jobcategory/{companyname}")
	 public ResponseEntity<?> getJobByCompanyName(@PathVariable("companyname") String companyname ) throws UserIdNotFoundException
	 {
		 
		 
			Recruiters recruiters=this.recruitersRepository.getJobByCompanyname(companyname)
					 .orElseThrow(()-> new UserIdNotFoundException("User not found for this company name :"+companyname));
				return new ResponseEntity<Recruiters>(recruiters,HttpStatus.OK);
		 }
		
	@GetMapping("/jobcategory/{categoryName}")
	 public ResponseEntity<?> getJobCategoryByName(@PathVariable("categoryName") long categoryName ) throws UserIdNotFoundException
	 {
		 
			 JobCategories jobCategorie=this.jobCategoriesRepository.getJobByCategoryName(categoryName)
				.orElseThrow(()-> new UserIdNotFoundException("User not found for this category name :"+categoryName));
				return new ResponseEntity<JobCategories>(jobCategorie,HttpStatus.OK);
		
	 }
	
	
}
