package fehidro.api.util.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import fehidro.api.util.email.dto.EmailDTO;
import fehidro.model.dto.usuario.CadastroUsuarioDTO;

@Service
public class EmailService {
	@Autowired
	private MailSender mailSender;
	@Value("${spring.mail.username}")
	private String sender;
	
	public void sendMailUserSignUp(CadastroUsuarioDTO user, String decodedPassword) {
		EmailDTO model = new EmailDTO();
		
		model.setTo(user.getEmail());
		model.setSubject("Portal CBH-BS - Cadastro feito com sucesso");
		model.setText("Olá, " + user.getNome() + "!\n"
                + 	"\n Você foi cadastrado com sucesso no Portal CBH-BS!"
                + 	"\n\n Seus dados para entrar no sistema são:"
                +	"\n\t Login: " + user.getLogin()
                +	"\n\t Senha: " + decodedPassword
                +	"\n\n Seja bem vindo(a)! :D");
		
		sendEmail(model);
	}

	public void sendEmail(EmailDTO email) {
		SimpleMailMessage message = prepareSimpleMailMessage(email);
		mailSender.send(message);
	}

	private SimpleMailMessage prepareSimpleMailMessage(EmailDTO email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getTo());
		mailMessage.setFrom(sender);
		mailMessage.setSubject(email.getSubject());
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText(email.getText());
		return mailMessage;
	}

}
