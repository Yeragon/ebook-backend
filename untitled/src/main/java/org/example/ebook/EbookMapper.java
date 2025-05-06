package org.example.ebook;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;
@Mapper
public interface EbookMapper {
    Ebook findById(UUID id);
    List<Ebook> findRandomFour();
    void insertEbook(Ebook ebook);
    List<Ebook> searchByKeyword(String keyword);
    Integer getStockByEbookId(UUID ebookId);
    void decreaseStockById(@Param("id") UUID ebookId);

}
