/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class Feat implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String description;
    private List<String> storyRequirements;
    private List<Long> requiredFeats;
    
    private static String filePath = "feats";
    private static Map<Long, Feat> feats = new Hashtable<Long, Feat>();
    private static Long maxId;
    private static String url = "jdbc:mysql://famalis.no-ip.biz:3306/rpg?useUnicode=true&characterEncoding=utf8";
    private static String login = "rpg";
    private static String pass  = "A9A6pq5AXPXEsbDZ";
  
    public Feat() {
        super();
        init();
    }
    
    public final void init() {
        id = (long)0;
        storyRequirements = new ArrayList<String>();
        requiredFeats = new ArrayList<Long>();
    }
    
    public String toHtmlString(){
        String str = "";
        str+="<b>"+name+"</b><hr id='featLine'/>"+"<b>"+"Wymagania:</b> ";
        int count = 0;
        for (String s : storyRequirements) {
            str+="<font color=purple>";
            str+=s+", ";
            str+="</font>";
            count++;
        }        
        for (Long id : requiredFeats) {
            str+="<a href="+id+" style='color:blue'>"+Feat.findFeatById(id).getName()+"</a>, ";
            count++;
        }
        if(count==0) {
            str+="Brak";
        }
        str+="<br/><b>Opis</b>: "+this.description+"<br/>";
        return str;
    }
    
    public static boolean Save() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(feats);
            oos.writeObject(maxId);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("exception while saving: "+e);
            return false;
        }
        
        return true;
    }
    public static boolean loadFromSQL() {
        
        feats.clear();
        Connection c = RpgConnection.getConnection();
        
        if(c!=null) {
            Statement s = null;
            ResultSet results = null;
            try {
                s = c.createStatement();
                results = s.executeQuery("SELECT * FROM feats");
                
                
            } catch (Exception e) {
                e.printStackTrace();
                RpgConnection.closeConnection(c);
                return false;
            }
            
            try {
                while(results.next()){
                    Feat f = new Feat();
                    f.setId(results.getLong("id"));
                    f.setName(results.getString("name"));
                    f.setDescription(results.getString("description"));
                    
                    String storyReqString = results.getString("story_req");
                    String[] storyReqArr  = storyReqString.split(";");
                    System.out.println(f.getId()+" "+f.getName());
                    for (int i = 0; i<storyReqArr.length ; i++) {
                        if(!"".equals(storyReqArr[i])) {
                            f.addStoryRequirement(storyReqArr[i]);
                            System.out.println("    "+storyReqArr[i]);
                        }
                    }
                    
                    String featReqString = results.getString("feat_req");
                    String[] featReqArr  = featReqString.split(";");
                    for(int i = 0; i<featReqArr.length; i++) {
                        if(!"".equals(featReqArr[i])) {
                            Long id = Long.parseLong(featReqArr[i]);
                            f.addRequiredFeat(id);
                            System.out.println("    "+id);
                        } else {
                        }
                    }
                    feats.put(f.getId(), f);
                }
                
            } catch (Exception e) {
                System.err.println("ResultSet reading error");
                e.printStackTrace();  
                RpgConnection.closeConnection(c);
                return false;
            }
            
            
        }
        RpgConnection.closeConnection(c);
        return true;
    }
    public static boolean Load() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File file = null;
        try {
            file = new File(filePath);
            file.createNewFile();
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            feats = (Hashtable<Long, Feat>) ois.readObject();
            maxId = (Long)ois.readObject();
            ois.close();
            fis.close();
        } catch (EOFException eof) {
           System.out.println("New memory file was created");
        } catch (Exception e) {
            System.err.println("Exception while loading: "+e);
            return false;
        } 
        
        return true;
    }
    public static Feat findFeatById(Long id) {
        return feats.get(id);
    }
    
    public static Feat findFeatByName(String name) {
        Feat[] arr = (Feat[]) feats.values().toArray();
        for (Feat f : arr) {
            if(f.getName().toLowerCase().equals(name.toLowerCase())){
                return f;
            }
        }
        
        return null;
        
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getStoryRequirements() {
        return storyRequirements;
    }

    public void setStoryRequirements(List<String> storyRequirements) {
        this.storyRequirements = storyRequirements;
    }
    

    public List<Long> getRequiredFeats() {
        return requiredFeats;
    }

    public void setRequiredFeats(List<Long> requiredFeats) {
        this.requiredFeats = requiredFeats;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        Feat.filePath = filePath;
    }

    public static Map<Long, Feat> getFeats() {
        return feats;
    }
    
    public static Long getMaxId() {
        return maxId;
    }
    
    public void addRequiredFeat(Long id) {
        this.requiredFeats.add(id);
    }
    
    public void addStoryRequirement(String requirement) {
        this.storyRequirements.add(requirement);
    }

    public void loadFeat(Long id) {
        
    }
    
}


