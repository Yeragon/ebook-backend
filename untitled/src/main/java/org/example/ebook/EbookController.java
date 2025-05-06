package org.example.ebook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api") // 对应前端请求的 /books
public class EbookController {

    private final EbookService ebookService;

    public EbookController(EbookService ebookService) {
        this.ebookService = ebookService;
    }

    @GetMapping("/books")
    public List<Ebook> getRandomBooks() {
        return ebookService.getRandomBooks();
    }

    @GetMapping("/ebook/{id}")
    public Ebook getById(@PathVariable UUID id) {
        Ebook ebook = ebookService.getById(id);
        if (ebook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ebook not found");
        }
        return ebookService.getById(id);
    }

    @GetMapping("/search/books")
    public List<Ebook> searchBooks(@RequestParam String query) {
        return ebookService.searchByKeyword(query);
    }


}
