package fehidro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fehidro.api.model.Pontuacao;
import fehidro.api.model.SubcriterioAvaliacao;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long>{

	public List<Pontuacao> findAllBySubcriterio(SubcriterioAvaliacao subcriterio );
	
}
