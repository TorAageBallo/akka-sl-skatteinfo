package com.ballo.core.akka.sl.skatteinfo.message;

public class StartWork {
    private Long inntektsaar;

    public StartWork(Long inntektsaar) {
        this.inntektsaar = inntektsaar;
    }

    public Long getInntektsaar() {
        return inntektsaar;
    }
}
