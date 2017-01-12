package org.throwable.blocking;

/**
 * @author zjc
 * @version 2017/1/12 23:54
 * @description
 */
public class Task {

	private int id;
	private String name;

	public Task() {
	}

	public Task(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
