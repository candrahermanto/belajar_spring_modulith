package net.candra.belajar_spring_modulith.activity;

import net.candra.belajar_spring_modulith.message.MessageEventPublisher;
import net.candra.belajar_spring_modulith.pemohon.PemohonEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActivityService {
    @ApplicationModuleListener
    void logPengguna(PemohonEventPublisher event) {
        System.out.println("Pemohon id" + event);
    }
    @ApplicationModuleListener
    void logMessage(MessageEventPublisher messageEventPublisher) {
        System.out.println("log kirim email : "+ messageEventPublisher.emailAddress());
    }
}

