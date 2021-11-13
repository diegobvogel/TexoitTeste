package com.texoit.api.classes;

import java.util.Objects;

public class Intervalo {

	public Ganhador min;
	public Ganhador max;

	public Ganhador getMin() {
		return min;
	}

	public void setMin(Ganhador min) {
		this.min = min;
	}

	public Ganhador getMax() {
		return max;
	}

	public void setMax(Ganhador max) {
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
