package com.ballo.core.akka.sl.skatteinfo.worker;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.routing.RoundRobinRouter;
import com.ballo.core.akka.sl.skatteinfo.domain.SlSak;
import com.ballo.core.akka.sl.skatteinfo.message.IngenFlereRaderIDb;
import com.ballo.core.akka.sl.skatteinfo.message.MapSlSaker;
import com.ballo.core.akka.sl.skatteinfo.message.SakerSendtTilMapping;
import com.ballo.core.akka.sl.skatteinfo.message.StartWork;
import com.ballo.core.akka.sl.skatteinfo.repository.SkatteinfoRepository;

import java.util.List;

public class WorkProvider extends UntypedActor {

    SkatteinfoRepository skatteinfoRepository = new SkatteinfoRepository();

    private final ActorRef skatteinfoDokumentActorRef;
    private ActorRef status;

    public WorkProvider(ActorRef status) {
        this.status = status;
        skatteinfoDokumentActorRef = createSkatteinfoDokumentActor(status);
    }

    private ActorRef createSkatteinfoDokumentActor(final ActorRef status) {
        Props props = new Props(new UntypedActorFactory() {
            public UntypedActor create() {
                return new Worker(status);
            }
        });
        return getContext().actorOf(props.withRouter(new RoundRobinRouter(3)), "skatteinfoDokument-actor");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof StartWork) {
            StartWork startWork = (StartWork) message;
            System.out.println("Starter å hente slSaker fra DB");
            List<SlSak> slSaker;
            do {
                slSaker = skatteinfoRepository.hentSlSaker(startWork.getInntektsaar());
                if (!slSaker.isEmpty()) {
                    status.tell(new SakerSendtTilMapping());
                    skatteinfoDokumentActorRef.tell(new MapSlSaker(slSaker));
                }
            } while (!slSaker.isEmpty());

            status.tell(new IngenFlereRaderIDb());
        }else{
            unhandled(message);
        }

    }
}
