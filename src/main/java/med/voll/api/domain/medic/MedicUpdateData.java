package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record MedicUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
