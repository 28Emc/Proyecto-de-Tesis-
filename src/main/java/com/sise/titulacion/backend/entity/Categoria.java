package com.sise.titulacion.backend.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "ID autogenerado")
    private Long id;

    @Column(name = "nombre_categoria", unique = true)
    @ApiModelProperty(notes = "Nombre de la categoría", required = true)
    private String nombreCategoria;

    @Column(name = "date_created")
    @ApiModelProperty(notes = "Fecha de creación autogenerada")
    private Date dateCreated;

    @Column(name = "date_updated")
    @ApiModelProperty(notes = "Fecha de actualización autogenerada")
    private Date dateUpdated;

    @Column(name = "estado_categoria")
    @ApiModelProperty(notes = "Estado de la categoría, por defecto true")
    private boolean estadoCategoria;

    // ETIQUETA QUE RESUEVE UN ERROR A LA HORA DE LLAMAR LOS PRODUCTOS CON SU
    // CATEGORIA, O VICEVERSA
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Set<Producto> productos;

    @PrePersist
    private void onCreate() {
        dateCreated = new Date();
        dateUpdated = null;
    }

    @PreUpdate
    private void onUpdate() {
        dateUpdated = new Date();
    }

}