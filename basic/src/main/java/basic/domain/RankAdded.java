package basic.domain;

import basic.domain.*;
import basic.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RankAdded extends AbstractEvent {

    private Long id;
    private String name;

    public RankAdded(Rank aggregate) {
        super(aggregate);
    }

    public RankAdded() {
        super();
    }
}
