package com.texoit.api.classes;

import java.util.Objects;

public class Ganhador {

	public String producer;
	public int interval;
	public int previousWin;
	public int followingWin;

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followingWin, interval, previousWin, producer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ganhador other = (Ganhador) obj;
		return followingWin == other.followingWin && interval == other.interval && previousWin == other.previousWin
				&& Objects.equals(producer, other.producer);
	}

}
