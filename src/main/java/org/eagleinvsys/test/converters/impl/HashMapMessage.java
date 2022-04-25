package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.HashMap;
import java.util.Map;

public class HashMapMessage implements ConvertibleMessage {

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    Map<String, String> map = new HashMap<>();

    @Override
    public String getElement(String elementId) {
        return map.get(elementId);
    }
}
