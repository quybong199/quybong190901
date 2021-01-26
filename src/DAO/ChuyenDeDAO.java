/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.chuyende;

/**
 *
 * @author Quy Bong
 */
public class ChuyenDeDAO {
    public void insert(chuyende model) {
            String sql = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
            JdbcHelper.executeUpdate(sql, model.getMaCD(), model.getTenCD(), model.getHocPhi(), model.getThoiLuong(), model.getHinh(), model.getMoTa());
        }

        public void update(chuyende model) {
            String sql = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
            JdbcHelper.executeUpdate(sql, model.getTenCD(), model.getHocPhi(), model.getThoiLuong(), model.getHinh(), model.getMoTa(), model.getMaCD());
        }

        public void delete(String MaCD) {
            String sql = "DELETE FROM ChuyenDe WHERE MaCD=?";
            JdbcHelper.executeUpdate(sql, MaCD);
        }

        public List<chuyende> select() {
            String sql = "SELECT * FROM ChuyenDe";
            return select(sql);
        }

        public chuyende findById(String macd) {
            String sql = "SELECT * FROM ChuyenDe WHERE MaCD=?";
            List<chuyende> list = select(sql, macd);
            return list.size() > 0 ? list.get(0) : null;
        }

        private List<chuyende> select(String sql, Object... args) {
            List<chuyende> list = new ArrayList<>();
            try {
                ResultSet rs = null;
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                     
 
                while (rs.next()) {
                        chuyende model = readFromResultSet(rs);
                        list.add(model);
                    }
                } finally {
                    rs.getStatement().getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return list;
        }

        private chuyende readFromResultSet(ResultSet rs) throws SQLException {
            chuyende model = new chuyende();
            model.setMaCD(rs.getString("MaCD"));
            model.setHinh(rs.getString("Hinh"));
            model.setHocPhi(rs.getDouble("HocPhi"));
            model.setMoTa(rs.getString("MoTa"));
            model.setTenCD(rs.getString("TenCD"));
            model.setThoiLuong(rs.getInt("ThoiLuong"));
            return model;
        }
}
