/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.schedule;

import java.util.TimerTask;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class ScheduledEvent extends TimerTask
{
    private final ApplicationEvent event;
    private final Controller controller;
    public ScheduledEvent(final Controller controller, final ApplicationEvent event)
    {
        this.controller = controller;
        this.event = event;
        
    }
    /* (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run()
    {
        try
        {
            controller.getEventQueue().put(event);
        }
        catch(final InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
