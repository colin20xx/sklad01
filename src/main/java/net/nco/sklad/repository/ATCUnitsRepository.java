package net.nco.sklad.repository;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import net.nco.sklad.dto.ATCUnitDTO;
import net.nco.sklad.entity.documents.AktAcceptanceTransferUnits;
import net.nco.sklad.entity.documents.AktTechConditionUnits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ATCUnitsRepository extends JpaRepository<AktTechConditionUnits, Long> {
    List<AktTechConditionUnits> findAllByAtcId(Long id);

    @Query(value = "SELECT" +
            "    datcu.id," +
            "    'sn'," +
            "    datcu.category," +
            "    datcu.qty_bts," +
            "    datcu.qty_ps," +
            "    datcu.quantity," +
            "    datcu.price," +
            "    datcu.atc_id," +
            "    datcu.denomination_id," +
            "    datcu.nomenclature_id," +
            "    crd.id_card," +
            "    d.denomination_name," +
            "    n.nomenclature_name," +
            "    crd.card_number " +
            "FROM sklad.document_akt_tech_condition_units datcu " +
            "   LEFT JOIN sklad.denominations d ON datcu.denomination_id = d.id " +
            "   LEFT JOIN sklad.nomenclatures n ON datcu.nomenclature_id = n.id " +
            "   left join sklad.atcu_cards ac on datcu.id = ac.atcu_id " +
            "   left join sklad.cards crd on crd.id_card = ac.card_id " +
            "WHERE datcu.atc_id = :idATC and datcu.unit_id isnull;", nativeQuery = true)
    List<ATCUnitDTO> findAllByAtcID(Long idATC);

    @Query(value = """
            select datcu.id, u.serial_number, u.category, cast(0 as numeric), cast(0 as numeric), cast(1 as numeric), u.price, :atcId,
                   d.id, n.id, cast(1 as bigint), d.denomination_name, n.nomenclature_name, cast(0 as bigint)
            from sklad.document_akt_tech_condition_units datcu
                    left join sklad.units u on u.id = datcu.unit_id
                    left join sklad.denominations d on u.denomination_id = d.id
                    left join sklad.nomenclatures n on u.nomenclature_id = n.id
            where atc_id = :atcId and unit_id is not null;
""", nativeQuery = true)
    List<ATCUnitDTO> findAllNumericUnits(Long atcId);

    @Query(value = "select coalesce(sum(datcu.price), 0) from sklad.document_akt_tech_condition_units datcu where atc_id = :idATC", nativeQuery = true)
    BigDecimal sumUnitsPrice(Long idATC);

    @Query(value = "select coalesce(sum(datcu.quantity), 0) from sklad.document_akt_tech_condition_units datcu where atc_id = :idATC", nativeQuery = true)
    BigDecimal sumUnitsQuantity(Long idATC);

}//end interface
