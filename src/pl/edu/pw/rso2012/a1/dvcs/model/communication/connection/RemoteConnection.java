/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.model.communication.connection;

import java.util.LinkedList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pl.edu.pw.rso2012.a1.dvcs.model.communication.MailMessage;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.Configuration;
import pl.edu.pw.rso2012.a1.dvcs.model.configuration.RepositoryConfiguration;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class RemoteConnection
{
    private Properties props;
    private RepositoryConfiguration repositoryConfiguration;
    public RemoteConnection()
    {
        props = new Properties();
        repositoryConfiguration = Configuration.getInstance().getRepositoryConfiguration();
        prepareProperties();
    }
    /**
     * 
     */
    private void prepareProperties()
    {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }
    
    public void sendMail(LinkedList<MailMessage> messagesToSend) throws MessagingException
    {
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(repositoryConfiguration.getRepositoryAddress(), 
                        repositoryConfiguration.getRepositoryPass());
                }
            });
 
        try 
        {
            for (MailMessage message : messagesToSend)
            {
                Message mail = new MimeMessage(session);
                mail.setFrom(new InternetAddress(repositoryConfiguration.getRepositoryAddress()));
                mail.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(message.getSendTo()));
                mail.setSubject(message.getSubject());
                mail.setText(message.getBody());
                Transport.send(mail);
            }
        } 
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
