package lt.baraksoft.summersystem.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "configurationEntry")
public class ConfigurationEntry implements IEntity<Integer> {
	private static final long serialVersionUID = 2070081085809252992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "type")
	private ConfigurationEntryEnum type;

	@Size(max = 2000)
	@Column(name = "value")
	private String value;

	@Version
	private Integer version;

	public ConfigurationEntry() {
	}

	public ConfigurationEntry(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ConfigurationEntryEnum getType() {
		return type;
	}

	public void setType(ConfigurationEntryEnum type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "lt.baraksoft.summersystem.model.ConfigurationEntry[ id=" + id + " ]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConfigurationEntry that = (ConfigurationEntry) o;

		return type == that.type;

	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}
}
