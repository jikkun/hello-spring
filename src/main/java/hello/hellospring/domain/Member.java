package hello.hellospring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    // PK지정, db가 알아서 생성은 identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
