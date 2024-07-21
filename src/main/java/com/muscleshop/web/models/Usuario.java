package com.muscleshop.web.models;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String correo;
	private String password;
	private LocalDate fecha;

	@OneToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "rol_perfil_id")
	private RolPerfil rolPerfil;
	
	@OneToOne(mappedBy = "usuario")
	private UsuarioPerfil usuarioPerfil;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_rol", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "rol_id") })
	private Set<Roles> roles;

	public Usuario() {
	}

	public Usuario(Integer id, String correo, String password, LocalDate fecha, Estado estado, RolPerfil rolPerfil,
			UsuarioPerfil usuarioPerfil, Set<Roles> roles) {
		super();
		this.id = id;
		this.correo = correo;
		this.password = password;
		this.fecha = fecha;
		this.estado = estado;
		this.rolPerfil = rolPerfil;
		this.usuarioPerfil = usuarioPerfil;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public RolPerfil getRolPerfil() {
		return rolPerfil;
	}

	public void setRolPerfil(RolPerfil rolPerfil) {
		this.rolPerfil = rolPerfil;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public UsuarioPerfil getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	

}
