package org.sinouplen.inc.mygesthand.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.sinouplen.inc.mygesthand.domain.util.CustomDateTimeDeserializer;
import org.sinouplen.inc.mygesthand.domain.util.CustomDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Contact.
 */
@Entity
@Table(name = "T_CONTACT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "birthday", nullable = false)
    private DateTime birthday;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birth_country")
    private String birth_country;

    @Column(name = "quality")
    private String quality;

    @Column(name = "age_range")
    private String age_range;

    @Column(name = "mutation_indicator")
    private String mutation_indicator;

    @Column(name = "nationality_nature")
    private String nationality_nature;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_of_entry", nullable = false)
    private DateTime date_of_entry;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_of_recipt", nullable = false)
    private DateTime date_of_recipt;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_of_expiry", nullable = false)
    private DateTime date_of_expiry;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_of_qualification", nullable = false)
    private DateTime date_of_qualification;

    @Column(name = "license_status")
    private String license_status;

    @Column(name = "type_of_application")
    private String type_of_application;

    @Column(name = "license_number")
    private String license_number;

    @Column(name = "sex")
    private String sex;

    @Column(name = "individual_accident_insurance")
    private Boolean individual_accident_insurance;

    @Column(name = "allows_trade_eamil")
    private Boolean allows_trade_eamil;

    @Column(name = "allows_address")
    private Boolean allows_address;

    @Column(name = "civility")
    private String civility;

    @Column(name = "married_name")
    private String married_name;

    @Column(name = "foreign_birth_city")
    private String foreign_birth_city;

    @Column(name = "french_birth_city")
    private String french_birth_city;

    @Column(name = "departement_birth")
    private String departement_birth;

    @Column(name = "address_1")
    private String address_1;

    @Column(name = "address_2")
    private String address_2;

    @Column(name = "address_3")
    private String address_3;

    @Column(name = "french_postal_code")
    private String french_postal_code;

    @Column(name = "french_city")
    private String french_city;

    @Column(name = "foreign_postal_code")
    private String foreign_postal_code;

    @Column(name = "foreign_city")
    private String foreign_city;

    @Column(name = "country")
    private String country;

    @Column(name = "home_phone_number")
    private String home_phone_number;

    @Column(name = "cellular_phone_number")
    private String cellular_phone_number;

    @Column(name = "work_phone_number")
    private String work_phone_number;

    @Column(name = "fax_number")
    private String fax_number;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "license_status_date", nullable = false)
    private DateTime license_status_date;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirth_country() {
        return birth_country;
    }

    public void setBirth_country(String birth_country) {
        this.birth_country = birth_country;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getAge_range() {
        return age_range;
    }

    public void setAge_range(String age_range) {
        this.age_range = age_range;
    }

    public String getMutation_indicator() {
        return mutation_indicator;
    }

    public void setMutation_indicator(String mutation_indicator) {
        this.mutation_indicator = mutation_indicator;
    }

    public String getNationality_nature() {
        return nationality_nature;
    }

    public void setNationality_nature(String nationality_nature) {
        this.nationality_nature = nationality_nature;
    }

    public DateTime getDate_of_entry() {
        return date_of_entry;
    }

    public void setDate_of_entry(DateTime date_of_entry) {
        this.date_of_entry = date_of_entry;
    }

    public DateTime getDate_of_recipt() {
        return date_of_recipt;
    }

    public void setDate_of_recipt(DateTime date_of_recipt) {
        this.date_of_recipt = date_of_recipt;
    }

    public DateTime getDate_of_expiry() {
        return date_of_expiry;
    }

    public void setDate_of_expiry(DateTime date_of_expiry) {
        this.date_of_expiry = date_of_expiry;
    }

    public DateTime getDate_of_qualification() {
        return date_of_qualification;
    }

    public void setDate_of_qualification(DateTime date_of_qualification) {
        this.date_of_qualification = date_of_qualification;
    }

    public String getLicense_status() {
        return license_status;
    }

    public void setLicense_status(String license_status) {
        this.license_status = license_status;
    }

    public String getType_of_application() {
        return type_of_application;
    }

    public void setType_of_application(String type_of_application) {
        this.type_of_application = type_of_application;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getIndividual_accident_insurance() {
        return individual_accident_insurance;
    }

    public void setIndividual_accident_insurance(Boolean individual_accident_insurance) {
        this.individual_accident_insurance = individual_accident_insurance;
    }

    public Boolean getAllows_trade_eamil() {
        return allows_trade_eamil;
    }

    public void setAllows_trade_eamil(Boolean allows_trade_eamil) {
        this.allows_trade_eamil = allows_trade_eamil;
    }

    public Boolean getAllows_address() {
        return allows_address;
    }

    public void setAllows_address(Boolean allows_address) {
        this.allows_address = allows_address;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getMarried_name() {
        return married_name;
    }

    public void setMarried_name(String married_name) {
        this.married_name = married_name;
    }

    public String getForeign_birth_city() {
        return foreign_birth_city;
    }

    public void setForeign_birth_city(String foreign_birth_city) {
        this.foreign_birth_city = foreign_birth_city;
    }

    public String getFrench_birth_city() {
        return french_birth_city;
    }

    public void setFrench_birth_city(String french_birth_city) {
        this.french_birth_city = french_birth_city;
    }

    public String getDepartement_birth() {
        return departement_birth;
    }

    public void setDepartement_birth(String departement_birth) {
        this.departement_birth = departement_birth;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getAddress_3() {
        return address_3;
    }

    public void setAddress_3(String address_3) {
        this.address_3 = address_3;
    }

    public String getFrench_postal_code() {
        return french_postal_code;
    }

    public void setFrench_postal_code(String french_postal_code) {
        this.french_postal_code = french_postal_code;
    }

    public String getFrench_city() {
        return french_city;
    }

    public void setFrench_city(String french_city) {
        this.french_city = french_city;
    }

    public String getForeign_postal_code() {
        return foreign_postal_code;
    }

    public void setForeign_postal_code(String foreign_postal_code) {
        this.foreign_postal_code = foreign_postal_code;
    }

    public String getForeign_city() {
        return foreign_city;
    }

    public void setForeign_city(String foreign_city) {
        this.foreign_city = foreign_city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHome_phone_number() {
        return home_phone_number;
    }

    public void setHome_phone_number(String home_phone_number) {
        this.home_phone_number = home_phone_number;
    }

    public String getCellular_phone_number() {
        return cellular_phone_number;
    }

    public void setCellular_phone_number(String cellular_phone_number) {
        this.cellular_phone_number = cellular_phone_number;
    }

    public String getWork_phone_number() {
        return work_phone_number;
    }

    public void setWork_phone_number(String work_phone_number) {
        this.work_phone_number = work_phone_number;
    }

    public String getFax_number() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public DateTime getLicense_status_date() {
        return license_status_date;
    }

    public void setLicense_status_date(DateTime license_status_date) {
        this.license_status_date = license_status_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", birthday='" + birthday + "'" +
            ", nationality='" + nationality + "'" +
            ", birth_country='" + birth_country + "'" +
            ", quality='" + quality + "'" +
            ", age_range='" + age_range + "'" +
            ", mutation_indicator='" + mutation_indicator + "'" +
            ", nationality_nature='" + nationality_nature + "'" +
            ", date_of_entry='" + date_of_entry + "'" +
            ", date_of_recipt='" + date_of_recipt + "'" +
            ", date_of_expiry='" + date_of_expiry + "'" +
            ", date_of_qualification='" + date_of_qualification + "'" +
            ", license_status='" + license_status + "'" +
            ", type_of_application='" + type_of_application + "'" +
            ", license_number='" + license_number + "'" +
            ", sex='" + sex + "'" +
            ", individual_accident_insurance='" + individual_accident_insurance + "'" +
            ", allows_trade_eamil='" + allows_trade_eamil + "'" +
            ", allows_address='" + allows_address + "'" +
            ", civility='" + civility + "'" +
            ", married_name='" + married_name + "'" +
            ", foreign_birth_city='" + foreign_birth_city + "'" +
            ", french_birth_city='" + french_birth_city + "'" +
            ", departement_birth='" + departement_birth + "'" +
            ", address_1='" + address_1 + "'" +
            ", address_2='" + address_2 + "'" +
            ", address_3='" + address_3 + "'" +
            ", french_postal_code='" + french_postal_code + "'" +
            ", french_city='" + french_city + "'" +
            ", foreign_postal_code='" + foreign_postal_code + "'" +
            ", foreign_city='" + foreign_city + "'" +
            ", country='" + country + "'" +
            ", home_phone_number='" + home_phone_number + "'" +
            ", cellular_phone_number='" + cellular_phone_number + "'" +
            ", work_phone_number='" + work_phone_number + "'" +
            ", fax_number='" + fax_number + "'" +
            ", license_status_date='" + license_status_date + "'" +
            '}';
    }
}
