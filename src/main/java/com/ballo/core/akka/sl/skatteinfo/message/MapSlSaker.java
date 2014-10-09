package com.ballo.core.akka.sl.skatteinfo.message;

import com.ballo.core.akka.sl.skatteinfo.domain.SlSak;

import java.util.List;

public class MapSlSaker {
    private List<SlSak> slSaker;

    public MapSlSaker(List<SlSak> slSaker) {
        this.slSaker = slSaker;
    }

    public List<SlSak> getSlSaker() {
        return slSaker;
    }
}
