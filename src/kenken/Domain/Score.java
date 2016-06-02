/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;
/**
 *
 * @author dasilvacontin
 */
public class Score extends DomainBase {
    final private int boardId;
    final private int boardSize;
    final private int time;
    final private String username;
    
    public Score(int boardId, int boardSize, String username, int time) {
        this.boardId = boardId;
        this.boardSize = boardSize;
        this.username = username;
        this.time = time;
    }
    
    @Override
    public String serialize() {
        return super.serialize() + " " + boardId + " " + boardSize + " " + username + " " + time;
    }

    public static Score deserialize(String[] props) {
        String id = props[0];
        int boardId = Integer.parseInt(props[1]);
        int boardSize = Integer.parseInt(props[2]);
        String username = props[3];
        int time = Integer.parseInt(props[4]);
        
        Score s = new Score(boardId, boardSize, username, time);
        return s;
    }
    
    @Override
    public String toString() {
        return "[Score boardId: " + boardId + ", boardSize: " + boardSize + ", username: " + username + ", time: " + time + "]";
    }

    @Override
    public Boolean matchesQuery(String key, String value) {
        switch (key) {
            case "boardId":
                return boardId == Integer.parseInt(value);
            case "boardSize":
                return boardSize == Integer.parseInt(value);
            case "username":
                return username.equals(value);
            case "time":
                return time == Integer.parseInt(value);
            default:
                return false;
        }
    }
    
}
