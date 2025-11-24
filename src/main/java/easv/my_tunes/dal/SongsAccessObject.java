package easv.my_tunes.dal;

import easv.my_tunes.be.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsAccessObject {

    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection()){
            String sqlPrompt = "SELECT * FROM songs";
            PreparedStatement pst = con.prepareStatement(sqlPrompt);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String category = rs.getString("category");
                int time = rs.getInt("time");
                String path = rs.getString("path");
                songs.add(new Song(id, title, artist, category, time, path));
            }
            return songs;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}