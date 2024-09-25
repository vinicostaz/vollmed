package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.address.Address;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid MedicRegistrationData data, UriComponentsBuilder uriBuilder){
        var medic = new Medic(data);
        repository.save(medic);

        var uri = uriBuilder.path("/medics/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicalDetailsData(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicalListingData>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page =  repository.findAllByActiveTrue(pageable).map(MedicalListingData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid MedicUpdateData data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateInformation(data);

        return ResponseEntity.ok(new MedicalDetailsData(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicalDetailsData(medic));
    }

}