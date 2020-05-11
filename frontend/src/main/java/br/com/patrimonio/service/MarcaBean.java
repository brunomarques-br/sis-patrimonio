package br.com.patrimonio.service;

import br.com.patrimonio.dto.MarcaDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ManagedBean
public class MarcaBean {

    @Getter
    @Setter
    private Integer idMarca;

    @Getter
    @Setter
    private String nome;

    public List<MarcaDTO> getListMarcas() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9090/marcas/");
        String json = webTarget.request().get(String.class);
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<MarcaDTO>>() {
        }.getType());
    }

    public Response insertMarca() {
        MarcaDTO marcaDTO = new MarcaDTO(this.idMarca, this.nome);
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9090/marcas");
        Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
        return client.target("http://localhost:8080/lancamento")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(marcaDTO, MediaType.APPLICATION_JSON));
    }

    public Response updateMarca(Integer id) {
        MarcaDTO marcaDTO = new MarcaDTO(this.idMarca, this.nome);
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9090/marcas/" + id);
        Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
        return client.target("http://localhost:8080/lancamento")
                .request(MediaType.APPLICATION_JSON).put(Entity.entity(marcaDTO, MediaType.APPLICATION_JSON));
    }

    public void deleteMarca(Integer id) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9090/marcas/" + id);
        webTarget.request().delete();
    }
}
