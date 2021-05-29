package entities.server;
import java.util.ArrayList;

public class CadastraCliente {
	private String login;
	private String senha;
	private int tipo =0;
	ArrayList<CadastraCliente> bancoCliente;
	
	public CadastraCliente(String login, String senha, int tipo) {
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
	}
	
	public CadastraCliente() {
		
	}
	
	//Nesse não exige tipo para o construtor pois é apenas utilizado para testar se ele está cadastrado no sistema
	public CadastraCliente(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public void addCliente() {
		CadastraCliente cliente1 = new CadastraCliente("clientecomum", "senha123", 1);
		CadastraCliente cliente2 = new CadastraCliente("gerente", "senha123", 2);
		bancoCliente = new ArrayList<CadastraCliente>();
		bancoCliente.add(cliente1);
		bancoCliente.add(cliente2);
	}
	
	public void setCliente(CadastraCliente addCliente) {
		bancoCliente.add(addCliente);
	}
	
	
	public int getTipo() {
		return tipo;
	}
	

	public boolean testLogin(CadastraCliente clienteLogado) {
			for(int i = 0; i < bancoCliente.size(); i++) {
				if(bancoCliente.get(i).login.equals(clienteLogado.login) 
						&& bancoCliente.get(i).senha.equals(clienteLogado.senha)) {
					
					clienteLogado.tipo = bancoCliente.get(i).tipo;
					return true;
				}
			}
			
		return false;
	}
	
	public boolean testaLoginRepetido(String testaLogin) {
		for(int i = 0; i < bancoCliente.size();i++) {
			if(bancoCliente.get(i).login.equals(testaLogin)) {
				return true;
			}
		}

		return false;

	}

}
