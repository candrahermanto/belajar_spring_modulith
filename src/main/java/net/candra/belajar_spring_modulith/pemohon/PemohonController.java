package net.candra.belajar_spring_modulith.pemohon;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/pemohon")
public class PemohonController {

    private final PemohonService pemohonService;


    public PemohonController(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }



    @PostMapping("/add")
    ResponseEntity<Pemohon> daftar(@RequestBody Pemohon pemohon) {

        var data = pemohonService.simpan(pemohon);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

}

@Service
@Transactional
class PemohonService {
    private final PemohonRepository pemohonRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    PemohonService(PemohonRepository pemohonRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.pemohonRepository = pemohonRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    Pemohon simpan(Pemohon pemohon) {
        var data =  this.pemohonRepository.save(pemohon);
        this.applicationEventPublisher.publishEvent(new PemohonEventPublisher(data.id(), data.name(), data.emailAddress()));
        return data;
    }
}

interface PemohonRepository extends CrudRepository<Pemohon, String> {}

@Table(name = "pemohon")
record Pemohon(@Id  int id, String name, String emailAddress, Date createdDate) {}