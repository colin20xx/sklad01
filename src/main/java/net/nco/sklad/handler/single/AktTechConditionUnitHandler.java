package net.nco.sklad.handler.single;

import jakarta.transaction.Transactional;
import net.nco.sklad.common.DocumentTypeShortTitle;
import net.nco.sklad.entity.documents.AktTechConditionUnits;
import net.nco.sklad.handler.DocumentUnitHandler;
import net.nco.sklad.repository.ATCUnitsRepository;
import net.nco.sklad.repository.AktTechConditionRepository;
import net.nco.sklad.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AktTechConditionUnitHandler implements DocumentUnitHandler {

    private final UnitRepository unitRepository;
    private final AktTechConditionRepository atcRepo;
    private final ATCUnitsRepository atcUnitsRepo;

    public AktTechConditionUnitHandler(UnitRepository unitRepository, AktTechConditionRepository aktTechConditionRepository, ATCUnitsRepository atcUnitsRepo) {
        this.unitRepository = unitRepository;
        this.atcRepo = aktTechConditionRepository;
        this.atcUnitsRepo = atcUnitsRepo;
    }
    @Override
    public String getDocumentTitle() {
        return DocumentTypeShortTitle.AKT_TECH_CONDITION;
    }

    @Override
    public String getDocumentTitle(Integer documentTypeId) {
        return "";
    }

    @Override
    @Transactional
    public void addUnit(Long documentId, Long unitId) {
        AktTechConditionUnits atcUnits = new AktTechConditionUnits();
        atcUnits.setQuantity(BigDecimal.ONE);
        atcUnits.setUnit(unitRepository.getReferenceById(unitId));
        atcUnits.setAtc(atcRepo.getReferenceById(documentId));
        atcUnitsRepo.save(atcUnits);
        System.out.println("[sklad]---> add unit to atc units with atc id and unit id");
    }
}
