package thhsu.chloe.jeeva.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chloe on 5/15/2018.
 */

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_pic")
    @Expose
    private Object profilePic;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("hosted_events_count")
    @Expose
    private String hostedEventsCount;
    @SerializedName("upcomming_events_count")
    @Expose
    private String upcommingEventsCount;
    @SerializedName("joined_events_count")
    @Expose
    private String joinedEventsCount;
    @SerializedName("work_experience")
    @Expose
    private List<Object> workExperience = null;
    @SerializedName("education")
    @Expose
    private List<Object> education = null;
    @SerializedName("interests")
    @Expose
    private List<Object> interests = null;
    @SerializedName("skills")
    @Expose
    private Object skills;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("linkedin_account")
    @Expose
    private String linkedinAccount;
    @SerializedName("facebook_account")
    @Expose
    private String facebookAccount;
    @SerializedName("github_account")
    @Expose
    private String githubAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Object profilePic) {
        this.profilePic = profilePic;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getHostedEventsCount() {
        return hostedEventsCount;
    }

    public void setHostedEventsCount(String hostedEventsCount) {
        this.hostedEventsCount = hostedEventsCount;
    }

    public String getUpcommingEventsCount() {
        return upcommingEventsCount;
    }

    public void setUpcommingEventsCount(String upcommingEventsCount) {
        this.upcommingEventsCount = upcommingEventsCount;
    }

    public String getJoinedEventsCount() {
        return joinedEventsCount;
    }

    public void setJoinedEventsCount(String joinedEventsCount) {
        this.joinedEventsCount = joinedEventsCount;
    }

    public List<Object> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<Object> workExperience) {
        this.workExperience = workExperience;
    }

    public List<Object> getEducation() {
        return education;
    }

    public void setEducation(List<Object> education) {
        this.education = education;
    }

    public List<Object> getInterests() {
        return interests;
    }

    public void setInterests(List<Object> interests) {
        this.interests = interests;
    }

    public Object getSkills() {
        return skills;
    }

    public void setSkills(Object skills) {
        this.skills = skills;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLinkedinAccount() {
        return linkedinAccount;
    }

    public void setLinkedinAccount(String linkedinAccount) {
        this.linkedinAccount = linkedinAccount;
    }

    public String getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(String facebookAccount) {
        this.facebookAccount = facebookAccount;
    }

    public String getGithubAccount() {
        return githubAccount;
    }

    public void setGithubAccount(String githubAccount) {
        this.githubAccount = githubAccount;
    }

}

