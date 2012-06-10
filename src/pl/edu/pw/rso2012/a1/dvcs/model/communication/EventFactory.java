package pl.edu.pw.rso2012.a1.dvcs.model.communication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.rso2012.a1.dvcs.controller.event.ApplicationEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.CloneMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PullMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.request.PushMailRequestEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.CloneResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PullResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.controller.event.operation.response.PushResponseEvent;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.AbstractOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.CloneRequestOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PullRequestOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushRequestOperation;
import pl.edu.pw.rso2012.a1.dvcs.model.operation.PushResponseOperation;

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
        operationEventMap.put(PullRequestOperation.class, PullMailRequestEvent.class);
        operationEventMap.put(PullOperation.class, PullResponseEvent.class);
        operationEventMap.put(PushRequestOperation.class, PushMailRequestEvent.class);
        operationEventMap.put(PushResponseOperation.class, PushResponseEvent.class);
        operationEventMap.put(CloneRequestOperation.class, CloneMailRequestEvent.class);
        operationEventMap.put(CloneOperation.class, CloneResponseEvent.class);
    }
    public ApplicationEvent getEvent(final AbstractOperation abstractOperation)
    {
        try
        {
            final Class<? extends ApplicationEvent> class1 = operationEventMap.get(abstractOperation.getClass());
            if(class1 == null)
                return null;
            final Constructor<? extends ApplicationEvent> constructor = class1.getConstructor(abstractOperation.getClass());
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
        catch(final Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
