package com.muscleshop.web.models.dto;

/*DTO creado para la vista de los productos por MenuSub, categorías, ofertas*/

public class ProductoItemsDto {
    private Integer id;
    private String nombre;
    private String urlProducto;
    private String imagen;
    private String nombreCategoria;
    private String urlCategoria;
    private String nombreMenuSub;
    private String urlMenuSub;
    private Integer productoPropiedadDetalleId;
    private Double precio;
    private Double precioReducido;
    private Integer stock;
    private String detalleNombre;
    private String detalleModificado;
    private String propiedadNombre;
    private String nombreMarca;
    private String urlMarca;
    private String urlMenuMarca;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlProducto() {
        return urlProducto;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getUrlCategoria() {
        return urlCategoria;
    }

    public void setUrlCategoria(String urlCategoria) {
        this.urlCategoria = urlCategoria;
    }

    public String getNombreMenuSub() {
        return nombreMenuSub;
    }

    public void setNombreMenuSub(String nombreMenuSub) {
        this.nombreMenuSub = nombreMenuSub;
    }

    public String getUrlMenuSub() {
        return urlMenuSub;
    }

    public void setUrlMenuSub(String urlMenuSub) {
        this.urlMenuSub = urlMenuSub;
    }

    public Integer getProductoPropiedadDetalleId() {
        return productoPropiedadDetalleId;
    }

    public void setProductoPropiedadDetalleId(Integer productoPropiedadDetalleId) {
        this.productoPropiedadDetalleId = productoPropiedadDetalleId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecioReducido() {
        return precioReducido;
    }

    public void setPrecioReducido(Double precioReducido) {
        this.precioReducido = precioReducido;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetalleNombre() {
        return detalleNombre;
    }

    public void setDetalleNombre(String detalleNombre) {
        this.detalleNombre = detalleNombre;
    }

    public String getDetalleModificado() {
        return detalleModificado;
    }

    public void setDetalleModificado(String detalleModificado) {
        this.detalleModificado = detalleModificado;
    }

    public String getPropiedadNombre() {
        return propiedadNombre;
    }

    public void setPropiedadNombre(String propiedadNombre) {
        this.propiedadNombre = propiedadNombre;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getUrlMarca() {
        return urlMarca;
    }

    public void setUrlMarca(String urlMarca) {
        this.urlMarca = urlMarca;
    }

    public String getUrlMenuMarca() {
        return urlMenuMarca;
    }

    public void setUrlMenuMarca(String urlMenuMarca) {
        this.urlMenuMarca = urlMenuMarca;
    }
}
