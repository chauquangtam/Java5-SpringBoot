package edu.poly.fpt.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.User;
import edu.poly.fpt.service.DepartService;
import edu.poly.fpt.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/list")
	public String listDeparts(ModelMap model) {
		List<User> list = (List<User>) userService.findAll();
		model.addAttribute("users", list);
		return "users/list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("user", new User());
		return "users/addOrEdit";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute User user) {
		Optional<User> opDepart = userService.findById(user.getUsername());
		String message = "New user inserted!";
		if (user.getUsername() != null) {
			message = "The user updated!";
		}
		if (user.getPassword().equals("")) {
			user.setPassword(opDepart.get().getPassword());
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		userService.save(user);
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		return "redirect:/users/list";
	}

	@GetMapping("/edit/{username}")
	public String edit(ModelMap model, @PathVariable(name = "username") String id) {
		Optional<User> opDepart = userService.findById(id);
		if (opDepart.isPresent()) {
			model.addAttribute("user", opDepart.get());

		} else {
			return listDeparts(model);
		}
		return "users/addOrEdit";
	}

	@GetMapping("/delete/{username}")
	public String delete(ModelMap model, @PathVariable(name = "username") String id) {
		userService.deleteById(id);
		return "redirect:/users/list";
	}

	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<User> list = (List<User>) userService.findByUsernameLikeOrderByUsernameAsc(name);

		String message = "";
		if (list != null && list.size() > 0) {
			message = "Kết quả";
			model.addAttribute("users", list);
			model.addAttribute("message", message);
		} else {
			message = "Không có kết quả";
//			model.addAttribute("staffs", staffService.findAll());
			model.addAttribute("message", message);
		}

		return "users/list";
	}

	@RequestMapping("/profile")
	public String profile(ModelMap model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username==null) {
			return "redirect:/home";
		}
		Optional<User> opDepart = userService.findById(username);
		String mk = "******************";
		if (opDepart.isPresent()) {
			model.addAttribute("user", opDepart.get());
			model.addAttribute("password", mk);
		} else {
			return listDeparts(model);
		}
		return "admin/profile";
	}

	@PostMapping("/profile")
	public String updateProfile(ModelMap model, @ModelAttribute User user) {
		Optional<User> opDepart = userService.findById(user.getUsername());
		String message = "New user inserted!";
		if (user.getUsername() != null) {
			message = "The user updated!";
		}
		user.setPassword(opDepart.get().getPassword());
		userService.save(user);
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		return "admin/profile";
	}

	@RequestMapping("/forgotpassword")
	public String forgotpassword(ModelMap model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		Optional<User> opDepart = userService.findById(username);
		String mk = "******************";
		if (opDepart.isPresent()) {
			model.addAttribute("user", opDepart.get());
			model.addAttribute("password", mk);
		} else {
			return listDeparts(model);
		}
		return "admin/forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String forgotpasswordSave(ModelMap model, @ModelAttribute User user,
			@RequestParam("password") String password, @RequestParam("passwordOld") String passwordOld,
			@RequestParam("passwordNew") String passwordNew, HttpSession session) {
		String username = (String) session.getAttribute("username");
		Optional<User> opDepart = userService.findById(username);
		String message = "New user inserted!";
		if (!opDepart.get().getPassword().equals(password)) {
			message = "Phải nhập đúng mật khẩu cũ!";
		} else {
			if (!passwordOld.equals(passwordNew)) {
				message = "Mật khẩu không trùng khớp!";
			} else {
				user.setUsername(opDepart.get().getUsername());
				user.setPassword(passwordOld);
				user.setFullname(opDepart.get().getFullname());
				userService.save(user);
				message = "Cập nhật thành công!";

			}

		}
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		return "admin/forgotpassword";
	}
	
}
