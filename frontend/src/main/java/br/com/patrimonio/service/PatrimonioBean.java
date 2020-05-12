package br.com.patrimonio.service;

import br.com.patrimonio.dto.MarcaDTO;
import br.com.patrimonio.dto.PatrimonioDTO;
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
public class PatrimonioBean {

    private static final String URI_REST = "http://localhost:9090/";

    @Getter
    @Setter
    private PatrimonioDTO patrimonioDTO;

    @Getter
    @Setter
    private List<PatrimonioDTO> listPatrimonios;

    @Getter
    @Setter
    private List<MarcaDTO> listMarcas;

    @PostConstruct
    public void listPatrimonios() {
        listPatrimonios = new ArrayList<>();
        WebTarget webTarget = getWebTargetConfigured("patrimonios/");
        String json = webTarget.request().get(String.class);
        Gson gson = new Gson();
        listPatrimonios = gson.fromJson(json, new TypeToken<List<PatrimonioDTO>>() {
        }.getType());
        listMarcas = carregarMarcas();
    }

    public List<MarcaDTO> carregarMarcas() {
        listMarcas = new ArrayList<>();
        WebTarget webTarget = getWebTargetConfigured("marcas/");
        String json = webTarget.request().get(String.class);
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<MarcaDTO>>() {
        }.getType());
    }


    public void newPatrimonio() {
        try {
            patrimonioDTO = new PatrimonioDTO();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro.");
            erro.printStackTrace();
        }
    }

    public void loadUpdateForm(ActionEvent evento) {
        try {
            patrimonioDTO = (PatrimonioDTO) evento.getComponent().getAttributes().get("patrimonioSelecionado");
            listMarcas = carregarMarcas();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro para carregar os dados.");
            erro.printStackTrace();
        }
    }

    public void updatePatrimonio() {
        try {
            Gson gson = new Gson();
            Response response;
            if (patrimonioDTO.getNumTombo() != null) {
                WebTarget webTarget = getWebTargetConfigured("patrimonios/" + patrimonioDTO.getNumTombo());
                Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
                response = ib.put(Entity.entity(gson.toJson(patrimonioDTO), MediaType.APPLICATION_JSON));
            } else {
                WebTarget webTarget = getWebTargetConfigured("patrimonios");
                Invocation.Builder ib = webTarget.request(MediaType.APPLICATION_JSON);
                response = ib.post(Entity.entity(gson.toJson(patrimonioDTO), MediaType.APPLICATION_JSON));
            }
            if (response.getStatus() == 200) {
                listPatrimonios();
                Messages.addGlobalInfo("Patrimônio salvo com sucesso: ");
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o registro");
            erro.printStackTrace();
        }

    }

    public void deletePatrimonio(ActionEvent evento) {
        try {
            patrimonioDTO = (PatrimonioDTO) evento.getComponent().getAttributes().get("patrimonioSelecionado");
            WebTarget webTarget = getWebTargetConfigured("patrimonios/" + patrimonioDTO.getNumTombo());
            webTarget.request().delete();
            listPatrimonios();
            Messages.addGlobalInfo("Registro removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover registro.");
            erro.printStackTrace();
        }
    }

    private WebTarget getWebTargetConfigured(String path) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        return client.target(URI_REST).path(path);
    }
}
