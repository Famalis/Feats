/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author sergi_000
 */
public class FeatNode {

    private static Long maxId = 0L;
    private static Map<Long, FeatNode> nodes = new Hashtable<Long, FeatNode>();
    private static ArrayList<Long> containedFeats = new ArrayList<Long>();
    //private Integer tier = 0;
    private Long id = 0L;
    private ArrayList<Long> parents = new ArrayList<Long>();
    private Long feat = 0L;
    private ArrayList<Long> children = new ArrayList<Long>();

    public static void initData() {
        for (Feat f : Feat.getFeats().values()) {
        }
    }
    
    public String getFeatName() {
        return Feat.getFeats().get(feat).getName();
    }

    public Integer getTier() {
        Integer tier = 1;
        if (!parents.isEmpty()) {
            int tmpTier = 0;
            int maxTier = 0;
            for (Long parentId : parents) {
                tmpTier = nodes.get(parentId).getTier();
                if (maxTier < tmpTier) {
                    maxTier = tmpTier;
                }

            }
            tier += maxTier;
        }

        return tier;
    }

    public FeatNode(Long feat) {
        maxId++;
        this.id = maxId;
        this.feat = feat;
        containedFeats.add(feat);
        nodes.put(id, this);
        if (!Feat.findFeatById(feat).getRequiredFor().isEmpty()) {
            for (Long id : Feat.findFeatById(feat).getRequiredFor()) {
                Feat getFeat = Feat.getFeats().get(id);
                children.add(getNode(this.id, getFeat.getId()).getId());
            }
        }

    }

    private static FeatNode getNode(Long parentId, Long featId) {
        if (containedFeats.contains(featId)) {
            for (FeatNode n : nodes.values()) {
                if (n.getFeat() == featId) {
                    nodes.get(n.getId()).getParents().add(parentId);
                    System.out.println("Node returned withour creating new");
                    System.out.println("Parents updates for: " + n.getFeat() + " New parents size: " + n.getParents().size());
                    return nodes.get(n.getId());
                }
            }
        }
        System.out.println("New feat created as child for " + nodes.get(parentId));
        return new FeatNode(parentId, featId);
    }

    public FeatNode(Long parentId, Long featId) {


        maxId++;
        this.id = maxId;
        this.feat = featId;
        this.parents.add(parentId);
        containedFeats.add(feat);
        nodes.put(id, this);
        if (!Feat.findFeatById(feat).getRequiredFor().isEmpty()) {
            for (Long id : Feat.findFeatById(feat).getRequiredFor()) {
                Feat getFeat = Feat.getFeats().get(id);
                children.add(getNode(this.id, getFeat.getId()).getId());
            }
        }


    }

    public String htmlTable() {
        String result = "<table style=\"padding: 10px;\" border='1'>\n"
                + "<tr>\n"
                + "<td colspan='" + 15 + "'>\n"
                + "<p style=\"text-align: center;\">"
                + "<a href=\"/Feats/feats/" + feat + "\" style='color:blue'>" + Feat.findFeatById(feat).getName() + " "+this.getTier()+"</a>"
                + "</p>\n"
                + "</td>\n"
                + "</tr>\n";
        if (!children.isEmpty()) {
            result += "<tr>\n";
            for (Long n : children) {
                result += "<td>\n" + nodes.get(n).htmlTable() + "\n</td>";
            }
            result += "</tr>\n";
        }
        result += "</table>";
        return result;
    }

    public String listTree(String prefix) {
        String result = Feat.findFeatById(feat).getName() + "\n";
        for (int i = 0; i < children.size(); i++) {
            /*for (int j = 0; j<i+1; j++) {
             result+="-";
             }
             * */
            result += prefix + (i + 1) + " " + nodes.get(children.get(i)).listTree("-" + prefix);
        }

        return result;
    }

    public ArrayList<Feat> getAllChildren() {
        ArrayList<Feat> allChildren = new ArrayList<Feat>();
        allChildren.add(Feat.findFeatById(feat));
        for (Long n : this.children) {
            allChildren.addAll(nodes.get(n).getAllChildren());
        }

        return allChildren;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Long> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Long> parents) {
        this.parents = parents;
    }

    public Long getFeat() {
        return feat;
    }

    public void setFeat(Long feat) {
        this.feat = feat;
    }

    public ArrayList<Long> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Long> children) {
        this.children = children;
    }

    public static Long getMaxId() {
        return maxId;
    }

    public static Map<Long, FeatNode> getNodes() {
        return nodes;
    }

    public static ArrayList<Long> getContainedFeats() {
        return containedFeats;
    }
}
