package med.voll.api.domain.patient;

import med.voll.api.domain.address.AddressData;

public record PatientRegistrationData(String name, String email, String phone, String cpf, AddressData address) {
}
