package com.topaz.bean;

import java.util.ArrayList;

public class Servidor {
	private Integer tTask;
	private Integer ticks;
	private ArrayList<Usuario> usuarios;
	
	
	public Integer gettTask() {
		return tTask;
	}
	public void settTask(Integer tTask) {
		this.tTask = tTask;
	}
	public Integer getTicks() {
		return ticks;
	}
	public void setTicks(Integer ticks) {
		this.ticks = ticks;
	}
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Integer getNumUsuarios() {
		return usuarios.size();
	}
	
	public String toStringNumUsuarios() {
		return String.valueOf(usuarios.size()) + ", ";
	}
	
}
