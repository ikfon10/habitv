//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.24 at 09:12:23 AM CEST 
//


package com.dabi.habitv.provider.canalplus.video.entities;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}PAVE"/>
 *         &lt;element ref="{}BILLBOARD"/>
 *         &lt;element ref="{}HABILLAGE"/>
 *         &lt;element ref="{}PAGEID"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pave",
    "billboard",
    "habillage",
    "pageid"
})
@XmlRootElement(name = "PUB")
public class PUB {

    @XmlElement(name = "PAVE", required = true)
    protected PAVE pave;
    @XmlElement(name = "BILLBOARD", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String billboard;
    @XmlElement(name = "HABILLAGE", required = true)
    protected HABILLAGE habillage;
    @XmlElement(name = "PAGEID", required = true)
    protected BigInteger pageid;

    /**
     * Gets the value of the pave property.
     * 
     * @return
     *     possible object is
     *     {@link PAVE }
     *     
     */
    public PAVE getPAVE() {
        return pave;
    }

    /**
     * Sets the value of the pave property.
     * 
     * @param value
     *     allowed object is
     *     {@link PAVE }
     *     
     */
    public void setPAVE(PAVE value) {
        this.pave = value;
    }

    /**
     * Gets the value of the billboard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBILLBOARD() {
        return billboard;
    }

    /**
     * Sets the value of the billboard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBILLBOARD(String value) {
        this.billboard = value;
    }

    /**
     * Gets the value of the habillage property.
     * 
     * @return
     *     possible object is
     *     {@link HABILLAGE }
     *     
     */
    public HABILLAGE getHABILLAGE() {
        return habillage;
    }

    /**
     * Sets the value of the habillage property.
     * 
     * @param value
     *     allowed object is
     *     {@link HABILLAGE }
     *     
     */
    public void setHABILLAGE(HABILLAGE value) {
        this.habillage = value;
    }

    /**
     * Gets the value of the pageid property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPAGEID() {
        return pageid;
    }

    /**
     * Sets the value of the pageid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPAGEID(BigInteger value) {
        this.pageid = value;
    }

}
