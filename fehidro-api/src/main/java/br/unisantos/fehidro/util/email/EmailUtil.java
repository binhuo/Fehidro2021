package br.unisantos.fehidro.util.email;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.unisantos.fehidro.model.Usuario;

import java.util.Properties;


public class EmailUtil {
	public static void sendMail(String email, String nome, String login, String senha) {
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

        try {

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

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}