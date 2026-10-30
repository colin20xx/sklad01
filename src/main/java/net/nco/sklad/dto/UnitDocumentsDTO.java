package net.nco.sklad.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UnitDocumentsDTO {
    private Long id;
    private String title;
    private String docNumber;
    private Timestamp docDate;
    private String docCommitedAt;
    private String sender;
    private String receiver;
    private Integer category;
    private BigDecimal price;
    private String comments;

    public UnitDocumentsDTO(Long id, String title, String docNumber, Timestamp docDate, String docCommitedAt, String sender, String receiver, Integer category, BigDecimal price, String comments) {
        this.id = id;
        this.title = title;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.docCommitedAt = docCommitedAt;
        this.sender = sender;
        this.receiver = receiver;
        this.category = category;
        this.price = price;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Timestamp getDocDate() {
        return docDate;
    }

    public void setDocDate(Timestamp docDate) {
        this.docDate = docDate;
    }

    public String getDocCommitedAt() {
        return docCommitedAt;
    }

    public void setDocCommitedAt(String docCommitedAt) {
        this.docCommitedAt = docCommitedAt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}//end class
