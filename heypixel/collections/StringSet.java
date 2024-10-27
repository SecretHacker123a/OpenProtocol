package dev.undefinedteam.gensh1n.protocol.heypixel.collections;

import java.util.HashSet;

public class StringSet extends HashSet<String> {
    public String data;

    public StringSet(String str) {
        this.data = str;
        add("Error Name " + this.data);
    }
}
