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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.fpt.dtos.StaffDTO;
import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Record;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.service.RecordService;
import edu.poly.fpt.service.StaffService;

@Controller
@RequestMapping("/records")
public class RecordController {
	@Autowired
	private RecordService recordService;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("records", recordService.findAll());
		return "records/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		Record record = new Record();

		model.addAttribute("record", record);
		return "records/addOrEdit";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute Record record) {
		String message = "New record inserted!";
		if (record.getId() != null && record.getId() > 0) {
			message = "The record updated!";
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		record.setDate(date);
		recordService.save(record);
		model.addAttribute(record);
		model.addAttribute("message", message);
		return "redirect:/records/list";

	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
		Optional<Record> opDepart = recordService.findById(id);
		if (opDepart.isPresent()) {
			model.addAttribute("record", opDepart.get());
		} else {
			return list(model);
		}

		return "records/addOrEdit";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		recordService.deleteById(id);
		return "redirect:/records/list";
	}

	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") Integer type) {

		List<Record> list = (List<Record>) recordService.findByTypeLikeOrderByTypeAsc(type);
		String message = "";
		if (type != null && type == 1 || type == 0) {
			if (list != null && list.size() > 0) {
				System.out.println(list);
				message = "Kết quả";
				model.addAttribute("records", list);
				model.addAttribute("message", message);
			} else {

				message = "Không có kết quả";
				model.addAttribute("records", recordService.findAll());
				model.addAttribute("message", message);
			}
		} else {
			System.out.println("loại : "+type);
			model.addAttribute("records", recordService.findAll());
		}

		return "records/list";
	}

	@ModelAttribute(name = "staffs")
	public List<Staff> getStaffs() {
		return recordService.findAllStaffs();
	}
	
	@GetMapping("/ranktop10")
	public String getRank() {
		return "records/top10staffs";
	}
	
//	@ModelAttribute(name = "rank")
//	public List<Object[]> getSRank(){
//		return recordService.getRankHome();
//	}
	@ModelAttribute(name = "staffsRecord")
	public List<Object[]> getSfaffsRecord(){
		return recordService.getStaffsRecord();
	}
	
	@ModelAttribute(name = "departsRecord")
	public List<Object[]> getDepartsRecord(){
		return recordService.getDepartsRecord();
	}
	
	@GetMapping("/topStaffs")
	public String getRankStaffs() {
		return "records/liststaffs";
	}
	
	@GetMapping("/topDeparts")
	public String getRankDeparts() {
		return "records/listdeparts";
	}


}
