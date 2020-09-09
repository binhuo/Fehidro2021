package fehidro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fehidro.api.model.Proposta;
import fehidro.api.model.Usuario;

@Repository
public interface PropostaRepository  extends JpaRepository<Proposta, Long> {
	
//	@Query(0/0)
//	public List<Proposta> findEmAberto(Usuario usuario); 
}
