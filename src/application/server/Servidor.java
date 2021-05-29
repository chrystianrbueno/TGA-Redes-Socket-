package application.server;
import java.io.*;
import java.net.*;

import entities.server.CadastraCliente;
import entities.server.Livros;

class Servidor {
	public static void main(String argv[]) throws Exception {
		
		Livros libBanco = new Livros();
		CadastraCliente clienteBanco = new CadastraCliente();
		CadastraCliente clienteLogado;
		String login = "";
		String senha = "";
		int tipo = 0;
		String pesquisa;

		ServerSocket socketRecepcao = new ServerSocket(8888);

		while(true) {
			Socket socketConexao = socketRecepcao.accept();
			BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketConexao.getInputStream()));
			DataOutputStream paraClienteString = new DataOutputStream(socketConexao.getOutputStream());
			
			String opcao = doCliente.readLine();
			switch(opcao) {
				case "0":
						login = doCliente.readLine();
						senha = doCliente.readLine();
						
						if(libBanco.listaLivros().isEmpty()){
							libBanco.addLivros();
							clienteBanco.addCliente();

						}
						
				        clienteLogado = new CadastraCliente(login, senha);
						if(clienteBanco.testLogin(clienteLogado)){
							paraClienteString.writeBytes("true\n");
							paraClienteString.writeBytes(""+clienteLogado.getTipo()+ '\n');
	
						}else {
							paraClienteString.writeBytes("false\n");
							paraClienteString.writeBytes("0\n");
						}
						
						break;
						
				case "1":
						pesquisa = doCliente.readLine();
						paraClienteString.writeBytes(libBanco.pesqTitulo(pesquisa) + '\n');
						break;
							
				case "2":
						pesquisa = doCliente.readLine();
						paraClienteString.writeBytes(libBanco.pesqAutor(pesquisa) + '\n');
						break;
						
				case "3":
						pesquisa = doCliente.readLine();
						paraClienteString.writeBytes(libBanco.pesqEditora(pesquisa) + "\n");
						break;
				
				case "4":
					pesquisa = doCliente.readLine();	
					if(pesquisa.equals("login")) {
						paraClienteString.writeBytes(""+clienteBanco.testaLoginRepetido(doCliente.readLine()) + '\n');
					}else if(pesquisa.equals("%%Okay##")) {
						login = doCliente.readLine();
						senha = doCliente.readLine();
						tipo = Integer.valueOf(doCliente.readLine());
						CadastraCliente addCliente = new CadastraCliente(login, senha, tipo);
						clienteBanco.setCliente(addCliente);
						
					}else {
						paraClienteString.writeBytes(""+clienteBanco.testaLoginRepetido(doCliente.readLine()) + '\n');
					}
					
						
					
					break;
					
				case "5":
					pesquisa = doCliente.readLine();
					paraClienteString.writeBytes(libBanco.livroExistente(pesquisa) + "\n");
					if(pesquisa.equals("%%Okay##")){
						String titulo, editora, autor;
						int qtde;
						titulo = doCliente.readLine();
						autor = doCliente.readLine();
						editora = doCliente.readLine();
						qtde = Integer.valueOf(doCliente.readLine());
						Livros addLivro = new Livros(titulo,autor, editora, qtde);
						libBanco.setLivro(addLivro);
					}
					break;
				case "6":
					paraClienteString.writeBytes((libBanco.pesqLivrosEmFalta() + '\n'));
					break;
					
				case "7":
					pesquisa = doCliente.readLine();
					paraClienteString.writeBytes(libBanco.retiradaLivro(pesquisa) + '\n');
					
					break;
					
				case "8":
					pesquisa = doCliente.readLine();
					paraClienteString.writeBytes(libBanco.devolucaoLivro(pesquisa) + '\n');
					
					break;
					
			}//end switch		
		}//end while	
	}//end funcao
}//end class