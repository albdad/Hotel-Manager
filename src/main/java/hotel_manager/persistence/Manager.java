package hotel_manager.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotNull
    @Column(unique = true)
    private String eMailAddress;

    @NotNull
    @NotEmpty(message = "Der Vorname muss angegeben werden.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z]{2,25}$",
            message = "Der Vorname muss aus Klein- und Großbuchstaben bestehen und mindestens zwei buchstaben haben.")
    private String vorName;

    @NotNull
    @NotEmpty(message = "Der Nachname muss angegeben werden")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z]{2,25}$",
            message = "Der Nachname muss aus Klein- und Großbuchstaben bestehen und mindestens zwei buchstaben haben.")
    private String nachName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z]{1,20})(?=.*[A-Z]{1,20})(?=.*[0-9]{1,20})[a-zA-Z0-9]{6,20}$",
            message = "Das Passwort muss mindestens einen Kleinbuchstaben, einen Großbuchstaben, eine Zahl und mindestens 6 Ziffern enthalten.")
    private String password;


}
