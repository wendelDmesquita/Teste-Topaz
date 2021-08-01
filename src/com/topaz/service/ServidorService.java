package com.topaz.service;

import java.util.ArrayList;

import com.topaz.bean.Servidor;
import com.topaz.bean.Usuario;

public class ServidorService {

	private UsuarioService service = new UsuarioService();
	String saida[] = new String[1];

	public Servidor criarServidor(Integer tTask) {
		Servidor s = new Servidor();
		s.settTask(tTask);
		s.setUsuarios(new ArrayList<Usuario>());
		s.setTicks(0);

		return s;
	}

	public String adicionarUsuarioAoServidor(ArrayList<Servidor> servidores, Integer numUsuarios, Integer uMax,
			Integer tTask) {
		if(numUsuarios == 0) {
			return "nada acontece, feijoada :)";
		}
		
		if (servidores.size() == 0) {
			servidores.add(criarServidor(tTask));
		}
		
		for (int i = 0; i < servidores.size(); i++) {
			if (servidores.get(i).getNumUsuarios() == 0) {
				if (numUsuarios <= uMax) {
					int j = 0;
					while (j < numUsuarios) {
						servidores.get(i).getUsuarios().add(service.criarUsuario());
						j++;
					}
					return "ok 1";
					
				} else if (numUsuarios > uMax) {
					Integer numUsuariosRestantes = numUsuarios;
					int j = 0;
					while (numUsuariosRestantes > 0) {
						while (j < uMax) {
							servidores.get(i).getUsuarios().add(service.criarUsuario());
							numUsuariosRestantes -= 1;
							j++;
							if (servidores.get(i).getNumUsuarios() == uMax && numUsuariosRestantes > 0) {
								servidores.add(criarServidor(tTask));
								i += 1;
								j = 0;
							}
							
							if (numUsuariosRestantes <= 0) {
								return "ok 2";
							}
						}

					}

				}

			} else if (servidores.get(servidores.size() - 1).getNumUsuarios() == uMax) {
				servidores.add(criarServidor(tTask));
				i = servidores.size() - 1;
				if (numUsuarios <= uMax) {
					int j = 0;
					while (j < numUsuarios) {
						servidores.get(i).getUsuarios().add(service.criarUsuario());
						j++;
					}
					
					return "ok 3";
					
				} else if (numUsuarios > uMax) {
					Integer numUsuariosRestantes = numUsuarios;
					int j = 0;
					while (numUsuariosRestantes > 0) {
						while (j < uMax) {
							servidores.get(i).getUsuarios().add(service.criarUsuario());
							numUsuariosRestantes -= 1;
							j++;
							if (servidores.get(i).getNumUsuarios() == uMax && numUsuariosRestantes > 0) {
								servidores.add(criarServidor(tTask));
								i += 1;
								j = 0;
							}
							
							if (numUsuariosRestantes <= 0) {
								return "ok 4";
							}
						}

					}

				}
				
			} else if (servidores.get(servidores.size() - 1).getNumUsuarios() < uMax) {
				Integer numUsuariosRestantes = numUsuarios;
				Integer pos = servidores.size() - 1;
				while (servidores.get(pos).getNumUsuarios() < uMax) {
					servidores.get(pos).getUsuarios().add(service.criarUsuario());
					numUsuariosRestantes -= 1;
					if(numUsuariosRestantes == 0) {
						return "ok 5";
					}
				}
				
				servidores.add(criarServidor(tTask));
				i = servidores.size() - 1;
				if (numUsuariosRestantes <= uMax) {
					int j = 0;
					while (j <= numUsuariosRestantes && numUsuariosRestantes > 0) {
						servidores.get(i).getUsuarios().add(service.criarUsuario());
						numUsuariosRestantes -= 1;
						j++;
					}
					if(numUsuariosRestantes == 0) {
						return "ok 6";
					}
					
				} else if (numUsuariosRestantes > uMax) {
					int j = 0;
					while (numUsuariosRestantes > 0) {
						while (j < uMax) {
							servidores.get(i).getUsuarios().add(service.criarUsuario());
							numUsuariosRestantes -= 1;
							j++;
							if (servidores.get(i).getNumUsuarios() == uMax && numUsuariosRestantes > 0) {
								servidores.add(criarServidor(tTask));
								i += 1;
								j = 0;
							} else {
								return "ok 7";
							}

						}

					}

				}
			} 

		}
		
		return "algo deu mal";
	}

	public String exibirUsuariosServidores(ArrayList<Servidor> servidores) {
		saida[0] =  "";
		for (Servidor s : servidores) {
			saida[0] = saida[0] + s.toStringNumUsuarios();
		}
		String output = saida[0].substring(0, saida[0].length() - 2);
		System.out.println(output);
		return output;
	}

	public void exibirNumeroServidores(ArrayList<Servidor> servidores) {
		System.out.println("Número de servidores: " + servidores.size());
	}
	
	public void atualizarTicksServidor(ArrayList<Servidor> servidores) {
		for(Servidor s : servidores) {
			if(s.getUsuarios().size() > 0) {
				s.setTicks(s.getTicks() + 1);
				service.atualizarTicksUsuarios(s.getUsuarios());
			}
		}
	}
	
	public void lancarTTask(ArrayList<Servidor> servidores, Integer tTask) {
		for(Servidor s : servidores) {
			service.liberarUsuario(s.getUsuarios(), tTask);
		}
	}
	
	public Integer removerServidor(ArrayList<Servidor> servidores) {
		for(int i = 0; i < servidores.size(); i++) {
			if(servidores.get(i).getNumUsuarios() == 0) {
				Integer totalTicks = servidores.get(i).getTicks();
				servidores.remove(i);
				return totalTicks;
			}
		}
		return 0;
	}
	
	public void balancearCarga(ArrayList<Servidor> servidores, Integer uMax) {
		if(servidores.size() > 1) {
			for(int i = 0; i < servidores.size(); i++) {
			int pos = servidores.size() - 1;
			if(servidores.get(i).getNumUsuarios() < uMax && servidores.get(pos).getNumUsuarios() < uMax && servidores.get(pos) != null) {
				int j = 0;
				while(servidores.get(pos).getUsuarios().size() == 0) {
					servidores.get(i).getUsuarios().add(servidores.get(pos).getUsuarios().get(j));
					servidores.get(pos).getUsuarios().remove(j);
					j++;
				}
				break;
			}
		}
		}
		
	}
	
	public Integer calcularValor(ArrayList<Integer> totalTicks) {
		Integer valorFinal = 0;
		for(Integer val : totalTicks) {
			valorFinal += val;
		}
		return valorFinal;
	}
}
