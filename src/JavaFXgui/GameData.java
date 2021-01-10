package JavaFXgui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class GameData {
    private ArrayList<Integer> list;
    private BooleanProperty setupDone;
    private ArrayList<String> nameList;

    public GameData(ArrayList<Integer> list, boolean setupDone, ArrayList<String> nameList) {
        this.list = list;
        this.setupDone = new SimpleBooleanProperty(setupDone);
        this.nameList = nameList;
    }

    public ArrayList<Integer> getList() {
        return list;
    }



    public ArrayList<String> getNameList() {
        return nameList;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }



    public void setNameList(ArrayList<String> nameList) {
        this.nameList = nameList;
    }


    public boolean isSetupDone() {
        return setupDone.get();
    }

    public BooleanProperty setupDoneProperty() {
        return setupDone;
    }

    public void setSetupDone(boolean setupDone) {
        this.setupDone.set(setupDone);
    }
}
