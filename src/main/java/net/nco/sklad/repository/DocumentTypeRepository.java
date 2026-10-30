package net.nco.sklad.repository;

import net.nco.sklad.entity.documents.DocumentsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentTypeRepository extends JpaRepository<DocumentsType, Integer> {

    DocumentsType findIdByTitle(String title);

    DocumentsType findById(int id);

    @Query("select dt.englishShortTitle from DocumentsType dt where dt.id = :id")
    String findEnglishShortTitleById(Integer id);


}
