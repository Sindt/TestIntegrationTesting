package cphbusiness.test.sp.integration.entity;

public class User {

	private int id;
	private String name;
	private String job;
	private String dob;

	public User(int id, String name, String job, String dob) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.dob = dob;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
}
