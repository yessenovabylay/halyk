package polyclinic.srs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments extends BaseEntity{
    @Column(name = "comment",columnDefinition="TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Items items;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
}
