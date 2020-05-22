package com.sise.titulacion.titulacionbackend.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "ID del producto, autogenerado")
    private Long id;

    @Column(name = "nombre_producto")
    @ApiModelProperty(notes = "Nombre del producto", required = true)
    private String nombreProducto;

    @Column(name = "descripcion_producto")
    @ApiModelProperty(notes = "Descripción del producto", required = true)
    private String descripcionProducto;

    @Column(name = "presentacion_producto")
    @ApiModelProperty(notes = "Tipo de presentación del producto", required = true, example = "six pack latas 360 ml")
    private String presentacionProducto;

    @Column(name = "stock_producto")
    @ApiModelProperty(notes = "Stock del producto", required = true)
    private int stockProducto;

    @Column(name = "precio_producto")
    @ApiModelProperty(notes = "Precio del producto", required = true)
    private BigDecimal precioProducto;

    @Column(name = "foto_producto")
    @ApiModelProperty(notes = "Ruta de la foto del producto")
    private String fotoProducto;

    @Column(name = "date_created")
    @ApiModelProperty(notes = "Fecha de creación del producto, autogenerada")
    private Date dateCreated;

    @Column(name = "date_updated")
    @ApiModelProperty(notes = "Fecha de actualización del producto, autogenerada")
    private Date dateUpdated;

    @Column(name = "estado_producto")
    @ApiModelProperty(notes = "Estado del producto, por defecto true")
    private boolean estadoProducto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @ApiModelProperty(notes = "Relación con Categoría (1 Producto - N Categorias)", required = true)
    private Categoria categoria;

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