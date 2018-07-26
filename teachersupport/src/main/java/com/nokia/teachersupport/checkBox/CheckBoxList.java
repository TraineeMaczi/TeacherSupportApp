package com.nokia.teachersupport.checkBox;


import java.util.ArrayList;
import java.util.List;

public class CheckBoxList<T> {
private List<T> items=new ArrayList<>();

public CheckBoxList(List<T> inputList)
    {
        items=inputList;
    }
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
