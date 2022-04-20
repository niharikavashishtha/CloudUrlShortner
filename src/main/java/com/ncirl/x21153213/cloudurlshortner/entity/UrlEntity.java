package com.ncirl.x21153213.cloudurlshortner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "URL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlEntity {
    @TableGenerator(name = "ID_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME",
            valueColumnName = "GEN_VAL", pkColumnValue = "ID_Gen", initialValue = Integer.MAX_VALUE, allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_Gen")
    private Long id;

    @Lob
    @Column(columnDefinition = "Text")
    private String longUrl;

    @Column(nullable = false)
    private Date createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", referencedColumnName = "clientId", insertable = false, updatable = false)
    private ClientEntity clientEntity;
    private long clientId;
}
