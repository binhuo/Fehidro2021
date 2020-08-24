package fehidro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fehidro.api.model.Proposta;

public interface PropostaRepository  extends JpaRepository<Proposta, Long> {

}
