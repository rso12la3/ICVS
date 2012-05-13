/**
 * 
 */
package pl.edu.pw.rso2012.a1.dvcs.controller.schedule;

import java.util.Timer;

import pl.edu.pw.rso2012.a1.dvcs.controller.Controller;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CheckPullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.CheckPullResponseEvent;

/**
 * @author Grzegorz Sancewicz
 * @email g.sancewicz@stud.elka.pw.edu.pl
 * 
 */
public class Schedule
{
    private final Timer timer;
    private final Controller controller;
    
    public Schedule(final Controller controller)
    {
        this.controller = controller;
        timer = new Timer();
        prepareSchedule();
    }
    private void prepareSchedule()
    {
        timer.scheduleAtFixedRate(new ScheduledEvent(controller, new CheckPullRequestEvent()), 0, 10000);
        timer.scheduleAtFixedRate(new ScheduledEvent(controller, new CheckPullResponseEvent()), 5000, 10000);
    }
    public void kill()
    {
        timer.cancel();
    }
}
