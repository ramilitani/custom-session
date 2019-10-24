package br.com.rmsystems.customsession.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SpringSessionAttributesId implements Serializable {

    @Column(name="SESSION_PRIMARY_ID")
    private String sessionPrimaryId;

    @Column(name="ATTRIBUTE_NAME")
    private String attributeName;

}
