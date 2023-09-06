package hotel_manager.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[0-9]$", message = "Zimmer Nummer muss eine Zahl sein.")
    @NotEmpty(message = "Zimmernummer muss angegeben werden.")
    private String name;

    @NotEmpty(message = "Zimmer größe muss angegeben werden.")
    @Pattern(regexp = "^[0-9]$", message = "Größe muss eine Zahl sein.")
    private String sizeInM2;

    @NotEmpty(message = "Miete muss angegeben werden.")
    @Pattern(regexp = "^[0-9]$", message = "Miete muss eine Zahl sein.")
    private String rent;

    @NotEmpty(message = "Bezirk muss angegeben werden.")
    @Pattern(regexp = "^[0-9]$", message = "Bezirk muss als Zahl angegeben werden.")
    private String district;

    @NotEmpty(message = "Addresse muss angegeben werden.")
    private String address;

    private boolean available;

    private Collection<String> imagePaths;

}
