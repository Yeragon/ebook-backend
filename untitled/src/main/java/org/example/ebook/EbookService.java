package org.example.ebook;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EbookService {

    private final EbookMapper ebookMapper;

    public EbookService(EbookMapper ebookMapper) {
        this.ebookMapper = ebookMapper;
    }

    public List<Ebook> getRandomBooks() {
        return ebookMapper.findRandomFour();
    }

    public Ebook getById(UUID id) {
        return ebookMapper.findById(id);
    }

    public List<Ebook> searchByKeyword(String keyword) {
        return ebookMapper.searchByKeyword(keyword);
    }
}
