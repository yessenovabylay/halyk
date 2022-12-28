package polyclinic.srs.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{
    @Column(name = "payment_name")
    private String name;

    @ManyToOne
    private Category category;
}
