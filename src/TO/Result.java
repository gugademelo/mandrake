package TO;

public class Result {
	
	private String texto;
	private String cabecalho;
	
	
	public Result() {}
	
	public Result(String cabecalho,String texto) {
		this.texto = texto;
	}
		

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getcabecalho() {
		return cabecalho;
	}

	public void setcabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

}
