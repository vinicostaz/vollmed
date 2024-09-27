package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.medic.MedicalListingData;
import med.voll.api.domain.medic.*;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailsData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingData>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page =  repository.findAllByActiveTrue(pageable).map(PatientListingData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateData data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInformation(data);

        return ResponseEntity.ok(new PatientDetailsData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsData(patient));
    }

}