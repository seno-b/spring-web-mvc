package me.senob.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @GetMapping("files")
    public String fileUploadForm() {
        return "files/index";
    }

    @PostMapping("files")
    public String fileUpload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = file.getOriginalFilename() + "is uploaded";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/files";
    }
}
