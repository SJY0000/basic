package basic.domain;

import basic.BasicApplication;
import basic.domain.RankAdded;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Rank_table")
@Data
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @PostPersist
    public void onPostPersist() {
        RankAdded rankAdded = new RankAdded(this);
        rankAdded.publishAfterCommit();
    }

    public static RankRepository repository() {
        RankRepository rankRepository = BasicApplication.applicationContext.getBean(
            RankRepository.class
        );
        return rankRepository;
    }
}