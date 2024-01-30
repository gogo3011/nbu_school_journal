package com.example.schooljournal.utils;

import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {
    private CollectionUtils() {

    }

    public static <T> Set<T> toSet(T... el) {
        var set = new HashSet<T>();
        for (T crrEl : el) {
            set.add(crrEl);
        }
        return set;
    }
}
