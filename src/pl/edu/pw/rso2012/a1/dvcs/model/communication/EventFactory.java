package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.AbstractOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullRequestOperation;

public class EventFactory
{
    Map<Class<? extends AbstractOperation>, Class<? extends ApplicationEvent>> operationEventMap;
    public EventFactory()
    {
        operationEventMap = new HashMap<>();
        prepareOperationEventMap();
    }
    private void prepareOperationEventMap()
    {
        operationEventMap.put(PullRequestOperation.class, PullRequestEvent.class);
    }
    public ApplicationEvent getEvent(final AbstractOperation abstractOperation)
    {
        final Class<? extends ApplicationEvent> class1 = operationEventMap.get(abstractOperation);
        try
        {
            final Constructor<? extends ApplicationEvent> constructor = class1.getConstructor(AbstractOperation.class);
            return constructor.newInstance(abstractOperation);
        }
        catch(final NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(final SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(final InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(final IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(final IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(final InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
