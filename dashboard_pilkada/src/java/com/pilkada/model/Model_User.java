/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.model;

/**
 *
 * @author syukur
 */
public class Model_User {

    private String id_candidate;
    private String candidate_name;
    private String img_profile;

    public String getId_candidate() {
        return id_candidate;
    }

    public void setId_candidate(String id_candidate) {
        this.id_candidate = id_candidate;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

}
