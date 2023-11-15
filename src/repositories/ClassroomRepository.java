package repositories;

import dto.Classroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository {
    private final Connection conn;
    private final Statement stmt;

    public ClassroomRepository(Connection conn, Statement stmt) {
        this.conn = conn;
        this.stmt = stmt;
    }

    public List<Classroom> getClassroomByBuilding(int buildingNumber) throws SQLException {
        String sql = "SELECT ROOM_CODE, NAME FROM CLASSROOM WHERE BUILDING_NUMBER = ? ";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, buildingNumber);
        ResultSet rs = ps.executeQuery();

        List<Classroom> building = new ArrayList<>();
        while(rs.next()) {
            building.add(new Classroom(
                    rs.getInt(1)
            ));
        }

        rs.close();
        ps.close();

        return building;
    }

    public List<Classroom> getBuildings() throws SQLException {
        String sql = "SELECT DISTINCT BUILDING_NUMBER FROM CLASSROOM ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Classroom> building = new ArrayList<>();
        while(rs.next()) {
            building.add(new Classroom(
                    rs.getInt(1)
            ));
        }

        rs.close();
        ps.close();

        return building;
    }

}
