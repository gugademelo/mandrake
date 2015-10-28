package managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.UsuarioDAO;
import TO.Usuario;



@ManagedBean(name= "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String doLogin() {
		
		
		System.out.println(usuario.getNome());
		usuario = new UsuarioDAO().login(usuario.getNome(), usuario.getSenha());
		
		if (usuario == null) {
			usuario = new Usuario();
			return "erro";
		}
		
			
		//}

		return "index";
	}
	
	public String logoff(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();    
        HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);    
        session.invalidate();
        
        return "index";
	}
	
	public String insereUsuario(){
		
		UsuarioDAO dao = new UsuarioDAO();
		
		System.out.println(usuario.getId());
		
		if (! dao.salva(usuario)) {
			return "erro";
		}
		
		
		return "resultado";
		
	}

	
	
}
