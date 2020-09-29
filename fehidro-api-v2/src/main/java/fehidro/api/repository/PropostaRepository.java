package fehidro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import fehidro.api.model.PerfilAcesso;
import fehidro.api.model.Proposta;
import fehidro.api.model.Usuario;

@Repository
public interface PropostaRepository  extends JpaRepository<Proposta, Long> {
	
	//TODO: considerar renomear esses endpoints para evitar confusão e remover instituição do parametro e pegar pela query
	
	//Pega as avaliacoes em aberto para a CTPG
	@Query("select p from Proposta p where (p.instituicao != (select i from Instituicao i where i.id =?2) and p.id not in (select proposta from Avaliacao a where (a.avaliador =?1 and a.subcriterio in (select id from SubcriterioAvaliacao s where s.perfilAcesso = (select perfilAcesso from Usuario u where u.id =?1))))) and (p.id not in (select p from Proposta p where p.id not in (select proposta from Avaliacao a where a.subcriterio in (select id from SubcriterioAvaliacao s where s.perfilAcesso = (select perfilAcesso from Usuario u where u.id =1)))))")
	public List<Proposta> findEmAberto(Usuario usuario, long instituicao);
	
	//Pega as avaliacoes em aberto para a Secretaria Executiva
	@Query("select p from Proposta p where p.id not in (select proposta from Avaliacao a where a.avaliador =?1 and a.subcriterio in (select id from SubcriterioAvaliacao s where s.perfilAcesso = (select perfilAcesso from Usuario u where u.id =?1)))")
	public List<Proposta> findEmAberto(Usuario usuario);
	
}
