package edu.poly.fpt.dtos;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

public class StaffDTO {

	private Long id;

	@NotNull
	@NotEmpty(message = "Name is empty")
	@NotBlank(message = "Name is empty blank")
	@Length(min = 5, max = 50, message = "Name is out of range")
	private String name;

	@NotNull
	private MultipartFile photo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;

	@NotNull
	private Integer departId;

	public StaffDTO() {
		super();
	}

	public StaffDTO(Long id, String name,
			MultipartFile photo, Date birthday, Integer departId) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.birthday = birthday;
		this.departId = departId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	

}
