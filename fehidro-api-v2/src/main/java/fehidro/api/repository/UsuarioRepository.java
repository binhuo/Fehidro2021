package fehidro.api.repository;

import fehidro.api.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

	Optional<Usuario> findByCPF(String cpf);

	@Query(" select u from Usuario u where u.perfilAcesso = (select p from PerfilAcesso p where p.id = ?1) ")
	List<Usuario> findAllByPerfilAcesso(Long perfilacesso);

}
