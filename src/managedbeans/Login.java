package managedbeans;

import javax.faces.bean.*;

@ManagedBean
public class Login {
	
	private String email;
	private String pass;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String doLogin() {
		setEmail("teste");
		setPass("teste2");
		return "index";
	}
	
}
