package constuction.project.data.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import construction.project.data.dto.LoginDto;
import construction.project.data.dto.ResisterDto;
import construction.project.data.service.ContractorService;

import constuction.project.data.Repository.ContractorRepository;
import constuction.project.data.beans.Contractor;
import constuction.project.data.exception.ResourceNotFoundException;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/services/")
public class ContractorController  {
	
	
	@Autowired
	private ContractorRepository CRepo;
	
	
//	@PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
//		
//		System.out.println("hii");
//        if (!file.isEmpty()) {
//            try {
//                // Specify the directory where you want to save the uploaded file
//                String uploadDir = "F:/ConstructionEase/React-frontend/construction-ease/public/assets/img/contractor/";
//                String imagePath = "/assets/img/contractor/"+file.getOriginalFilename();
//                contractor con = new contractor();
//                con.setImagepath(imagePath);
//                CRepo.save(con);
////                contractor.setImagePath(imagePath);
//                // Create the directory if it doesn't exist
//                File dir = new File(uploadDir);
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                // Save the file to the specified directory
//                File uploadedFile = new File(uploadDir + file.getOriginalFilename());
//                FileOutputStream outputStream = new FileOutputStream(uploadedFile);
//                outputStream.write(file.getBytes());
//                outputStream.close();
//
//                return "File uploaded successfully";
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "Failed to upload file";
//            }
//        } else {
//            return "File is empty";
//        }
//    }
//	
//	
//	
//	@PostMapping("/uploadmany")
//	public String handleFileUpload(@RequestParam("files") MultipartFile[] files) {
//	    try {
//	        String uploadDir = "F:/ConstructionEase/React-frontend/construction-ease/public/assets/img/contractor/";
//	        // Create the directory if it doesn't exist
//	        File dir = new File(uploadDir);
//	        if (!dir.exists()) {
//	            dir.mkdirs();
//	        }
//
//	        // Iterate over the array of files
//	        for (MultipartFile file : files) {
//	            if (!file.isEmpty()) {
//	                String imagePath = "/assets/img/contractor/" + file.getOriginalFilename();
//	                contractor con = new contractor();
//	                con.setImagepath(imagePath);
//	                CRepo.save(con);
//
//	                // Save the file to the specified directory
//	                File uploadedFile = new File(uploadDir + file.getOriginalFilename());
//	                FileOutputStream outputStream = new FileOutputStream(uploadedFile);
//	                outputStream.write(file.getBytes());
//	                outputStream.close();
//	            }
//	        }
//	        return "Files uploaded successfully";
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return "Failed to upload files";
//	    }
//	}
//	
//	
//	
//	
//	
//	
//	@GetMapping("/upload/{id}")
//	public ResponseEntity<String> getContractorImagePath(@PathVariable String id) {
//		System.out.println("hii");
//		
//		long contractorId = Long.parseLong(id);
//	    contractor contractor = CRepo.findById(contractorId)
//	            .orElseThrow(() -> new ResourceNotFoundException("Contractor not found with id: " + id));
//	    System.out.println(contractor.getImagepath());
//	    return ResponseEntity.ok(contractor.getImagepath());
//	}

	
	@Autowired
	private ContractorService cservice;
	
	
	@GetMapping("/contractors")
	public List<Contractor> getContractorData()
	{
		
		return CRepo.findAll();
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> resister(@RequestBody ResisterDto resisterdto) {
		return new ResponseEntity<String>(cservice.resister(resisterdto), HttpStatus.OK);
	}
	@PutMapping("/verifyotp")
	public ResponseEntity<String> verifyAccount(@RequestParam("email") String email,@RequestParam("otp") String otp) {
		System.out.println("IN VERIFICATION METHOD");
		return new ResponseEntity<String>(cservice.verifyAccount(email,otp), HttpStatus.OK);
	}

	@PutMapping("/regenerate-otp")
	public ResponseEntity<String> regenerateOtp(@RequestParam("email") String email) {
		return new ResponseEntity<>(cservice.regenerateOtp(email), HttpStatus.OK);
	}

	@PutMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto logindto) {
		return new ResponseEntity<>(cservice.login(logindto), HttpStatus.OK);
	}
	
	@PutMapping("/forgot-password")
	public ResponseEntity<String>forgotPassword(@RequestBody LoginDto loginDto){
		System.out.println(loginDto);
	return new ResponseEntity<>(cservice.forgotPassword(loginDto.getEmail()),HttpStatus.OK);
	}
	
	
	 // build get employee by id REST API
    @GetMapping("/contractors/{id}")
    public ResponseEntity<Contractor> getContractorById(@PathVariable long id){
        Contractor con = CRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(con);
    }

    // build update employee REST API
    @PutMapping("/contractors/{id}")
    public ResponseEntity<Contractor> updateContractor(@PathVariable long id,@RequestBody Contractor contractordetails) {
        Contractor updatecontractor = CRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updatecontractor.setUsername(contractordetails.getUsername());
        updatecontractor.setPassword(contractordetails.getPassword());
        updatecontractor.setEmail(contractordetails.getEmail());
        updatecontractor.setMobile(contractordetails.getMobile());
       

        CRepo.save(updatecontractor);

        return ResponseEntity.ok(updatecontractor);
    }

    // build delete employee REST API
    @DeleteMapping("/contractors/{id}")
    public ResponseEntity<HttpStatus> deleteContractor(@PathVariable long id){

    	Contractor con = CRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        CRepo.delete(con);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}