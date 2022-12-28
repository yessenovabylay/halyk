package polyclinic.srs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

    @Column(name = "Title", length = 512)
    private String title;

    @Column(name = "short_title")
    private String short_title;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;



}
