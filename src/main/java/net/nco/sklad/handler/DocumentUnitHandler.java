package net.nco.sklad.handler;

import java.io.InputStream;

public interface DocumentUnitHandler {

    String getDocumentTitle();
    String getDocumentTitle(Integer documentTypeId);

    void addUnit(Long documentId, Long unitId);
}
