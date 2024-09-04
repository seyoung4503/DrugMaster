package com.ll.drugmaster;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

public class DMexApplication {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://172.28.0.12:5000");

        // 파일 객체 생성
        File file = new File("/drugmaster/yolo_test/val1.png");
        FileSystemResource resource = new FileSystemResource(file);

        // POST 요청으로 파일 전송
        Mono<String> response = webClient.post()
                .uri("/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(resource)
                .retrieve()
                .bodyToMono(String.class);

//        // 응답 출력
//        response.subscribe(System.out::println);
//        System.out.println(response);
        // 응답을 비동기적으로 처리
        response.subscribe(result -> {
            System.out.println("Response received: " + result);
        }, error -> {
            System.err.println("Error occurred: " + error.getMessage());
        });

        // 메인 스레드가 즉시 종료되지 않도록 잠시 대기
        try {
            Thread.sleep(5000);  // 5초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
