package thhsu.chloe.ModelHub.api.model;

/**
 * Created by Chloe on 5/31/2018.
 */

public class LanguageSkill {
    String language;
    String level;

    public LanguageSkill(String language, String level) {
        this.language = language;
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.getLanguage() + " " + this.getLevel();
    }
}
