package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.address.Address;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid MedicRegistrationData data){
        repository.save(new Medic(data));
    }

    @GetMapping
    public Page<MedicalListingData> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return repository.findAllByActiveTrue(pageable).map(MedicalListingData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid MedicUpdateData data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateInformation(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();
    }

}