package com.texoit.api.classes;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "premiacao")
public class Premiacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "year")
	public Integer year;

	@Column(name = "title")
	public String title;

	@Column(name = "studios")
	public String studios;

	@Column(name = "producers")
	public String producers;

	@Column(name = "winner")
	public String winner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, producers, studios, title, winner, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premiacao other = (Premiacao) obj;
		return id == other.id && Objects.equals(producers, other.producers) && Objects.equals(studios, other.studios)
				&& Objects.equals(title, other.title) && Objects.equals(winner, other.winner)
				&& Objects.equals(year, other.year);
	}

}
