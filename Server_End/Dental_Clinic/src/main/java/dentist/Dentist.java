package dentist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity  // This tells Hibernate to make a table out of this class
public class Dentist {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long Dentist_id;
	
	@NotNull
	private String Lname;  //Last Name
	
	@NotNull
	private String Fname;  //First Name
	
	private String Gender;  //gender
	//private String Birthday;  //birthday
	private String Phonenumber; //number
	private String Email;   //email
	private String Address; //Address
	private String AdditionalInformation;
	private String Image; 
	
	public Dentist(){
		
	}
	public Dentist(String Lname,String Fname,String Gender,String Phonenumber,
			String Email, String Address,String AdditionalInformation,String Image){
		this.Lname = Lname;
		this.Fname=Fname;
		this.Gender=Gender;
		this.Phonenumber=Phonenumber;
		this.Email=Email;
		this.Address=Address;
		this.AdditionalInformation=AdditionalInformation;
		this.Image=Image;
	}
	
	public long getDentist_id(){
		return Dentist_id;
	}
	public void setDentist_id(long id){
		this.Dentist_id=id;
	}
	public String getLname(){
		return Lname;
	}
	public void setLname(String Lname){
		this.Lname = Lname;
	}
	public String getFname(){
		return Fname;
	}
	public void setFname(String Fname){
		this.Fname=Fname;
	}
	public String getGender(){
		return Gender;
	}
	public void setGender(String Gender){
		this.Gender=Gender;
	}
	
	public String getPhonenumber(){
		return Phonenumber;
	}
	public void setPhonenumber(String Phonenumber){
		this.Phonenumber=Phonenumber;
	}
	
	public String getEmail(){
		return Email;
	}
	public void setEmail(String Email){
		this.Email=Email;
	}
	
	public String getAddress(){
		return Address;
	}
	public void setAddress(String Address){
		this.Address=Address;
	}
	
	public String getAdditionalInformation(){
		return AdditionalInformation;
	}
	public void setAdditionalInformation(String AdditionalInformation){
		this.AdditionalInformation=AdditionalInformation;
	}
	public String getImage(){
		return Image;
	}
	public void setImage(String Image){
		this.Image=Image;
	}
	
}