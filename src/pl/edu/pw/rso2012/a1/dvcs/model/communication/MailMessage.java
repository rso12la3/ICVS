package pl.edu.pw.rso2012.a1.dvcs.model.communication;

/**
 * @author Grzegorz Sancewicz
 * 
 */
public class MailMessage
{
    
    private String sendTo;
    private String subject;
    private String body;

    public MailMessage(String sendTo, String subject, String body)
    {
        this.sendTo = sendTo;
        this.subject = subject;
        this.body = body;
    }

    public MailMessage()
    {
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }

    public void setSendTo(String sendTo)
    {
        this.sendTo = sendTo;
    }
    
    public String getSendTo()
    {
        return sendTo;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }
    
}
