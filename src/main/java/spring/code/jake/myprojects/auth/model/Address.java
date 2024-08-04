package spring.code.jake.myprojects.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Address {
    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;
    
    @NotNull
    private String zipCode;
}
