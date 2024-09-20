package med.voll.api.medic;

public record MedicalListingData(String name, String email, String crm, Specialty specialty) {
    public MedicalListingData(Medic medic) {
        this(medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
