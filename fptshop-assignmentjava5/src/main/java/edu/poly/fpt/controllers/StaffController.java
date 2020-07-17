package edu.poly.fpt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.fpt.dtos.StaffDTO;
import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.service.StaffService;

@Controller
@RequestMapping("/staffs")
public class StaffController {
	@Autowired
	private StaffService staffService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		List<Staff> list = (List<Staff>) staffService.findAll();
		model.addAttribute("staffs", list);
		return "staffs/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		StaffDTO staffDTO = new StaffDTO();
		model.addAttribute("staffDto", staffDTO);
		return "staffs/addOrEdit";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute StaffDTO staffDto) {
		String message = "New staff inserted!";
		Staff staff = null;
//		if (result.hasErrors()) {
//			model.addAttribute("message", "Please input all required fields!");
//			model.addAttribute("staffDto", staffDto);
//			return "staffs/addOrEdit";
//		}

		if (staffDto.getId() != null && staffDto.getId() > 0) {
			message = "The staff updated!";
		}
		Path path = Paths.get("uploads/");
		String image = "";
		if (!staffDto.getPhoto().isEmpty()) {
			try {

				InputStream input = staffDto.getPhoto().getInputStream();
				Files.copy(input, path.resolve(staffDto.getPhoto().getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				image = staffDto.getPhoto().getOriginalFilename().toLowerCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			image = "a3.jpg";
		}

//		try (InputStream inputStream = staffDto.getPhoto().getInputStream()) {
//			Files.copy(inputStream, path.resolve(staffDto.getPhoto().getOriginalFilename()),
//					StandardCopyOption.REPLACE_EXISTING);
//			String fileName = staffDto.getPhoto().getOriginalFilename();
//			model.addAttribute("message", fileName + "uploaded!");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "Error" + e.getMessage());
//		}

		staff = new Staff();
		staff.setName(staffDto.getName());
		staff.setBirthday(staffDto.getBirthday());
		staff.setPhoto(image);
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);
		staffService.save(staff);
		model.addAttribute(staffDto);
		model.addAttribute("message", message);
		return "redirect:/staffs/list";

	}

	@PostMapping("/update")
	public String update(ModelMap model, @ModelAttribute StaffDTO staffDto) {
		Optional<Staff> optionalStaff = staffService.findById(staffDto.getId());
		String message = "New staff inserted!";
		String image = "a3.jpg";
		Staff staff = null;
//		if (result.hasErrors()) {
//			model.addAttribute("message", "Please input all required fields!");
//			model.addAttribute("staffDto", staffDto);
//			return "staffs/addOrEdit";
//		}

		if (staffDto.getId() != null && staffDto.getId() > 0) {
			message = "The staff updated!";
		}
		Path path = Paths.get("uploads/");
		if (optionalStaff.isPresent()) {
			if (!staffDto.getPhoto().isEmpty()) {
				try {

					InputStream input = staffDto.getPhoto().getInputStream();
					Files.copy(input, path.resolve(staffDto.getPhoto().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = staffDto.getPhoto().getOriginalFilename().toLowerCase();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				image = optionalStaff.get().getPhoto();
			}
		} else {
			if (!staffDto.getPhoto().isEmpty()) {
				try {
					InputStream input = staffDto.getPhoto().getInputStream();
					Files.copy(input, path.resolve(staffDto.getPhoto().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = staffDto.getPhoto().getOriginalFilename().toLowerCase();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		staff = new Staff(staffDto.getId(), staffDto.getName(), image, staffDto.getBirthday(),
				new Depart(staffDto.getDepartId(), ""));
		staffService.save(staff);
//		Path path = Paths.get("uploads/");
//		
//		
//		try (InputStream inputStream = staffDto.getPhoto().getInputStream()) {
//			Files.copy(inputStream, path.resolve(staffDto.getPhoto().getOriginalFilename()),
//					StandardCopyOption.REPLACE_EXISTING);
//			String fileName = staffDto.getPhoto().getOriginalFilename();
//			model.addAttribute("message", fileName + "uploaded!");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "Error" + e.getMessage());
//		}
		model.addAttribute(staffDto);
		model.addAttribute("message", message);
		return "redirect:/staffs/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
		Optional<Staff> opDepart = staffService.findById(id);
		StaffDTO staffDTO = null;
		if (opDepart.isPresent()) {
			Staff staff = opDepart.get();
			File file = new java.io.File("uploads/" + staff.getPhoto());
			FileInputStream input;
			try {
				input = new FileInputStream(file);
				MultipartFile multiPhoto = new MockMultipartFile("file", file.getName(), "text/plain",
						IOUtils.toByteArray(input));
				staffDTO = new StaffDTO(staff.getId(), staff.getName(), multiPhoto, staff.getBirthday(),
						staff.getDepart().getId());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("staffDto", staffDTO);
		} else {
			model.addAttribute("staffDto", new StaffDTO());
		}

		return "staffs/update";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		staffService.deleteById(id);
		return "redirect:/staffs/list";
	}

	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<Staff> list = (List<Staff>) staffService.findByNameLikeOrderByNameAsc(name);
	
		String message="";
		if (list!=null&& list.size()>0) {
			message="Kết quả";
			model.addAttribute("staffs", list);
			model.addAttribute("message", message);
		}else {
			message = "Không có kết quả";
//			model.addAttribute("users", staffService.findAll());
			model.addAttribute("message", message);
		}
		return "staffs/find";
	}

	@ModelAttribute(name = "departs")
	public List<Depart> getDeparts() {
		return staffService.findAllDeparts();
	}

}
