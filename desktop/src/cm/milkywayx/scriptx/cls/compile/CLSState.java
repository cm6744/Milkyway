package cm.milkywayx.scriptx.cls.compile;

import cm.milkywayx.scriptx.cls.statement.IfManage;
import cm.milkywayx.scriptx.cls.statement.WhileState;
import cm.milkyway.lang.Task;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.container.map.Map;

public class CLSState
{

    public Variables vars = new Variables();
    public List<Task> lines = new List<>();
    public List<IfManage> ifStates = new List<>();
    public List<WhileState> whileStates = new List<>();
    public Map<String, CLS> links = new Map<>();

    public void reset()
    {
        vars.flush();
    }

}
