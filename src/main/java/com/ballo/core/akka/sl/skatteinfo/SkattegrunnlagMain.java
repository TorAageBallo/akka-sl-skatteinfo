package com.ballo.core.akka.sl.skatteinfo;

public class SkattegrunnlagMain {

    public static void main(String[] args) {
        SkattegrunnlagService skattegrunnlagService = new SkattegrunnlagService();
        Long inntektsaar = 2014L;
        skattegrunnlagService.overfoerGrunnlag(inntektsaar);
    }

}
