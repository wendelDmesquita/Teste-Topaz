package com.topaz.service;

import java.util.ArrayList;

import com.topaz.bean.Usuario;

public class UsuarioService {
	public Usuario criarUsuario() {
		return new Usuario(0);
	}
	
	public void atualizarTicksUsuarios(ArrayList<Usuario> usuarios) {
		 for(Usuario u : usuarios) {
			 u.setNumTicks(u.getNumTicks() + 1);
		 }
	}
	
	public void liberarUsuario(ArrayList<Usuario> usuarios, Integer tTask) {
		int tamanho = usuarios.size();
		for(int i = 0; i < tamanho; i++) {
			if(usuarios.get(i).getNumTicks() == tTask) {
				usuarios.remove(i);
				tamanho = usuarios.size();
				i = -1;
			}
		}
	}
}
