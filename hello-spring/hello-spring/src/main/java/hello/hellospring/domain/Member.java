package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 id(pk)를 설정해주는 것: IDENTITY 전략
    private Long id;

//    @Column(name = "username") // 컬럼명 -> db와 매핑할 때 필요
    private String name; // 변수명

    // (public) getId 존재 이유: id가 private라서
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
