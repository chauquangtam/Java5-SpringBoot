package edu.poly.fpt.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.fpt.models.Staff;
import edu.poly.fpt.service.StaffService;

@Controller
public class ImageUploadFileController {

	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "getimage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
		Optional<Staff> sop = staffService.findById(id);
		if (sop.isPresent()) {
			Staff staff = sop.get();
			System.out.println(staff.getPhoto());
			try {
				Path path = Paths.get("uploads", staff.getPhoto());
				byte[] buffer = Files.readAllBytes(path);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/png")).body(byteArrayResource);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ResponseEntity.badRequest().build();
	}
}
