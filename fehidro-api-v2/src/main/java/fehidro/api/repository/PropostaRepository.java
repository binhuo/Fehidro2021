package fehidro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fehidro.api.model.Proposta;

@Repository
public interface PropostaRepository  extends JpaRepository<Proposta, Long> {
	
//	@Query(0/0)
//	public List<Proposta> findEmAberto(Usuario usuario); 
}
