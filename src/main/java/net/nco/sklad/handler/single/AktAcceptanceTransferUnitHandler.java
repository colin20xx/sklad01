package net.nco.sklad.handler.single;

import jakarta.transaction.Transactional;
import net.nco.sklad.common.DocumentTypeShortTitle;
import net.nco.sklad.entity.documents.AktAcceptanceTransferUnits;
import net.nco.sklad.entity.documents.DocumentsType;
import net.nco.sklad.handler.DocumentUnitHandler;
import net.nco.sklad.repository.AktAcceptanceTransferRepository;
import net.nco.sklad.repository.AktAcceptanceTransferUnitsRepository;
import net.nco.sklad.repository.DocumentTypeRepository;
import net.nco.sklad.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AktAcceptanceTransferUnitHandler implements DocumentUnitHandler {

    private DocumentTypeRepository documentTypeRepo;
    private final AktAcceptanceTransferRepository appRepo;
    private final AktAcceptanceTransferUnitsRepository appUnitsRepo;
    private final UnitRepository unitRepository;

    public AktAcceptanceTransferUnitHandler(AktAcceptanceTransferRepository appRepo, AktAcceptanceTransferUnitsRepository appUnitsRepo, UnitRepository unitRepository, DocumentTypeRepository documentTypeRepo) {
        this.appRepo = appRepo;
        this.appUnitsRepo = appUnitsRepo;
        this.unitRepository = unitRepository;
        this.documentTypeRepo = documentTypeRepo;
    }
    @Override
    public String getDocumentTitle() {
        return DocumentTypeShortTitle.AKT_ACCEPTANCE_TRANSFER;
    }

    @Override
    public String getDocumentTitle(Integer documentTypeId) {
        return documentTypeRepo.findEnglishShortTitleById(documentTypeId);
    }

    @Override
    @Transactional
    public void addUnit(Long documentId, Long unitId) {
        AktAcceptanceTransferUnits aatu = new AktAcceptanceTransferUnits();
        aatu.setUnit(unitRepository.getReferenceById(unitId));
        aatu.setAktAcceptanceTransfer(appRepo.getReferenceById(documentId));
        aatu.setQuantity(BigDecimal.ONE);
        appUnitsRepo.save(aatu);
        System.out.println("[sklad]---> add unit to app units with app id and unit id");
    }
}
