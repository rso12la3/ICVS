/**
 * 
 */
package pl.edu.pw.elka.rso;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Message
{
    
    private String sendTo;
    private String subject;
    private String body;

    public Message(String sendTo, String subject, String body)
    {
        this.sendTo = sendTo;
        this.subject = subject;
        this.body = body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body)
    {
        this.body = body;
    }

    /**
     * @return the body
     */
    public String getBody()
    {
        return body;
    }

    /**
     * @param sendTo the sendTo to set
     */
    public void setSendTo(String sendTo)
    {
        this.sendTo = sendTo;
    }
    
    /**
     * @return
     */
    public String getSendTo()
    {
        return sendTo;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * @return the subject
     */
    public String getSubject()
    {
        return subject;
    }
    
}
