package com.client.rest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;
    private String password;
    private boolean status;

}
