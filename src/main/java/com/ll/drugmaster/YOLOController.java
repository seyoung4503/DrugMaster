package com.ll.drugmaster;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class YOLOController {

    @PostMapping("/run-yolo")
    public String runYOLO(@RequestParam String imagePath, Model model) {
        StringBuilder output = new StringBuilder();

        try {
            // 파이썬 스크립트를 실행하는 명령어
            String[] command = new String[] {
                    "python", "yolo_detect.py", imagePath
            };

            // 파이썬 스크립트 실행
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 파이썬 스크립트의 출력 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // 프로세스가 종료될 때까지 기다리기
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                model.addAttribute("status", "success");
                model.addAttribute("result", output.toString());  // 파이썬 스크립트 출력
            } else {
                model.addAttribute("status", "fail");
                model.addAttribute("error", "Process exited with code " + exitCode);
            }

        } catch (Exception e) {
            model.addAttribute("status", "error");
            model.addAttribute("message", e.getMessage());
        }

        return "yoloResult"; // yoloResult.html 혹은 JSP 페이지로 이동
    }
}
