package com.ballo.core.akka.sl.skatteinfo.repository;

import com.ballo.core.akka.sl.skatteinfo.domain.SlSak;

import java.util.ArrayList;
import java.util.List;

public class SkatteinfoRepository {
    private final static int KJOERER_GANGER = 10;
    int antallKjoert = 0;

    public List<SlSak> hentSlSaker(Long inntektsaar) {
        List<SlSak> slSaker = new ArrayList<>();
        if (antallKjoert != KJOERER_GANGER) {
            slSaker.add(new SlSak());
            slSaker.add(new SlSak());
            slSaker.add(new SlSak());
            antallKjoert ++;
        }

        return slSaker;
    }
}
