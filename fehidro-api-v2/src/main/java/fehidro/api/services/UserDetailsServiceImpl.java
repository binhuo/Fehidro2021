package fehidro.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fehidro.api.model.Usuario;
import fehidro.api.repository.UsuarioRepository;
import fehidro.api.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository _usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUser = _usuarioRepository.findByLogin(username);
		if (!optUser.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		Usuario user = optUser.get();
		return new UserDetailsImpl(user);
	}
}
