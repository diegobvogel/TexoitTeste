package com.texoit.api.classes;

import java.util.List;
import java.util.Objects;

public class Intervalo {

	public List<Ganhador> min;
	public List<Ganhador> max;

	public List<Ganhador> getMin() {
		return min;
	}

	public void setMin(List<Ganhador> min) {
		this.min = min;
	}

	public List<Ganhador> getMax() {
		return max;
	}

	public void setMax(List<Ganhador> max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		return Objects.hash(max, min);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervalo other = (Intervalo) obj;
		return Objects.equals(max, other.max) && Objects.equals(min, other.min);
	}

}
