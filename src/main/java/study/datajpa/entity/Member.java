package study.datajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    //기본 생성자 protected 중요
    protected Member() {
    }

    public Member(String name) {
        this.name = name;
    }
}
