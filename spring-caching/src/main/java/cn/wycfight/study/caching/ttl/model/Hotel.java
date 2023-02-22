package cn.wycfight.study.caching.ttl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {
    private static final long serialVersionUID = -7522425515104603968L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double rating;
    /**
     * 使用该注解 可以避免 无限循环和递归的问题
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;

    private String address;
    private double lattitude;
    private double longitude;
    private boolean deleted = false;


}
