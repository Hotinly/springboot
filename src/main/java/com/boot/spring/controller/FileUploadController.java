package com.boot.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	private HttpSession session2;

	@RequestMapping("/file")
	public String uploadFile() {
		session2.setAttribute("user", "HOLY");
		session2.setAttribute("describe", "Session is set by [FileUploadController.java], page can fetch it by [${session.KEY}]");
		return "uploadFile";
	}

	@Autowired
	private Environment env;
	// private String path = env.getProperty("user.file.uploadDir");	NULL

	@Value("${user.name}")
	private String userName;

	@RequestMapping(value = "/single", method = RequestMethod.POST)
	// public @ResponseBody String single(@RequestParam("name") MultipartFile file, HttpServletRequest req) {
	public @ResponseBody String single(MultipartFile file, HttpServletRequest req) {
		// <input type="file" name="file" /> | name="file" |
		// MultipartFile file2 = ((MultipartHttpServletRequest)req).getFile("file");
		// System.out.println(file2.getOriginalFilename());
		String path = env.getProperty("user.file.uploadDir");
		if (!file.isEmpty()) {
			try {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(new File(path, file.getOriginalFilename())));
				bos.write(file.getBytes());
				bos.flush();
				bos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "File upload success";
		} else {
			return "File is null";
		}
	}

	@PostMapping("/multiple")
	public String multipleFile(HttpServletRequest req, RedirectAttributes redirectAttributes) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file2");
		String path = env.getProperty("user.file.uploadDir");
		if (files.get(0).getSize() != 0) {
			BufferedOutputStream bos = null;
			for (MultipartFile file : files) {
				if(!file.isEmpty()){
					try {
						bos = new BufferedOutputStream(new FileOutputStream(new File(path, file.getOriginalFilename())));
						bos.write(file.getBytes());
						bos.flush();
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			redirectAttributes.addFlashAttribute("message", "File upload success");
			return "redirect:uploadResult";
		} else {
			redirectAttributes.addAttribute("userName", userName).addFlashAttribute("message", "Please select file to upload");
			return "redirect:uploadResult";
		}
	}

	@GetMapping("/uploadResult")
	public String uploadStatus(Map<String, String> map){ //Add and set params, views can fetch this
		map.put("userName", userName);
		return "uploadStatus";
	}
}
