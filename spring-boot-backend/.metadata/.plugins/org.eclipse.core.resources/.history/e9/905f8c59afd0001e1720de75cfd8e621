package construction.project.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import constuction.project.data.Repository.Adminrepo;
import constuction.project.data.beans.Admin;



@Service
public class Adminservice {
	@Autowired
	Adminrepo adm;

	public String login(Admin admindata) {
		Admin con= adm.findByEmail(admindata.getEmail())
				.orElseThrow(()-> new RuntimeException("Admin not found with this email "+admindata.getEmail()));
		if(admindata.getPassword().equals(con.getPassword()))
		{
			return "Login successfully";
		}
		else {
			return "Password is Incorrect";
		}
	
	}



}
