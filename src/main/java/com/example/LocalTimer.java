package com.example;

import java.time.LocalTime;

public class LocalTimer {

    /**
     * @return local time, e.g. 16:31:17.904
     */
    public String now() {
        return LocalTime.now().toString();
    }
}
