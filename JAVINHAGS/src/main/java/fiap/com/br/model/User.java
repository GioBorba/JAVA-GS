    package fiap.com.br.model;
    
    import lombok.*;
    
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    
    @Setter
    @Getter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class User {
    
        // Getters and setters
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String password;
    
    }
