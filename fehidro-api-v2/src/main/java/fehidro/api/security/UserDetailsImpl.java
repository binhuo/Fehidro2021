package fehidro.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fehidro.api.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl() {
	
	}
	
	public UserDetailsImpl(Usuario user) {
		super();
		if (user != null) {
			this.id = user.getId();
			this.login = user.getLogin();
			this.senha = user.getSenha();
			//new SimpleGrantedAuthority(user.getPerfilAcesso().getNome());
			//this.authorities = new HashSet<PerfilAcesso>();
		}
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
