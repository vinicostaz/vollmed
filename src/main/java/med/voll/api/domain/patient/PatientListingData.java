package med.voll.api.domain.patient;

public record PatientListingData(Long id, String name, String email) {
    public PatientListingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail());
    }
}
