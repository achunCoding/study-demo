package cn.wycfight.study.caching.ttl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author HJ01812A
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City implements Serializable {
    private static final long serialVersionUID = -4057874886599951708L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private double cityCentreLattitude;

    private double cityCentreLongitude;
}
