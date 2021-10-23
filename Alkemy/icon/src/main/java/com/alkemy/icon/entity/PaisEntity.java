package com.alkemy.icon.entity;

import lombok.Setter;
import lombok.Getter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@Table(name = "pais")
public class PaisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String imagen;
    private String denominacion;
    @Column(name = "cant_habitantes")
    private Long cantidadHabitantes;
    private Long superficie;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contitente_id", insertable = false, updatable = false)
    private ContinenteEntity continente;
    @Column(name = "continente_id", nullable = false)
    private Long continenteId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "icon_pais",
            joinColumns = @JoinColumn(name = "pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<IconEntity> icons = new HashSet<>();

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }




}
