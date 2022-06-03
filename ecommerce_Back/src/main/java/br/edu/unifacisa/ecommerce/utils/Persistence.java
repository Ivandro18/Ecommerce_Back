package br.edu.unifacisa.ecommerce.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Persistence {
	
private static ObjectInputStream input;
	
	public static Object openData() {
		//abre o arquivo
		if (Files.exists(Paths.get("Save.txt"))){
			try {
				input = new ObjectInputStream(Files.newInputStream(Paths.get("Save.txt")));
				}			
			
			catch (IOException ioException) {
				System.err.println("Erro ao abrir araquivo");
				System.exit(1);
				}
			
			//Ler o arquivo
			try {
				Object object;
				return object =  input.readObject();
			}
			
			catch(EOFException endOfFileException){
				System.out.printf("%No more records%n");
				
			}
			
			catch(ClassNotFoundException classNotFoundException) {
				System.err.println("Tipo do objeto errado");
			}
			
			catch (IOException ioExcepcion) {
				System.err.println("Erro ao ler arquivo");
				Object object;
				return object = new Object();
			}
			
			//fecha o arquivo
			try {
				if (input != null) 
					input.close();
			}
			catch(IOException ioExcepcion) {
				System.err.println("Erro ao fechar arquivo");
			}
			
			
		}
		else {
			System.out.println("Arquivo não existe");
			Object object;
			return object = new Object();
		}
		Object object;
		return object = new Object();
	}
	
	private static ObjectOutputStream output;
	
	//salva os dados em arquivo sereliazado
	public static void saveData(Object object) {
		//abre o arquivo
		try {
			output = new ObjectOutputStream(Files.newOutputStream(Paths.get("Save.txt")));
		}
		catch (IOException ioException) {
			System.err.println("Erro ao abrir araquivo");
			System.exit(1);
		}
		try {
			//serializa o objeto em um arquivo
			output.writeObject(object);
		}
		catch (IOException ioExcepcion) {
			System.err.println("Erro ao gravar arquivo");
		}
		
		//fecha o arquivo
		try {
			if (output != null) 
				output.close();
		}
		catch(IOException ioExcepcion) {
			System.err.println("Erro ao fechar arquivo");
		}
	}
}
