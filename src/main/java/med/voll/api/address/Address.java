package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zip_code;
    private String city;
    private String state;
    private String number;
    private String additional_info;

    public Address(AddressData data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zip_code = data.zip_code();
        this.city = data.city();
        this.state = data.state();
        this.number = data.number();
        this.additional_info = data.additional_info();
    }
}