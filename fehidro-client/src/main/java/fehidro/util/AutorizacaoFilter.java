package fehidro.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fehidro.model.Usuario;
import fehidro.model.enums.CodigoPerfilAcessoEnum;

@WebFilter(urlPatterns = { "/*" }, dispatcherTypes = { DispatcherType.REQUEST })
public class AutorizacaoFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		HttpSession sessao = httpRequest.getSession();
		Usuario user = (Usuario) sessao.getAttribute("usuarioLogado");
		String uri = httpRequest.getRequestURI();

		if (!uri.contains("/login/index.xhtml") && !uri.contains("resource")) {
			if (user != null) {
				aplicarRegraAcesso(request, response, chain, httpRequest, user, uri);
			}

			// chain.doFilter(request, response);
			else {
				sessao.setAttribute("message", "Faça o login");
				String contextPath = httpRequest.getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/login/index.xhtml");
			}
		} else {
			chain.doFilter(request, response);
		}

		// chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	public void aplicarRegraAcesso(ServletRequest request, ServletResponse response, FilterChain chain,
			HttpServletRequest httpRequest, Usuario user, String uri) throws IOException, ServletException {
		if (!uri.contains("forbidden")) {

			List<String> acessoSecretariaExec = new ArrayList<String>();
			acessoSecretariaExec.add("/deliberacao/");
			acessoSecretariaExec.add("/instituicao/");
			acessoSecretariaExec.add("/pdc/");
			acessoSecretariaExec.add("/criterioAvaliacao/");
			acessoSecretariaExec.add("/tipoProposta/");
			acessoSecretariaExec.add("/proposta/");
			acessoSecretariaExec.add("/avaliacao/");
			acessoSecretariaExec.add("/relatorio/");
			acessoSecretariaExec.add("/usuario/");

			List<String> acessoAvaliadorCtpg = new ArrayList<String>();
			acessoAvaliadorCtpg.add("/proposta/");
			acessoAvaliadorCtpg.add("/avaliacao/");

			if (user.getPerfilAcesso() == CodigoPerfilAcessoEnum.SecretariaExecutiva.getCodigo()
					&& !acessoSecretariaExec.stream().anyMatch(uri::contains)) {
				String contextPath = httpRequest.getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/forbidden.xhtml");
			} else if (user.getPerfilAcesso() == CodigoPerfilAcessoEnum.AvaliadorCtpg.getCodigo()
					&& !acessoAvaliadorCtpg.stream().anyMatch(uri::contains)) {
				String contextPath = httpRequest.getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/forbidden.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
