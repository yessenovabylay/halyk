package polyclinic.srs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "kredits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kredits extends BaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

    @Column(name = "kredits")
    private int kredit;

    @ManyToOne(fetch = FetchType.EAGER)
    private Items items;

}
