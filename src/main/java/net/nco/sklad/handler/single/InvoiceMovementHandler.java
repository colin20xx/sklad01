package net.nco.sklad.handler.single;

import net.nco.sklad.common.DocumentTypeShortTitle;
import net.nco.sklad.handler.DocumentUnitHandler;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMovementHandler implements DocumentUnitHandler {


    public InvoiceMovementHandler() {
    }

    @Override
    public String getDocumentTitle() {
        return DocumentTypeShortTitle.INVOICE;
    }

    @Override
    public String getDocumentTitle(Integer documentTypeId) {
        return "";
    }

    @Override
    public void addUnit(Long documentId, Long unitId) {
        System.out.println("[sklad]---> add unit to invoice units with invoice id:("+ documentId +") and unit id:" + unitId);
    }
}
