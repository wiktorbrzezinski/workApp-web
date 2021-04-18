package pl.BrzezinskiCRM;

import lombok.*;

import javax.persistence.*;

@Table(name="users")
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String role;

}
