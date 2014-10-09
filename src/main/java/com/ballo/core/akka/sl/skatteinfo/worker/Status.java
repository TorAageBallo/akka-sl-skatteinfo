package com.ballo.core.akka.sl.skatteinfo.worker;

import akka.actor.UntypedActor;
import com.ballo.core.akka.sl.skatteinfo.message.IngenFlereRaderIDb;
import com.ballo.core.akka.sl.skatteinfo.message.SakerMappet;
import com.ballo.core.akka.sl.skatteinfo.message.SakerSendtTilMapping;


public class Status extends UntypedActor {
    private boolean ferdigAaLese;
    private int antallMeldingerFraProvider = 0;
    private int antallMeldingerBehandlet = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof IngenFlereRaderIDb) {
            System.out.println("Ingen flere rader i DB");
            ferdigAaLese = true;
        } else if (message instanceof SakerSendtTilMapping) {
            System.out.println("Ny sak sendt til mapping");
            antallMeldingerFraProvider ++;
        } else if (message instanceof SakerMappet) {
            System.out.println("Ny sak mappet ferdig");
            antallMeldingerBehandlet ++;
        } else
            unhandled(message);
        avsluttHvisAlleProsesserErFerdig();
    }

    private void avsluttHvisAlleProsesserErFerdig() {
        if (ferdigAaLese && antallMeldingerFraProvider == antallMeldingerBehandlet ) {
            System.out.println("Ferdig å behandle, I'm shutting down");
            getContext().system().shutdown();
        }
    }
}
