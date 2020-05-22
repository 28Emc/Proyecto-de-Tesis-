CREATE TABLE heroku_56897c984a6aed1.tb_categorias (
  id bigint NOT NULL AUTO_INCREMENT,
  nombre_categoria varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  estado_categoria bit(1) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY nombre_categoria_UNIQUE (nombre_categoria)
)

CREATE TABLE heroku_56897c984a6aed1.tb_productos (
  id bigint NOT NULL AUTO_INCREMENT,
  nombre_producto varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  descripcion_producto varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  presentacion_producto varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  stock_producto int DEFAULT NULL,
  precio_producto decimal(13,2) DEFAULT NULL,
  foto_producto varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'https://dev-titulacion.000webhostapp.com/images/productos/no-image.png',
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  estado_producto bit(1) DEFAULT b'1',
  id_categoria bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_PRODUCTO_CATEGORIA_idx (id_categoria),
  CONSTRAINT FK_PRODUCTO_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES tb_categorias (id) ON DELETE CASCADE ON UPDATE CASCADE
)