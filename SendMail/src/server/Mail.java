package server;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	private From from;
	private String to;
	private String subject;
	private String content;
	private Properties properties;
	private Session session;
	private MimeMessage message;
	private String result;

	public Mail(From from, String to, String subject, String content){
		
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.properties = new Properties();
		this.session = createSession();
		this.message = new MimeMessage(this.session);
	}
	
	public Session createSession(){
		
		this.properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		this.properties.setProperty("mail.smtp.starttls.enable", "true");
		this.properties.setProperty("mail.smtp.port", "587");
		this.properties.setProperty("mail.smtp.user", this.from.getUsername());
		this.properties.setProperty("mail.smtp.auth", "true");
		
		return Session.getDefaultInstance(properties);
	}
	
	public void createMessage() throws Exception{
		
		this.message.setFrom(new InternetAddress(this.from.getUsername()));
		this.message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
		this.message.setSubject(this.subject);
		this.message.setText(this.content);
	}
	
	public void send(){
		try{
			createMessage();
			
			Transport t = this.session.getTransport("smtp");
			t.connect(this.from.getUsername(), this.from.getPassword());
			t.sendMessage(this.message, this.message.getAllRecipients());
			t.close();
			
			this.result = "Mensaje enviado. Revise su carpeta de spam";
			
		} catch (Exception e) {
			this.result = "Error al enviar el mensaje";
		}
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public MimeMessage getMessage() {
		return message;
	}

	public void setMessage(MimeMessage message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
