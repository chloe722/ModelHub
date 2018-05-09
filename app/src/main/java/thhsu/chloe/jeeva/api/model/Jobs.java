package thhsu.chloe.jeeva.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chloe on 5/7/2018.
 */

public class Jobs implements Serializable{
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
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("benefits")
    @Expose
    private List<Object> benefits = null;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("requirements")
    @Expose
    private String requirements;

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

    public List<Object> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Object> benefits) {
        this.benefits = benefits;
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

//
//    public Jobs(Parcel in) {
//        super();
//        readFromParcel(in);
//    }
//
//    public static final Parcelable.Creator<Jobs> CREATOR = new Parcelable.Creator<Jobs>() {
//        public Jobs createFromParcel(Parcel in) {
//            return new Jobs(in);
//        }
//
//        public Jobs[] newArray(int size) {
//
//            return new Jobs[size];
//        }
//
//    };
//
//    public void readFromParcel(Parcel in) {
//        Value1 = in.readInt();
//        Value2 = in.readInt();
//        Value3 = in.readInt();
//
//    }
//    public int describeContents() {
//        return 0;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(Value1);
//        dest.writeInt(Value2);
//        dest.writeInt(Value3);
//    }
}


