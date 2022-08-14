package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //기본 생성자 protected 중요
/*    protected Member() {
    }*/

    public Member(String name) {
        this.username = name;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.changeTeam(team);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
