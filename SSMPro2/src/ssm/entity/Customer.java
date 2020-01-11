package ssm.entity;

/**
 * 客户实体类
 * 
 * @author sayyes
 */
public class Customer {
	private int id;
	private String name;
	private String telephone;
	private String address;

	// 无参构造函数
	public Customer() {

	}

	// 有参构造函数
	public Customer(int id, String name, String telephone, String address) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.address = address;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", telephone=" + telephone + ", address=" + address + "]";
	}
}
