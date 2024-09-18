package med.voll.api.controller;

import med.voll.api.address.Address;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicRegistrationData;
import med.voll.api.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody MedicRegistrationData data){
        repository.save(new Medic(data));
    }


}