import java.util.ArrayList;
import java.util.List;

public class VictoryCondition {
    private List<VictoryListener> listeners = new ArrayList<VictoryListener>();

    public void addListener(VictoryListener toAdd) {
        listeners.add(toAdd);
    }

    public void celebration() {
        System.out.println("Victory!!");

        for (VictoryListener victoryListener : listeners)
            victoryListener.victory();
    }
}
