package com.stack.puzzle.domain.book.service;

import com.stack.puzzle.domain.book.dto.BookResponse;
import com.stack.puzzle.domain.book.dto.NaverBooksDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class BookService {

    @Value("${config.naver.client-id}")
    private String naverClientId;

    @Value("${config.naver.client-secret}")
    private String naverClientSecret;
    public List<BookResponse> getNaverBooks(String emotion) {
        return getBooks(naverApi(emotion));
    }

    private NaverBooksDto naverApi(String emotion) {
        // 외부 API 호출 해오기 위함
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        // naver header 및 url 넣기
        String naverUrl = "https://openapi.naver.com/v1/search/book.json";
        headers.add("X-Naver-Client-Id", naverClientId);
        headers.add("X-Naver-Client-Secret", naverClientSecret);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromUriString(naverUrl)
                .queryParam("display", "100")
                .queryParam("query", emotion)
                .encode()
                .build()
                .toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, NaverBooksDto.class).getBody();
    }

    /**
     * 필요한 데이터만 내보내기 위함
     * @param naverBooksDto
     * @return
     */
    private List<BookResponse> getBooks(NaverBooksDto naverBooksDto) {
        return naverBooksDto.getItems().stream()
                .map(a -> BookResponse.builder()
                        .title(a.getTitle())
                        .author(a.getAuthor())
                        .link(a.getLink())
                        .image(a.getImage())
                        .description(a.getDescription())
                        .build())
                .toList();
    }
}
