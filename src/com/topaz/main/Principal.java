package com.topaz.main;

import static com.topaz.config.IOConfig.*;

import java.io.IOException;
import java.util.ArrayList;

import com.topaz.bean.Servidor;
import com.topaz.service.ServidorService;

public class Principal {
	public static void main(String args[]) throws IOException {
		
		ServidorService service = new ServidorService();
		
		ArrayList<Servidor> servidores = new ArrayList<Servidor>();
		ArrayList<Integer> input = lerArquivo();
		ArrayList<Integer> totalTicks = new ArrayList<Integer>();
		
		Integer tTask = input.get(0);
		Integer uMax = input.get(1);
		Integer i = 2;
		Integer tamanhoInput = input.size();
		Integer tamanhoServidor = 0;
		
		while(servidores != null) {
			if(i < tamanhoInput) {
				Integer numUsuarios = input.get(i);
				System.out.println(service.adicionarUsuarioAoServidor(servidores, numUsuarios, uMax, tTask));
				escreverArquivo(service.exibirUsuariosServidores(servidores)); 
				service.atualizarTicksServidor(servidores);
				service.lancarTTask(servidores, tTask);
				Integer val = service.removerServidor(servidores);
				
				if(val > 0) {
					totalTicks.add(val);
				}
				
				if(tamanhoServidor <= servidores.size()) {
					tamanhoServidor = servidores.size();
				}
				
				i++;
				
			} else {
				
				escreverArquivo(service.exibirUsuariosServidores(servidores));
				service.atualizarTicksServidor(servidores);
				service.lancarTTask(servidores, tTask);
				Integer val = service.removerServidor(servidores);
				
				if(val > 0) {
					totalTicks.add(val);
				}
				
				service.exibirNumeroServidores(servidores);
				
				if(tamanhoServidor <= servidores.size()) {
					tamanhoServidor = servidores.size();
				}
				
				if(servidores.size() == 0) {
					servidores = null;
				}
			}
			
		}
		
		System.out.println("Número de servidores utilizados: " + tamanhoServidor);
		System.out.println("Valor final: R$" + service.calcularValor(totalTicks) + ",00");
		escreverArquivo(String.valueOf(service.calcularValor(totalTicks)));
		
	}
}
