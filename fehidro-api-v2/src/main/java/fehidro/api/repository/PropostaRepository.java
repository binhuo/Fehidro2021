package fehidro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fehidro.api.model.PerfilAcesso;
import fehidro.api.model.Proposta;

@Repository
public interface PropostaRepository  extends JpaRepository<Proposta, Long> {
	
	@Query("select p from Proposta p where p.id not in (select proposta from Avaliacao a where a.subcriterio in (select id from SubcriterioAvaliacao s where s.perfilAcesso = ?1))")
	public List<Proposta> findEmAberto(PerfilAcesso perfilAcesso);
	
	
}
