package fehidro.api.repository;

import fehidro.api.model.Avaliacao;
import fehidro.api.model.CriterioAvaliacao;
import fehidro.api.model.Proposta;
import fehidro.api.model.SubPDC;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
	
	public List<Avaliacao> findAllByAvaliador(Usuario avaliador);
	public List<Avaliacao> findAllByCriterio(CriterioAvaliacao criterio);
	public List<Avaliacao> findAllBySubcriterio(SubcriterioAvaliacao subcriterio);
	public List<Avaliacao> findAllByProposta(Proposta proposta);
	
	@Query("select a from Avaliacao a where a.proposta = ?1 and a.avaliador = ?2")
	public List<Avaliacao> findAllByAvaliadorProposta(Proposta proposta,Usuario avaliador);
	
	@Query("select a from Avaliacao a where a.proposta = ?1 and a.criterio = ?2")
	public List<Avaliacao> findAllByCriterioProposta(Proposta proposta,CriterioAvaliacao criterio);
	
	@Query("select a from Avaliacao a where a.proposta = ?1 and a.subcriterio = ?2")
	public List<Avaliacao> findAllBySubcriterioProposta(Proposta proposta,SubcriterioAvaliacao subcriterio);
	
	//@Query("select a from Avaliacao a where a.proposta = ( select p from Proposta p where p.subPDC = ?1 )  ")
	@Query("select a from Avaliacao a inner join Proposta p on a.proposta = p.id where p.id = (select id from SubPDC s where s =?1)"  )
	public List<Avaliacao> findAllBySubPdc(SubPDC subpdc);
}
