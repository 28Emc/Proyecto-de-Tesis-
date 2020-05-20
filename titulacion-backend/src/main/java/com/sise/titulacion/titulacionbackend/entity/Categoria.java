package com.sise.titulacion.titulacionbackend.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_categoria", unique = true)
    private String nombreCategoria;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "date_updated")
    @UpdateTimestamp
    private Date dateUpdated;

    @Column(name = "estado_categoria")
    private boolean estadoCategoria;

    // ETIQUETA QUE RESUEVE UN ERROR A LA HORA DE LLAMAR LOS PRODUCTOS CON SU
    // CATEGORIA, O VICEVERSA
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Set<Producto> productos;

}
