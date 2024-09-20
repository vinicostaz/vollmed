package med.voll.api.medic;

public record MedicalListingData(Long id, String name, String email, String crm, Specialty specialty) {
    public MedicalListingData(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
