package hello.hellospring.domain;

public class Member {

    private Long id;
    private String name;

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
