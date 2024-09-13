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
public class Model_Active {

    private String id_votingplace_active;
    private String id_subdistrict;
    private String subdistrict_name;
    private String id_votingplace;
    private String votingplace_name;
    private String total_voter;

    public String getTotal_voter() {
        return total_voter;
    }

    public void setTotal_voter(String total_voter) {
        this.total_voter = total_voter;
    }

    public String getId_votingplace_active() {
        return id_votingplace_active;
    }

    public void setId_votingplace_active(String id_votingplace_active) {
        this.id_votingplace_active = id_votingplace_active;
    }

    public String getId_subdistrict() {
        return id_subdistrict;
    }

    public void setId_subdistrict(String id_subdistrict) {
        this.id_subdistrict = id_subdistrict;
    }

    public String getSubdistrict_name() {
        return subdistrict_name;
    }

    public void setSubdistrict_name(String subdistrict_name) {
        this.subdistrict_name = subdistrict_name;
    }

    public String getId_votingplace() {
        return id_votingplace;
    }

    public void setId_votingplace(String id_votingplace) {
        this.id_votingplace = id_votingplace;
    }

    public String getVotingplace_name() {
        return votingplace_name;
    }

    public void setVotingplace_name(String votingplace_name) {
        this.votingplace_name = votingplace_name;
    }


}
