package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.EventBlackOut;
import it.polito.tdp.poweroutages.model.Nerc;
import java.time.*;
import java.sql.Date;
import java.sql.Time;
public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<EventBlackOut> getEventList(String nerc) {

		String sql = "SELECT   p.event_type_id, p.date_event_began , p.date_event_finished,p.customers_affected, "
				+ "YEAR(p.date_event_began) AS anno "
				+ "FROM poweroutages p, nerc n "
				+ "WHERE n.id= p.nerc_id AND n.value=? "
				+ "";
				
		List<EventBlackOut> eventList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nerc);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				float oreDisservizio=0;
				int anno= res.getInt("anno");
				EventBlackOut e = new EventBlackOut(res.getInt("event_type_id"), res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getInt("customers_affected"),oreDisservizio, anno);
				oreDisservizio= Duration.between(e.getDate_event_began(), e.getDate_event_finished()).toHours();
				e.setOreDisservizio(oreDisservizio);
				eventList.add(e);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventList;
	}
	

}
