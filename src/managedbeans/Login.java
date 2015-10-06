package managedbeans;

import javax.faces.bean.*;

@ManagedBean
public class Login {
	
	public String doLogin() {
		return "index";
	}
}
