package com.topaz.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IOConfig {
	
	public static final String INPUT = "input.txt";
	public static final String OUTPUT = "output.txt";
	
	public static ArrayList<Integer> lerArquivo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT)));
		
		ArrayList<String> aux = new ArrayList<String>();
		
		String s = br.readLine();
		
		while(s != null) {
			aux.add(s);
			s = br.readLine();
		}
		
		br.close();
		
		ArrayList<Integer> input = new ArrayList<Integer>();
		int i = 0;
		
		while(i < aux.size()) {
			input.add(Integer.valueOf(aux.get(i)));
			i++;
		}
		
		return input;
	}
	
	public static void escreverArquivo(String dado) throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT, true)));
		pw.append(dado + "\n");
		pw.close();
	}
}
