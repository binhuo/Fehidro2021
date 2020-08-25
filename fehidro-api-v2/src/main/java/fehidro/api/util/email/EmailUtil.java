package fehidro.api.util.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailUtil {
	
//	private JavaMailSender sender;
//	private JavaMailSenderImpl senderImpl;

//	private Environment env;

//	public EmailUtil(JavaMailSender _sender, Environment _env) {
//		sender = _sender;
//		env = _env;
//		senderImpl = new JavaMailSenderImpl();
//	}

	public void sendMailUserSignUp(String email, String nome, String login, String senha) {

		try {
			
			final String username = "";
	        final String password = "";

	        Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(email)
	            );
	            message.setSubject("Fehidro - Cadastro feito com sucesso");
	            message.setText("Olá, " + nome + "!\n"
	                    + 	"\n\n Você foi cadastrado com sucesso no sistema da Fehidro!"
	                    + 	"\n\n Seus dados para entrar no sistema são:"
	                    +	"\n\t Login: " + login
	                    +	"\n\t Senha: " + senha
	                    +	"\n\n Seja bem vinda(o)!!!");

	            Transport.send(message);
			
//			senderImpl.setHost(env.getProperty("spring.mail.host"));
//			senderImpl.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
//
//			senderImpl.setUsername(env.getProperty("spring.mail.username"));
//			senderImpl.setPassword(env.getProperty("spring.mail.password"));
//
//			Properties props = senderImpl.getJavaMailProperties();
//			props.put("mail.transport.protocol", "smtp");
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.debug", "true");
//
//			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(senderImpl.getUsername(), senderImpl.getPassword());
//				}
//			});

		
//			MimeMessage msg = senderImpl.createMimeMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//
//	        helper.setTo(email);
//	        helper.setSubject("Portal CBH-BS: Cadastro feito com sucesso");
//	        helper.setText("Olá, " + nome + "!\n"
//                    + 	"\n\n Você foi cadastrado com sucesso no sistema da Fehidro!"
//                    + 	"\n\n Seus dados para entrar no sistema são:"
//                    +	"\n\t Login: " + login
//                    +	"\n\t Senha: " + senha
//                    +	"\n\n Seja bem vinda(o)!!!", true);

			//// helper.setFrom("fehidro.adm@gmail.com");

			// senderImpl.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
