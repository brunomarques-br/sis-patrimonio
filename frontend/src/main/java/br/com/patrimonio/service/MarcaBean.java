package br.com.patrimonio.service;

import br.com.patrimonio.dto.MarcaDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.glassfish.jersey.client.ClientConfig;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class MarcaBean {

    private static final String URI_REST = "http://localhost:9090/";

    @Getter
    @Setter
    private MarcaDTO marca;

    @Getter
    @Setter
    private List<MarcaDTO> listMarcas;

    @PostConstruct
    public void listMarcas() {
        listMarcas = new ArrayList<>();
        WebTarget webTarget = getWebTargetConfigured("marcas/");
        String json = webTarget.request().get(String.class);
        Gson gson = new Gson();
        listMarcas = gson.fromJson(json, new TypeToken<List<MarcaDTO>>() {
        }.getType());
    }


    public void newMarca() {
        try {
            marca = new MarcaDTO();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro.");
            erro.printStackTrace();
        }
    }

    public void loadUpdateForm(ActionEvent evento) {
        try {
            marca = (MarcaDTO) evento.getComponent().getAttributes().get("marcaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro para carregar os dados.");
            erro.printStackTrace();
        }
    }

    public void updateMarca() {
        try {
            Gson gson = new Gson();
            Response response;
            if (marca.getIdMarca() != null) {
                WebTarget webTarget = getWebTargetConfigured("marcas/" + marca.getIdMarca());
                Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
                response = ib.put(Entity.entity(gson.toJson(marca), MediaType.APPLICATION_JSON));
            } else {
                WebTarget webTarget = getWebTargetConfigured("marcas");
                Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
                response = ib.post(Entity.entity(gson.toJson(marca), MediaType.APPLICATION_JSON));
                if(response.getStatus() == 400)
                    Messages.addFlashGlobalError("Já existe uma marca cadastrada com este nome!");
            }
            if(response.getStatus() == 200) {
                Messages.addGlobalInfo("Marca salva com sucesso: ");
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova marca");
            erro.printStackTrace();
        }

    }

    public void deleteMarca(ActionEvent evento) {
        try {
            Response response;
            marca = (MarcaDTO) evento.getComponent().getAttributes().get("marcaSelecionada");
            WebTarget webTarget = getWebTargetConfigured("marcas/" + marca.getIdMarca());
            response = webTarget.request().delete();
            if(response.getStatus() == 500){
                Messages.addFlashGlobalError("Esta marca possui patrimônios vinculados, exclua-os primeiro.");
            }else {
                Messages.addGlobalInfo("Marca removida com sucesso: " +response.getStatus() );
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir.");
            erro.printStackTrace();
        }
    }

    private WebTarget getWebTargetConfigured(String path) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(URI_REST).path(path);
    }
}
