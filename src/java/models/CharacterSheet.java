/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import utils.RpgConnection;

/**
 *
 * @author sergi_000
 */

public class CharacterSheet {
    
    private Long id;
    private String name;
    private List<Long> feats;
    private Integer exp;
    private String description;
    private String notes;
    private String playerName;
    
    private static Map<Long, CharacterSheet> sheets = new Hashtable<Long, CharacterSheet>();
    
    public CharacterSheet() {
        
    }
    
    public static Map<Long, CharacterSheet> getSheets() {
        return sheets;
    }
    
    public String getFeatHtmlString() {
        String str = "";
        Feat.loadFromSQL();
        for (Long l : feats) {
            System.out.println(l+"");
            str+=Feat.findFeatById(l).getName()+"<br/><hr/>";
            
        }
        return str;
    }
    
    public static boolean Load() {
        sheets.clear();
        Connection c = RpgConnection.getConnection();
        
        if(c!=null) {
            Statement s = null;
            ResultSet results = null;
            
            try {
                s = c.createStatement();
                results = s.executeQuery("SELECT * FROM character_sheets");
                
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            
            try {
                while(results.next()) {
                    CharacterSheet cs = new CharacterSheet();
                    cs.init();
                    cs.setId(results.getLong("id"));
                    cs.setName(results.getString("name"));
                    cs.setDescription(results.getString("description"));
                    cs.setExp(results.getInt("exp"));
                    cs.setNotes(results.getString("notes"));
                    cs.setPlayerName(results.getString("player"));
                    
                    String featsString = results.getString("feats");
                    String[] featsArr = featsString.split(";");
                    
                    for(int i = 0; i<featsArr.length; i++) {
                        if(!"".equals(featsArr[i])) {
                            System.out.println(cs.getName()+" "+featsArr[i]);
                            Long id = Long.parseLong(featsArr[i]);
                            cs.getFeats().add(id);
                        } else {
                        }
                    }
                    
                    sheets.put(cs.getId(), cs);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        
        return true;
        
    }
    
    public void init() {
        id = 0L;
        name = "";
        feats = new ArrayList<Long>();
        exp = 0;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getFeats() {
        return feats;
    }

    public void setFeats(List<Long> feats) {
        this.feats = feats;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    
    
    
    
}
