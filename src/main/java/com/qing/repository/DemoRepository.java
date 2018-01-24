package com.qing.repository;

import com.qing.entity.Demo;
import com.qing.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.repository</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class DemoRepository {

    public List<Demo> findList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from demo order by demo_id desc ";
        List<Demo> demos = new ArrayList<>();
        try {
            connection = DbUtil.open();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Demo demo = new Demo();
                demo.setId(resultSet.getLong("demo_id"));
                demo.setAddress(resultSet.getString("demo_name"));
                demo.setAge(resultSet.getInt("demo_id"));
                demo.setName(resultSet.getString("nick_name"));
                demos.add(demo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return demos;
    }
}
