package cm.milkywaycls.cls.compile;

import cm.milkywaycls.cls.statement.IfManage;
import cm.milkywaycls.cls.statement.WhileState;
import cm.milkywaytool.Task;
import cm.milkywaytool.container.List;
import cm.milkywaytool.container.Map;

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
