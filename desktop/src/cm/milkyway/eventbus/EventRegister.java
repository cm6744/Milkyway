package cm.milkyway.eventbus;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.container.map.Map;
import cm.milkyway.tipy.Tipy;

public class EventRegister
{

    Map<String, List<EventSubscriber>> subscribers = new Map<>();

    public void register(EventSubscriber ess, String c)
    {
        List<EventSubscriber> el = subscribers.get(c);
        if(el == null) {
            el = new List<>();
        }
        el.add(ess);
        subscribers.put(c, el);
    }

    public void dispatch(String type, Tipy tipy)
    {
        List<EventSubscriber> el = subscribers.get(type);
        EventSubscriber ess;
        for(int i = 0; i < el.size(); i++) {
            ess = el.get(i);

            if(ess != null) {
                ess.catchEvent(tipy);
            }
        }
    }

}
