package managedbeans;

import javax.faces.bean.*;

import TO.Usuario;
import DAO.UsuarioDAO;

@ManagedBean
@SessionScoped
public class UserSession {
	
	private String email;
	private String pass;
	private Usuario usuario;
	private boolean active;
	
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
		
		this.usuario = new UsuarioDAO().login(email, pass);
		
		//if (usuario) {
			
		//}
		setEmail("teste");
		setPass("teste2");
		return "index";
	}
	
}
