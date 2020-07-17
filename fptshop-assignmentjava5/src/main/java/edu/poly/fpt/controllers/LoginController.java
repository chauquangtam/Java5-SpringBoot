package edu.poly.fpt.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.fpt.models.User;
import edu.poly.fpt.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private String userId = "";

	@GetMapping("/menu/login")
	public String login(ModelMap model, User user) {
		model.addAttribute("login", user);
//		userId = user.getUsername();
		return "admin/login";
	}

	@GetMapping("/menu/regis")
	public String regis(ModelMap model) {
		model.addAttribute("login", new User());
		return "admin/register";
	}

	@PostMapping("/menu/regis")
	public String addregis(ModelMap model, @ModelAttribute User user) {
		String message = "";
//		if (user.getUsername() != null) {
//			message = "The depart updated!";
//		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User users = userService.save(user);
		System.out.println("ten: " + users.getFullname());
		if (user.getFullname().equals("") && user.getUsername().equals("") && user.getPassword().equals("")) {
			System.out.println("ten22: " + users.getFullname());
			message = "Không được bỏ trống";
			model.addAttribute("login", user);
			model.addAttribute("message", message);
		} else {
			if (!users.getUsername().isEmpty()) {
				message = "Người dùng: " + user.getFullname() + " được tạo thành công!";
				model.addAttribute("login", user);
				model.addAttribute("message", message);
				return "admin/login";
			} else {
				System.out.println("ten2: " + users.getFullname());
				message = "Tạo tài khoản thất bại";
				model.addAttribute("login", user);
				model.addAttribute("message", message);

			}
		}
		return "admin/register";

	}

	@PostMapping("/menu/login")
	public String checkLogin(ModelMap model, @RequestParam("username") String username, @ModelAttribute User user,
			@RequestParam("password") String password, HttpSession session) {
		String message = "";
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getUsername().equals("") && user.getPassword().equals("")) {
			message = "Không được bỏ trống";
			model.addAttribute("login", user);

			model.addAttribute("message", message);
		} else {
			if (userService.checkLogin(user.getUsername(), user.getPassword())) {
				System.out.println("tttt" + user.getPassword());
				userId = user.getUsername();
				session.setAttribute("username", user.getUsername());
				model.addAttribute("userId", userId);
				System.out.println(userId);
				return "redirect:/home";
			} else {
				model.addAttribute("login", user);
				model.addAttribute("message", "Username and password not exist");
			}
		}
		return "admin/login";
	}

	@RequestMapping("/menu/qmk")
	public String qmk(ModelMap model) {
		model.addAttribute("user", new User());
		return "admin/qmk";
	}

	@PostMapping("/menu/qmk")
	public String resetPassword(ModelMap model, @ModelAttribute User user) {
		Optional<User> opDepart = userService.findById(user.getUsername());
		String message="";
		if(user.getUsername().equals("")&&user.getUsername().length()==0) {
			message="Vui lòng nhập tên tài khoản !";
		}else {
			if (opDepart.isPresent()) {
				String password="";
				message="Mật khẩu của bạn sẽ hiển thị dưới đây !";
				password=opDepart.get().getPassword();
				model.addAttribute("password", password);
			} else {
				message="Tên tài khoản không chính xác !";
			}
		}
		model.addAttribute("message", message);
		return "admin/qmk";
	}

	@RequestMapping("/")
	public String list(ModelMap model, HttpSession session) {
		model.addAttribute("userId", userId);
		session.setAttribute("username", userId);
		return "home";
	}

	@RequestMapping("/home")
	public String home(ModelMap model, HttpSession session) {
		model.addAttribute("userId", userId);
		session.setAttribute("username", userId);
		return "home";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "redirect:/";
	}

}
