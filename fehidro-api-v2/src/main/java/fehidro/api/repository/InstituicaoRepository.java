package fehidro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fehidro.api.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
