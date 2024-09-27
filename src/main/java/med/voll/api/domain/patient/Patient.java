package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @Embedded
    private Address address;

    private Boolean active;


    public Patient(PatientRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateInformation(PatientUpdateData data) {
        if(data.name() != null) {
            this.name = data.name();
        }

        if(data.phone() != null) {
            this.phone = data.phone();
        }

        if(data.address() != null) {
            this.address.updateInformation(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
