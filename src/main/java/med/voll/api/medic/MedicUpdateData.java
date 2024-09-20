package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record MedicUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
