package net.candra.belajar_spring_modulith.message;

import net.candra.belajar_spring_modulith.pemohon.PemohonEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MessageService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @ApplicationModuleListener
    void on(PemohonEventPublisher pemohonPublishEvent) {
        sendEmail(pemohonPublishEvent.emailAddress(),"pengguna tersiman");
    }

    private void sendEmail(String emailAddress, String message) {
        this.applicationEventPublisher.publishEvent(new MessageEventPublisher(emailAddress, new Date()));
    }
}
