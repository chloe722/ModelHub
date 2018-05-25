package thhsu.chloe.ModelHub.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chloe on 5/7/2018.
 */

public class Cases implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("urgent")
    @Expose
    private Boolean urgent;
    @SerializedName("recommended")
    @Expose
    private Boolean recommended;
    @SerializedName("date_posted")
    @Expose
    private String datePosted;
    @SerializedName("date_expires")
    @Expose
    private String dateExpires;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    @SerializedName("benefits")
    @Expose
    private List<String> benefits = null;

    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("requirements")
    @Expose
    private String requirements;
    @SerializedName("hiring_source_from")
    @Expose
    private String hiring_source_from;
    @SerializedName("hiring_contact_name")
    @Expose
    private String hiring_contact_name;
    @SerializedName("hiring_contact_email")
    @Expose
    private String hiring_contact_email;
    @SerializedName("hiring_other_info")
    @Expose
    private String hiring_other_info;

    private boolean mIsSaved = false;

    public String getHiring_contact_name() {
        return hiring_contact_name;
    }

    public void setHiring_contact_name(String hiring_contact_name) {
        this.hiring_contact_name = hiring_contact_name;
    }

    public String getHiring_contact_email() {
        return hiring_contact_email;
    }

    public void setHiring_contact_email(String hiring_contact_email) {
        this.hiring_contact_email = hiring_contact_email;
    }

    public String getHiring_other_info() {
        return hiring_other_info;
    }

    public void setHiring_other_info(String hiring_other_info) {
        this.hiring_other_info = hiring_other_info;
    }

    public String getHiring_source_from() {
        return hiring_source_from;
    }

    public void setHiring_source_from(String hiring_source_from) {
        this.hiring_source_from = hiring_source_from;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(String dateExpires) {
        this.dateExpires = dateExpires;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getBenefits() {
        if(benefits != null)
            return benefits;
        else
            return new ArrayList<String>();
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }

    public void setBenefits(String[] benefits) {
        this.benefits = Arrays.asList(benefits);
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSaved(){return mIsSaved;}

    public void setSaved(boolean isSaved){
        mIsSaved = isSaved;
    }
}


