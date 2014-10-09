package com.ballo.core.akka.sl.skatteinfo.worker;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.ballo.core.akka.sl.skatteinfo.message.MapSlSaker;
import com.ballo.core.akka.sl.skatteinfo.message.SakerMappet;

import java.util.UUID;

public class Worker extends UntypedActor {

    private final String actorId;
    private ActorRef status;

    public Worker(ActorRef status) {
        this.status = status;
        this.actorId = UUID.randomUUID().toString();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapSlSaker) {
            MapSlSaker mapSlSaker = (MapSlSaker) message;
            System.out.println("Actor "+ actorId + " mapper " + mapSlSaker.getSlSaker().size() + " antall SlSaker.");
            mapTilSkatteinfodokumentOgCommit();
            status.tell(new SakerMappet());
        }else{
            unhandled(message);
        }
    }

    private void mapTilSkatteinfodokumentOgCommit() throws InterruptedException {
        Thread.sleep(200);
    }
}
