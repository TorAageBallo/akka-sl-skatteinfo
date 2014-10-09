package com.ballo.core.akka.sl.skatteinfo;

import akka.actor.*;
import com.ballo.core.akka.sl.skatteinfo.message.StartWork;
import com.ballo.core.akka.sl.skatteinfo.worker.*;

public class SkattegrunnlagService {

    public void overfoerGrunnlag(Long inntektsaar) {
        ActorSystem actorSystem = ActorSystem.create("SkattegrunnlagSystem");
        final ActorRef status = actorSystem.actorOf(new Props(com.ballo.core.akka.sl.skatteinfo.worker.Status.class), "status");

        // create the master
        ActorRef workProvider = actorSystem.actorOf(new Props(new UntypedActorFactory() {
            public UntypedActor create() {
                return new WorkProvider(status);
            }
        }), "workProvider");

        workProvider.tell(new StartWork(inntektsaar));
    }
}
