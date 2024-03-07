package com.workjunctionn.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workjunctionn.DTO.JobSeekerSignupDTO;
import com.workjunctionn.DTO.JobseekerLoginDTO;
import com.workjunctionn.DTO.RecruiterLoginDTO;
import com.workjunctionn.DTO.RecruiterSignupDTO;
import com.workjunctionn.Entity.JobSeeker;
import com.workjunctionn.Entity.Recruiters;
import com.workjunctionn.Entity.Role;
import com.workjunctionn.Exception.UserIdNotFoundException;
import com.workjunctionn.Repository.RecruitersRepository;
import com.workjunctionn.Repository.RoleRepository;
import com.workjunctionn.Service.RecruiterService;

@RestController
@RequestMapping("/api/recruiter/")
@CrossOrigin(origins = "http://localhost:4200")
public class RecruitersController {

	@Autowired
	private RecruiterService recruiterService;
	
	@Autowired
	private RecruitersRepository recruitersRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	    private RoleRepository roleRepository;
	
	@PostMapping("/post/recruiter")
	public ResponseEntity<Recruiters> addJobs(@RequestBody Recruiters recruiters)
	{
	 
		Recruiters recruiter=this.recruiterService.createRecruiters(recruiters);
		return new ResponseEntity<Recruiters>(recruiter,HttpStatus.CREATED);
		
	}
	@GetMapping("/get/allrecruiters")
	public ResponseEntity<List<Recruiters>> getAllRecruiters()
	{
		return new ResponseEntity<List<Recruiters>>(this.recruiterService.getAllRecruiters(),HttpStatus.OK);
	}
	
	@GetMapping("/get/recruiterbyid/{recruiterId}")
	 public ResponseEntity<?> getRecruitersById(@PathVariable("recruiterId") long recruiterId )throws UserIdNotFoundException
	 {
		 if(this.recruiterService.getRecruitersById(recruiterId).isPresent())
		 {
			 Recruiters recruiter=this.recruitersRepository.findById(recruiterId)
					 .orElseThrow(()-> new UserIdNotFoundException("User not found for this id :"+ recruiterId));
				return new ResponseEntity<Recruiters>(recruiter,HttpStatus.OK);
		 }
		 
		 else
		 {
			 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		 }
	 }
	 
	
	@PutMapping("/update/recruiterbyid/{recruiterId}")
	public ResponseEntity<?> updateRecruiter(@PathVariable ("recruiterId")long recruiterId, @RequestBody Recruiters recruiters)throws 
	UserIdNotFoundException
	{
		Optional<Recruiters> existingRecruiters=this.recruitersRepository.findById(recruiterId);
		if(existingRecruiters.isPresent())
		{
			existingRecruiters.get().setUsername(recruiters.getUsername());
			existingRecruiters.get().setCompanyname(recruiters.getCompanyname());
			existingRecruiters.get().setEmail(recruiters.getEmail());
			existingRecruiters.get().setRecruiterPhone(recruiters.getRecruiterPhone());
			existingRecruiters.get().setRecruiterName(recruiters.getRecruiterName());
			existingRecruiters.get().setRecruiterPassword(recruiters.getRecruiterPassword());

			this.recruiterService.updateRecruiterById(existingRecruiters
		 .orElseThrow(()-> new UserIdNotFoundException("User  not found for this id :"+ recruiterId)),recruiterId);
			
			return new ResponseEntity<Recruiters>(existingRecruiters.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
		
			
	}
	
	@DeleteMapping("/delete/recruiterbyid/{recruiterId}")
	public ResponseEntity<?> deleteRecruiterById(@PathVariable ("recruiterId")long recruiterId)throws UserIdNotFoundException
	{
		if(this.recruiterService.getRecruitersById(recruiterId).isPresent()) {
			this.recruiterService.deleteRecruiterById(recruiterId);

		return new ResponseEntity<String>("Record Delated",HttpStatus.OK);
		}
		
		else {
			 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		}
}
	
	 @PostMapping("/recruiter/signin")
	    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody RecruiterLoginDTO loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getEmail(), loginDto.getRecruiterPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        Map<String, String> response=new HashMap<>();
	        response.put("message", "User signed-in successfully!");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    @PostMapping("/recruiter/signup")
	    public ResponseEntity<?> registerUser(@RequestBody RecruiterSignupDTO signUpDto){
	    	 Map<String, String> response=new HashMap<>();
	      
	        // add check for email exists in DB
	        if(recruitersRepository.existsByEmail(signUpDto.getEmail())){
	        	response.put("message","Email is already taken!");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	        // create user object
	        Recruiters recruiter=new Recruiters();
	        recruiter.setUsername(signUpDto.getUsername());
	        recruiter.setRecruiterPhone(signUpDto.getRecruiterPhone());
	        recruiter.setCompanyname(signUpDto.getCompanyname());
	        recruiter.setRecruiterName(signUpDto.getRecruiterName());
	        recruiter.setEmail(signUpDto.getEmail());
	        recruiter.setRecruiterPassword(passwordEncoder.encode(signUpDto.getRecruiterPassword()));

	        Role roles = roleRepository.findByName("ROLE_RECRUITER").get();
	        recruiter.setRoles(Collections.singleton(roles));
	        
	        recruitersRepository.save(recruiter);
	        
	       
	        response.put("message", "Recruiter registered successfully");

	        return new ResponseEntity<>(response, HttpStatus.OK);

	    }

}