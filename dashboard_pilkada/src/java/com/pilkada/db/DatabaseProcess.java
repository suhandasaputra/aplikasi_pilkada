/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilkada.db;

import com.pilkada.model.Model_Active;
import com.pilkada.model.Model_Bank;
import com.pilkada.model.Model_Conn;
import com.pilkada.model.Model_Product;
import com.pilkada.model.Model_Report;
import com.pilkada.model.Model_User;
import com.pilkada.model.Opt_distict;
import com.pilkada.model.Opt_subdistict;
import com.pilkada.model.Opt_tps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseProcess {

    private void clearStatment(PreparedStatement stat) {
        if (stat != null) {
            try {
                stat.clearBatch();
                stat.clearParameters();
                stat.close();
                stat = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

    //proses login
    public boolean validate(String user, String password) throws SQLException {
        boolean status = false;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT * FROM db_user WHERE user_id=? AND password=?");
            stat.setString(1, user);
            stat.setString(2, password);
            rs = stat.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Product> getAllProduct() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Product> listProduct = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("Select * from m_subdistrict");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Product reportyabes = new Model_Product();
                reportyabes.setId_subdistrict(rs.getString("id_subdistrict"));
                reportyabes.setSubdistrict_name(rs.getString("subdistrict_name"));
                reportyabes.setDistrict(rs.getString("district"));
                listProduct.add(reportyabes);
            }
        } catch (SQLException e) {
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listProduct;
    }

    public String addProduct(Model_Product pro) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            boolean status1 = false;
            stat = conn.prepareStatement("select * from m_subdistrict where id_subdistrict=?");
            stat.setString(1, pro.getId_subdistrict());
            rs = stat.executeQuery();
            status1 = rs.next();
            if (status1 == true) {
                return status = "01";
            } else {
                stat = conn.prepareStatement("INSERT INTO m_subdistrict(id_subdistrict, subdistrict_name, district) VALUES (?, ?, ?)");
                stat.setString(1, pro.getId_subdistrict());
                stat.setString(2, pro.getSubdistrict_name());
                stat.setString(3, pro.getDistrict());
                stat.executeUpdate();
            }
        } catch (SQLException e) {
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }

    public String deleteproduct(String id_subdistrict_edit) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from m_subdistrict where id_subdistrict=?");
            stat.setString(1, id_subdistrict_edit);
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateProduk(String id_subdistrict, String subdistrict_name, String district) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE m_subdistrict SET subdistrict_name=?, district=? WHERE id_subdistrict=?");
            stat.setString(1, subdistrict_name);
            stat.setString(2, district);
            stat.setString(3, id_subdistrict);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Report> getAllTrx() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Report> listTrx = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from votingplace_active");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Report reportyabes = new Model_Report();
                reportyabes.setId_votingplace_active(rs.getString("id_votingplace_active"));
                reportyabes.setId_subdistrict(rs.getString("id_subdistrict"));
                reportyabes.setId_votingplace(rs.getString("id_votingplace"));
                reportyabes.setTotal_voter(rs.getString("total_voter"));
                reportyabes.setVoter_attend(rs.getString("voter_attend"));
                reportyabes.setVoter_not_present(rs.getString("voter_not_present"));
                reportyabes.setValid_vote(rs.getString("valid_vote"));
                reportyabes.setInvalid_vote(rs.getString("invalid_vote"));
                reportyabes.setImg_vote(rs.getString("img_vote"));
                reportyabes.setTotal_ballots(rs.getString("total_ballots"));
                reportyabes.setSurveyor(rs.getString("surveyor"));
                reportyabes.setPaslon01(rs.getString("paslon01"));
                reportyabes.setPaslon02(rs.getString("paslon02"));
                reportyabes.setPaslon03(rs.getString("paslon03"));
                listTrx.add(reportyabes);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listTrx;
    }

    public ArrayList<Model_Bank> getAllVolunteer() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Bank> listBank = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("Select * from m_user");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Bank mb = new Model_Bank();
                mb.setUser_id(rs.getString("user_id"));
                mb.setName(rs.getString("name"));
                mb.setPhone_number(rs.getString("phone_number"));
//                mb.setPassword(rs.getString("password"));
                mb.setDistrict(rs.getString("district"));
                mb.setAddress(rs.getString("address"));
                mb.setSubdistrict(rs.getString("subdistrict"));
                mb.setCreate_date(rs.getString("create_date"));
                mb.setCity(rs.getString("city"));
                mb.setId_votingplace(rs.getString("id_votingplace"));
                mb.setId_subdistrict(rs.getString("id_subdistrict"));
                listBank.add(mb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listBank;
    }

//    public String addBank(Model_Bank pro) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        String status;
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            boolean status1 = false;
//            stat = conn.prepareStatement("select * from listbank where bankcode=?");
//            stat.setString(1, pro.getBankcode());
//            rs = stat.executeQuery();
//            status1 = rs.next();
//            if (status1 == true) {
//                return status = "01";
//            } else {
//                stat = conn.prepareStatement("INSERT INTO listbank(bankcode, bankname) VALUES (?, ?)");
//                stat.setString(1, pro.getBankcode());
//                stat.setString(2, pro.getBankname());
//                stat.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return status = e.getMessage();
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return status = "00";
//    }
//
//    public String deletebank(String bankcode) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        String status = "01";
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            stat = conn.prepareStatement("delete from listbank where bankcode=?");
//            stat.setString(1, bankcode);
//            stat.executeUpdate();
//            status = "00";
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return status;
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return status;
//    }
//
//    public String updateBank(String bankcode, String bankname) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        String status = "01";
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            stat = conn.prepareStatement("UPDATE listbank SET bankname=? WHERE bankcode=?");
//            stat.setString(1, bankname);
//            stat.setString(2, bankcode);
//            stat.executeUpdate();
//            status = "00";
//            stat.clearParameters();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return status = e.getMessage();
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return status;
//    }
    public ArrayList<Model_User> getAllUser() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_User> listUser = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from m_candidate");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_User mu = new Model_User();
                mu.setId_candidate(rs.getString("id_candidate"));
                mu.setCandidate_name(rs.getString("candidate_name"));
                mu.setImg_profile(rs.getString("img_profile"));
                listUser.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listUser;
    }

//    public String addCandidate(Model_User pro) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        String status;
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            boolean status1 = false;
//            stat = conn.prepareStatement("select * from m_candidate where id_candidate=?");
//            stat.setString(1, pro.getId_candidate());
//            rs = stat.executeQuery();
//            status1 = rs.next();
//            if (status1 == true) {
//                return status = "01";
//            } else {
////                stat = conn.prepareStatement("INSERT INTO m_candidate(id_candidate, candidate_name, img_profile) VALUES (?, ?, ?)");
////                stat.setString(1, pro.getId_candidate());
////                stat.setString(2, pro.getCandidate_name());
////                stat.setString(3, pro.getImg_profile());
//                stat.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return status = e.getMessage();
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return status = "00";
//    }
    public String deleteuser(String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from loginpanel where bankcode=?");
            stat.setString(1, bankcode);
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateUser(String bankcode, String username, String statususer) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE loginpanel SET username=?, statususer=? WHERE bankcode=?");
            stat.setString(1, username);
            stat.setString(2, statususer);
            stat.setString(3, bankcode);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Model_Conn> getAllConn() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Conn> listConn = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from m_votingplace");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Conn mu = new Model_Conn();
                mu.setId_votingplace(rs.getString("id_votingplace"));
                mu.setVotingplace_name(rs.getString("votingplace_name"));

                listConn.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listConn;
    }

    public String deleteconn(String seq, String todir, String bankcode) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from socketconn where todirect=? and seq=?");
            stat.setString(1, todir);
            stat.setInt(2, Integer.valueOf(seq));
            stat.executeUpdate();
            stat.clearParameters();
            stat.clearBatch();
            stat = conn.prepareStatement("DROP TABLE opt_iso_table_" + bankcode + ", opt_200_" + bankcode + ", opt_400_" + bankcode + ", opt_800_" + bankcode + "");
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateConn(String seq, String todirect, String host, String port, String statusopen,
            String headertype, String bankcode, String lengthincl, String typeapp, String conname, String packagename,
            String autosignon, String needsignon, String packagerpath, String loadbalancing, String lbgroup) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE socketconn SET host=?, port=?, statusopen=?, headertype=?, bankcode=?, lengthincl=?, typeapp=?, conname=?, packagename=?, "
                    + "autosignon=?, needsignon=?, packagerpath=?, loadbalancing=?, lbgroup=? WHERE seq=? and todirect=?");
            stat.setString(1, host);
            stat.setInt(2, Integer.valueOf(port));
//            stat.setString(3, statusopen);
            if (statusopen.equals("1")) {
                stat.setBoolean(3, true);
            } else if (statusopen.equals("0")) {
                stat.setBoolean(3, false);
            }
            stat.setInt(4, Integer.valueOf(headertype));
            stat.setString(5, bankcode);
//            stat.setString(6, lengthincl);
            if (lengthincl.equals("1")) {
                stat.setBoolean(6, true);
            } else if (lengthincl.equals("0")) {
                stat.setBoolean(6, false);
            }
            stat.setString(7, typeapp);
            stat.setString(8, conname);
            stat.setString(9, packagename);
//            stat.setString(10, autosignon);
            if (autosignon.equals("1")) {
                stat.setBoolean(10, true);
            } else if (autosignon.equals("0")) {
                stat.setBoolean(10, false);
            }
//            stat.setString(11, needsignon);
            if (needsignon.equals("1")) {
                stat.setBoolean(11, true);
            } else if (needsignon.equals("0")) {
                stat.setBoolean(11, false);
            }
            stat.setString(12, packagerpath);
//            stat.setString(13, loadbalancing);
            if (loadbalancing.equals("1")) {
                stat.setBoolean(13, true);
            } else if (loadbalancing.equals("0")) {
                stat.setBoolean(13, false);
            }
            stat.setString(14, lbgroup);
            stat.setInt(15, Integer.valueOf(seq));
            stat.setString(16, todirect);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public ArrayList<Opt_distict> getAlldistrict() {
        ArrayList<Opt_distict> listdistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select distinct(district) from m_subdistrict");
            rs = stat.executeQuery();
            while (rs.next()) {
                Opt_distict dustrict = new Opt_distict();
                dustrict.setDistrict(rs.getString("district"));
                listdistrict.add(dustrict);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listdistrict;
    }

    public ArrayList<Opt_subdistict> getAllsubdistrict(String district) {
        ArrayList<Opt_subdistict> listdistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select id_subdistrict, subdistrict_name from m_subdistrict where district = ?");
            stat.setString(1, district);
            rs = stat.executeQuery();
            while (rs.next()) {
                Opt_subdistict dustrict = new Opt_subdistict();
                dustrict.setId_subdistrict(rs.getString("id_subdistrict"));
                dustrict.setSubdistrict_name(rs.getString("subdistrict_name"));
                listdistrict.add(dustrict);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listdistrict;
    }
    
    public ArrayList<Opt_subdistict> getAllsubdistrictAct() {
        ArrayList<Opt_subdistict> listdistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select id_subdistrict, subdistrict_name from m_subdistrict");
            rs = stat.executeQuery();
            while (rs.next()) {
                Opt_subdistict dustrict = new Opt_subdistict();
                dustrict.setId_subdistrict(rs.getString("id_subdistrict"));
                dustrict.setSubdistrict_name(rs.getString("subdistrict_name"));
                listdistrict.add(dustrict);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listdistrict;
    }

    public ArrayList<Opt_tps> getAllTps(String subdis) {
        ArrayList<Opt_tps> listdistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        PreparedStatement stat1 = null;
        ResultSet rs1 = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select id_votingplace from votingplace_active where id_subdistrict = ?");
            stat.setString(1, subdis);
            rs = stat.executeQuery();
            while (rs.next()) {
                stat1 = conn.prepareStatement("select votingplace_name from m_votingplace where id_votingplace = ?");
                stat1.setString(1, rs.getString("id_votingplace"));
                rs1 = stat1.executeQuery();
                while (rs1.next()) {
                    Opt_tps dustrict = new Opt_tps();
                    dustrict.setId_votingplace(rs.getString("id_votingplace"));
                    dustrict.setVotingplace_name(rs1.getString("votingplace_name"));
                    listdistrict.add(dustrict);
                }

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
            clearAllConnStatRS(conn, stat1, rs1);
        }
        return listdistrict;
    }
    
    public ArrayList<Opt_tps> getAllTpsAct() {
        ArrayList<Opt_tps> listdistrict = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select id_votingplace, votingplace_name from m_votingplace");
            rs = stat.executeQuery();
            while (rs.next()) {
                    Opt_tps dustrict = new Opt_tps();
                    dustrict.setId_votingplace(rs.getString("id_votingplace"));
                    dustrict.setVotingplace_name(rs.getString("votingplace_name"));
                    listdistrict.add(dustrict);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listdistrict;
    }

    public String addVotingplace(Model_Conn pro) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            boolean status1 = false;
            stat = conn.prepareStatement("select * from m_votingplace where id_votingplace=?");
            stat.setString(1, pro.getId_votingplace());
            rs = stat.executeQuery();
            status1 = rs.next();
            if (status1 == true) {
                return status = "01";
            } else {
                stat = conn.prepareStatement("INSERT INTO m_votingplace(id_votingplace, votingplace_name) VALUES (?, ?)");
                stat.setString(1, pro.getId_votingplace());
                stat.setString(2, pro.getVotingplace_name());
                stat.executeUpdate();
            }
        } catch (SQLException e) {
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }

    public String updateVotingplace(String id_votingplace, String votingplace_name) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE m_votingplace SET votingplace_name=? WHERE id_votingplace=?");
            stat.setString(1, votingplace_name);
            stat.setString(2, id_votingplace);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String deletevotingplace(String id_votingplace_edit) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from m_votingplace where id_votingplace=?");
            stat.setString(1, id_votingplace_edit);
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public HashMap getgrap() {
        HashMap result = new HashMap();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        result.put("resp_code", "0001");
        result.put("resp_desc", "Failed");
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select sum(paslon01::integer) as paslon01, sum(paslon02::integer) as paslon02, sum(paslon03::integer) as paslon03, sum(invalid_vote::integer) as invalid from votingplace_active");
            rs = stat.executeQuery();
            while (rs.next()) {
                ArrayList value = new ArrayList();
                ArrayList nama = new ArrayList();
                value.add(Integer.valueOf(rs.getString("paslon01")));
                value.add(Integer.valueOf(rs.getString("paslon02")));
                value.add(Integer.valueOf(rs.getString("paslon03")));
                value.add(Integer.valueOf(rs.getString("invalid")));

                nama.add("paslon01");
                nama.add("paslon02");
                nama.add("paslon03");
                nama.add("invalid");

                result.put("perolehan", value);
                result.put("paslon", nama);
                result.put("resp_code", "0000");
                result.put("resp_desc", "Success");
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            return result;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return result;
    }

    public ArrayList<Model_Active> getAllActive() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Active> listUser = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT  a.id_votingplace, a.votingplace_name, b.id_votingplace_active, b.total_voter, c.id_subdistrict, c.subdistrict_name\n"
                    + "FROM m_votingplace a INNER JOIN votingplace_active b ON a.id_votingplace = b.id_votingplace INNER JOIN m_subdistrict c ON b.id_subdistrict = c.id_subdistrict");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Active mu = new Model_Active();
                mu.setId_votingplace_active(rs.getString("id_votingplace_active"));
                mu.setId_subdistrict(rs.getString("id_subdistrict"));
                mu.setSubdistrict_name(rs.getString("subdistrict_name"));
                mu.setId_votingplace(rs.getString("id_votingplace"));
                mu.setVotingplace_name(rs.getString("votingplace_name"));
                mu.setTotal_voter(rs.getString("total_voter"));
                listUser.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listUser;
    }

    public String addActive(Model_Active pro) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            boolean status1 = false;
            stat = conn.prepareStatement("select id_subdistrict, id_votingplace from votingplace_active where id_subdistrict = ? and id_votingplace = ?");
            stat.setString(1, pro.getId_subdistrict());
            stat.setString(2, pro.getId_votingplace());
            rs = stat.executeQuery();
            status1 = rs.next();
            if (status1 == true) {
                return status = "01";
            } else {
            stat = conn.prepareStatement("INSERT INTO votingplace_active(id_subdistrict, id_votingplace, total_voter) VALUES (?, ?, ?)");
            stat.setString(1, pro.getId_subdistrict());
            stat.setString(2, pro.getId_votingplace());
            stat.setString(3, pro.getTotal_voter());
            stat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status = "00";
    }
//
    public String deleteactive(String id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("delete from votingplace_active where id_votingplace_active = ?");
            stat.setString(1, id);
            stat.executeUpdate();
            status = "00";
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }

    public String updateactive(String id_active, String total_voter) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "01";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("UPDATE votingplace_active SET total_voter=? WHERE id_votingplace_active=?");
            stat.setString(1, total_voter);
            stat.setString(2, id_active);
            stat.executeUpdate();
            status = "00";
            stat.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return status = e.getMessage();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return status;
    }
}
