package med.voll.api.medic;

import med.voll.api.address.Address;

public record MedicalDetailsData(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {

    public MedicalDetailsData(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getPhone(), medic.getSpecialty(), medic.getAddress());
    }
}
