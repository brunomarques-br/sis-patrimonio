package br.com.patrimonio.service;

import br.com.patrimonio.dto.Objeto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@ManagedBean
public class ConsumirBean {
	
		private int dia;
		private int mes;
		private int ano;
		private String descricao;
		private double valor;
		private boolean pg;
		
		public List<Objeto> getPegaDados(){
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8080/lancamento/lst");
			 String json = target.request().get(String.class);
			 Gson gson = new Gson();
			 return gson.fromJson(json, new TypeToken<List<Objeto>>(){}.getType());
		}
		
		public Response enviaDados(){
			 Objeto obj = new Objeto();
			 obj.setDia(dia);
			 obj.setMes(mes);
			 obj.setAno(ano);
			 obj.setDescricao(descricao);
			 obj.setValor(valor);
			 obj.setPg(pg);
			
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8080/lancamento");
			 Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
			 Response response = ib.post(Entity.entity(obj, MediaType.APPLICATION_JSON));

			 return client.target("http://localhost:8080/lancamento")
					 .request(MediaType.APPLICATION_JSON).post(Entity.entity(obj, MediaType.APPLICATION_JSON));
			
		}

		public void apagaDados(long id){
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8080/delete/"+id);
			 target.request().delete();

		}
		

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public int getMes() {
			return mes;
		}

		public void setMes(int mes) {
			this.mes = mes;
		}

		public int getAno() {
			return ano;
		}

		public void setAno(int ano) {
			this.ano = ano;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public boolean isPg() {
			return pg;
		}

		public void setPg(boolean pg) {
			this.pg = pg;
		}
		
}
