package com.ll.drugmaster.counsel;

import com.ll.drugmaster.member.Member;
import com.ll.drugmaster.member.MemberService;
import com.ll.drugmaster.phamacistOpinion.PhamacistOpinionForm;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class CounselController {

    private final CounselService counselService;
    private final MemberService memberService;

    // todo : 추후 member의 id를 사용해 검색하기로 바꾸기
    @GetMapping("/counsel/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Principal principal) {

        String memberName = principal.getName();
        Page<Counsel> paging = this.counselService.getListPageByMember(page, memberName);
//        Page<Counsel> paging = this.counselService.getListPage(page);
        model.addAttribute("paging", paging);
        return "counsel_list";
    }

    @GetMapping("/counsel/phar/list")
    public String pharList(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Principal principal) {

        // 현재 로그인한 사용자의 정보를 받아오기 (principal.getName()으로 사용자의 이름 가져오기)
        String memberName = principal.getName();

        // 로그인한 사용자의 정보를 서비스 또는 리포지토리에서 조회
        Member member = this.memberService.findMemberByMemberName(memberName);

        // isPharmacist가 true인지 확인
        if (member.getIsPhamacist()) {
            // 약사일 경우에만 리스트를 반환
            Page<Counsel> paging = this.counselService.getListPage(page);
            model.addAttribute("paging", paging);
            return "counsel_list";
        } else {
            // 약사가 아닐 경우 접근 금지 페이지로 리다이렉트
            return "redirect:/";
        }
    }

    @GetMapping("/counsel/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, PhamacistOpinionForm phamacistOpinionForm) {
        Counsel counsel = this.counselService.getCounsel(id);
        model.addAttribute("counsel", counsel);
        return "counsel_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/counsel/create")
    public String counselCreate(CounselForm counselForm) {
        return "counselCreate_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/counsel/create")
    @Transactional
    public String counselCreate(@Valid CounselForm counselForm, BindingResult bindingResult,
                                @RequestParam("image") MultipartFile image, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "counsel_form";
        }
        Member member = this.memberService.getUser(principal.getName());

        String imageUrl = null;
        if (!image.isEmpty()) {
            try {
                // Save the image and get the URL (this is a placeholder; implement actual logic)
                imageUrl = saveImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Image upload failed.");
                return "redirect:/counsel/create";
            }
        }

        this.counselService.create(counselForm.getSubject(), counselForm.getContent(), member, imageUrl);
        // todo : imageUrl을 통해 이미지 분석하고 memberdrug 테이블에 넣기

        System.out.println("URL"+ imageUrl);
        // Execute Python script
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "yolo_detect.py", imageUrl);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            System.out.println("yolo 완료");

            // Read the output of the Python script
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Print or process the output as needed

                }
            }

            System.out.println("yolo 완료");

            process.waitFor(); // Wait for the script to finish
            System.out.println("yolo 완료");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error while running image analysis.");
            return "redirect:/counsel/create";
        }
        System.out.println("yolo 완료");
        return "redirect:/counsel/list";

//

    }

    private final Path rootLocation = Paths.get("uploaded-images");

    private String saveImage(MultipartFile image) throws IOException {

        // 디렉토리 생성
        Files.createDirectories(rootLocation);

        // 파일 이름과 경로 설정
        String filename = image.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();

        // 파일 저장
        try (var inputStream = image.getInputStream()) {
            Files.copy(inputStream, destinationFile);
        }

        // 저장된 파일의 URL 반환 (파일 시스템에서 접근 가능한 경로)
        return "/uploaded-images/" + filename;
    }
}
