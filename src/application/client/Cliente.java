package application.client;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Timer;


public class Cliente {
	
	static BufferedReader doUsuario;
	static Socket socketCliente;
	static DataOutputStream paraServidor; 

	static BufferedReader doServidor;
	
	public static void abreConexao() throws UnknownHostException, IOException {
		doUsuario = new BufferedReader(new InputStreamReader(System.in));	
		socketCliente = new Socket("127.0.0.1", 8888);
		paraServidor = new DataOutputStream(socketCliente.getOutputStream());
		doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	}
	
	public static void fechaConexao() throws IOException {
		socketCliente.close();
	}


	public static void main(String argv[]) throws Exception {
		String login = "";
		String senha = "";
		int tipoCliente = 0;
		boolean logado = false;
		int opcao = 9999;
		char retornaMenu = 'F';
		String pesquisa;
		boolean flagLogin = false;
		Scanner sc = new Scanner(System.in);
		
		while(logado == false) {
			
			abreConexao();
			if (flagLogin) {
				System.out.println("\nLogin invalido digitar novamente\n");
			}
			paraServidor.writeBytes("0" + '\n');
			System.out.print("Login: ");
			login = doUsuario.readLine();
			paraServidor.writeBytes(login + '\n');
			System.out.print("\nSenha: ");
			senha = doUsuario.readLine();
			paraServidor.writeBytes(senha + '\n');
			
			logado = Boolean.valueOf(doServidor.readLine());
			tipoCliente = Integer.valueOf(doServidor.readLine());
			
			fechaConexao();	
			flagLogin = true;
			
		}


		while(opcao != 0) {
			menuCliente(tipoCliente, login);
			retornaMenu = '\u0000';
			String tituloCad;

			System.out.print("Digite a opcao: ");
			opcao = sc.nextInt();
			
			switch (opcao){
				case 1:
					//Pesquisa por Titulo
					System.out.print("\n\nDigite o Titulo: ");
					abreConexao();

					paraServidor.writeBytes("1" + '\n');
					
					pesquisa = doUsuario.readLine();
					paraServidor.writeBytes(pesquisa + '\n');
					System.out.print("\n\nResultado da busca:\n");
					splitaString(doServidor.readLine());

					fechaConexao();
					
					break;

				case 2:
					//Pesquisa por Autor
					System.out.print("\n\nDigite o Autor: ");
					abreConexao();

					paraServidor.writeBytes("2" + '\n');
					
					pesquisa = doUsuario.readLine();
					paraServidor.writeBytes(pesquisa + '\n');
					
					System.out.print("\n\nResultado da busca:\n");
					splitaString(doServidor.readLine());
					
					fechaConexao();
					
					
				    break;		
				    
				case 3:
					//Pesquisa por Editora
					System.out.print("\n\nDigite Editora: ");
					abreConexao();

					paraServidor.writeBytes("3" + '\n');
					
					pesquisa = doUsuario.readLine();
					paraServidor.writeBytes(pesquisa + '\n');

					System.out.print("\n\nResultado da busca:\n");
					splitaString(doServidor.readLine());
					
					fechaConexao();
					
					
				    break;		
				
				case 4:
					if(tipoCliente == 2) {
						String loginCad = "";
						boolean senhaIgual = false;
						boolean loginExiste = true;
						boolean flag = false;
						String senhaCad ="";
						String senhaConfirm="";
						
						while(loginExiste) {
							abreConexao();
							paraServidor.writeBytes("4" + '\n');
							if(!flag) {
								System.out.print("\n\nNovo cadastro de Cliente\n");
								paraServidor.writeBytes("login" + '\n');
								flag = true;
							}else {
								System.out.print("\n\nLogin Existente\n\nDigitar novamente\n");
								paraServidor.writeBytes("login" + '\n');
							}
							System.out.println("Nome novo Cliente: ");
							loginCad = doUsuario.readLine();
							paraServidor.writeBytes(loginCad + '\n');
							
							loginExiste = Boolean.valueOf(doServidor.readLine());
							fechaConexao();
							
						}

						
						flag = false;
						while(!senhaIgual) {
							boolean bug = false;
							
							if(flag) {
								System.out.print("As senhas nao sao iguais.");
							}
							
							//Por algum motivo ele não quer fazer a leitura da senha na primeira vez. E na segunda vez que acessa
							//Ele "pede" 2 vezes a senha se não colocar uma flag
							if(bug == false) {
								sc.nextLine(); // 
								bug = true;
							}

							System.out.print("\nDigite a senha:");
							senhaCad = sc.nextLine();
							
							
							System.out.print("Repetir a senha: ");
							senhaConfirm = sc.nextLine();
							
							System.out.println("senha1 " + senhaCad + " senha2 "+ senhaConfirm + "\n");
							if(senhaCad.equals(senhaConfirm)) {
								senhaIgual = true;
							}
							flag = true;
						}
						
						abreConexao();
						paraServidor.writeBytes("4" + '\n');
						paraServidor.writeBytes("%%Okay##" + '\n');
						paraServidor.writeBytes(loginCad + '\n');
						paraServidor.writeBytes(senhaCad + '\n');
						paraServidor.writeBytes("1" + '\n');
						fechaConexao();
						
					}else {
						System.out.println("Opcao invalida");
					}
					break;
					
				case 5:
					if(tipoCliente == 2) {
						boolean tituloExiste = false;
						abreConexao();
						paraServidor.writeBytes("5" + '\n');
						
						System.out.print("\n\nCadastro de novos Livros\nTitulo novo: ");
						tituloCad = doUsuario.readLine();
						paraServidor.writeBytes(tituloCad + '\n');
						tituloExiste = Boolean.valueOf(doServidor.readLine());
						
						if(!tituloExiste) {
							abreConexao();

							paraServidor.writeBytes("5" + '\n');
							paraServidor.writeBytes("%%Okay##" + '\n');
							paraServidor.writeBytes(tituloCad + '\n');
							System.out.print("Autor: ");
							paraServidor.writeBytes(doUsuario.readLine() + '\n');
							System.out.print("Editora: ");
							paraServidor.writeBytes(doUsuario.readLine() + '\n');
							System.out.print("Qtde: ");
							paraServidor.writeBytes(doUsuario.readLine() + '\n');
							
							fechaConexao();
						}else {
							System.out.println("\nTitulo já cadastrado");
						}
						
					}else {
						System.out.println("Opcao invalida");
					}
					
					break;
					
				case 6:
					if(tipoCliente == 2) {
						abreConexao();
						
						paraServidor.writeBytes("6" + '\n');
						System.out.println("\n\nResultado da Busca: ");
						splitaString(doServidor.readLine());
						fechaConexao();
					}else {
						System.out.println("Opcao invalida");
					}
					break;
					
				case 7:
					if(tipoCliente == 2) {
						abreConexao();
						
						paraServidor.writeBytes("7" + '\n');
						System.out.print("\n\nLivro a ser retirado: ");
						tituloCad = doUsuario.readLine();
						paraServidor.writeBytes(tituloCad + '\n');
						
						splitaString(doServidor.readLine());
						
						fechaConexao();
					}else {
						System.out.println("Opcao invalida");
					}
					break;

				case 8:
					if(tipoCliente == 2) {
						abreConexao();
						paraServidor.writeBytes("8" + '\n');
						System.out.print("\n\nLivro a ser devolvido: ");
						tituloCad = doUsuario.readLine();
						paraServidor.writeBytes(tituloCad + '\n');
						
						splitaString(doServidor.readLine());
						
						fechaConexao();
					}else {
						System.out.println("Opcao invalida");
					}
					break;
					
				case 0:
					System.out.println("Fechando aplicação...");
					Thread.sleep(3000);
					System.exit(0);
				default:
					System.out.print("Opcao invalida\nTente Novamente");

			}
			
			System.out.println("Retornar para o Menu (S/N): ");
			retornaMenu = sc.next().charAt(0);
			retornaMenu = Character.toUpperCase(retornaMenu);
			if(retornaMenu == 'S') {
				System.out.println("Retornando ao Menu Inicial.....");
				Thread.sleep(3000);
			}else if(retornaMenu == 'N') {
				System.out.println("Fechando aplicação...");
				Thread.sleep(3000);
				System.exit(0);
			}
		}


	}
	
	public static void menuCliente(int tipoCliente, String nomeCliente) {

		
		System.out.print("\nBiblioteca Nova");
		if (tipoCliente == 2) {
			System.out.print("\nGerente ");
		}
		System.out.print(nomeCliente);
		System.out.println("\nBem vindo ");
		System.out.println("--------");
		System.out.println("Selecionar o tipo de pesquisa");
		System.out.println("1 - Título");
		System.out.println("2 - Autor");
		System.out.println("3 - Editora");

		if(tipoCliente == 2) {
			System.out.println("4 - Cadastrar cliente");
			System.out.println("5 - Cadastrar livro");
			System.out.println("6 - Pesquisar livros em falta");
			System.out.println("7 - Retirar livro");
			System.out.println("8 - Devolver livro");
		}
		
		System.out.println("0 - Para sair");
	}
	
	public static void splitaString(String resposta) {
		String [] respostaSplit = resposta.split("#");
		
		for(int i= 0; i < respostaSplit.length; i++) {
			System.out.println(respostaSplit[i]);
		}
	}
}//end final