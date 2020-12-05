package fehidro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fehidro.api.model.Pontuacao;
import fehidro.api.model.SubcriterioAvaliacao;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long>{
	
	//@Query(" select p from Pontuacao p order by p.pontos")
	@Query(" select p from Pontuacao p where subcriterio = ?1 order by p.pontos ")
	public List<Pontuacao> findAllBySubcriterio(SubcriterioAvaliacao subcriterio );
	
}
