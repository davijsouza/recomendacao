package br.com.zanona.tcc.server.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "roteiro_turistico")
@SequenceGenerator(name = "seq_roteiro_turistico", sequenceName = "seq_roteiro_turistico", allocationSize = 1)
public class RoteiroTuristico implements CaseComponent, Serializable {

	private static final long serialVersionUID = -954887524394065230L;

	@Id
	@Column(name = "rot_id", columnDefinition = "bigserial")
	@GeneratedValue(generator = "seq_roteiro_turistico")
	private Integer id;

	@Column(name = "rot_nome")
	private String nome;

	@ManyToMany
	@JoinTable(
		name = "roteiro_turistico_atrativo", 
		joinColumns = { @JoinColumn(name = "rtt_rot_id" , referencedColumnName="rot_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "rtt_att_id" , referencedColumnName="att_id") }
	)
	private List<AtrativoTuristico> atrativos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AtrativoTuristico> getAtrativos() {
		return atrativos;
	}

	public void setAtrativos(List<AtrativoTuristico> atrativos) {
		this.atrativos = atrativos;
	}
	
	@Override
	@JsonIgnore
	public Attribute getIdAttribute() {
		return new Attribute("id", getClass());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoteiroTuristico other = (RoteiroTuristico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
