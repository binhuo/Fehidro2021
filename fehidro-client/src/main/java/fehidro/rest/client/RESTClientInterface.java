package fehidro.rest.client;

import java.util.List;

public interface RESTClientInterface<T> {
	
    public static String REST_USUARIO_URL = "usuario/";
    public static String REST_CTPG_URL = "usuario/ctpg/";
    public static String REST_SECRETARIA_URL = "usuario/secretaria/";
    public static String REST_DELIBERACAO_URL = "deliberacao/";
    public static String REST_CRITERIO_URL = "criterioAvaliacao/";
    public static String REST_INSTITUICAO_URL = "instituicao/";
    public static String REST_TIPOPROPOSTA_URL = "tipoProposta/";
    public static String REST_PDC_URL = "pdc/";
    public static String REST_SUBPDC_URL = "subpdc/";
    public static String REST_PROPOSTA_URL = "proposta/";
    public static String REST_AVALIACAO_URL = "avaliacao/";
    public static String REST_SUBCRITERIO_URL = "subcriterioAvaliacao/";
    public static String REST_PONTUACAO_URL = "pontuacao/";
    public static String REST_PERFILACESSO_URL = "perfilAcesso/";

    public List<T> findAll();
    public T find(Long id);
    public T create(T obj);
    public T edit(T obj);
    public boolean delete(Long id);        
}

