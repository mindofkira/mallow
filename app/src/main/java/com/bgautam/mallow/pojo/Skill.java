
package com.bgautam.mallow.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Skill  implements Serializable{

    @SerializedName("technical")
    @Expose
    private List<String> technical = null;
    @SerializedName("extra_curricular")
    @Expose
    private List<String> extraCurricular = null;

    public List<String> getTechnical() {
        return technical;
    }

    public void setTechnical(List<String> technical) {
        this.technical = technical;
    }

    public Skill withTechnical(List<String> technical) {
        this.technical = technical;
        return this;
    }

    public List<String> getExtraCurricular() {
        return extraCurricular;
    }

    public void setExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
    }

    public Skill withExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
        return this;
    }

}
