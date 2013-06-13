/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.Feat;
import models.FeatNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sergi_000
 */
@Controller
@RequestMapping("/tree")
public class TreeController {
    
    @RequestMapping(value = "/tree/full", method = RequestMethod.GET)
    public String fullTree(ModelMap map) {
        if (Feat.getFeats().isEmpty()) {
            Feat.loadFromSQL();
        }
        ArrayList<FeatNode> nodes = new ArrayList<FeatNode>();
        ArrayList<Integer> tiers = new ArrayList<Integer>();
        int count = 0;
        if (FeatNode.getNodes().isEmpty()) {
            for (Feat f : Feat.getFeats().values()) {
                if (f.getRequiredFeats().isEmpty()) {
                    System.out.println("Making node for " + f.getName());
                    if (!FeatNode.getContainedFeats().contains(f.getId())) {
                        nodes.add(new FeatNode(f.getId()));
                        count++;
                    }
                    
                }
            }
        } else {
            nodes.addAll(FeatNode.getNodes().values());
        }
        System.out.println(count + " == " + nodes.size() + " == " + FeatNode.getNodes().values().size());
       
        tiers.add(1);
        tiers.add(2);
        tiers.add(3);
        tiers.add(4);
        tiers.add(5);
        tiers.add(6);
        System.out.println(nodes.size());
        map.addAttribute("tiers", tiers);
        map.addAttribute("nodeArr", nodes);
        return "fulltree";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String generalTree(ModelMap map) {
        if (Feat.getFeats().isEmpty()) {
            Feat.loadFromSQL();
        }
        ArrayList<FeatNode> nodes = new ArrayList<FeatNode>();
        int count = 0;
        if (FeatNode.getNodes().isEmpty()) {            
            for (Feat f : Feat.getFeats().values()) {
                if (f.getRequiredFeats().isEmpty()) {
                    System.out.println("Making node for " + f.getName());
                    if (!FeatNode.getContainedFeats().contains(f.getId())) {
                        nodes.add(new FeatNode(f.getId()));
                        count++;
                    }
                    
                }
            }
        } else {
            nodes.addAll(FeatNode.getNodes().values());
        }
        System.out.println(count + " == " + nodes.size() + " == " + FeatNode.getNodes().values().size());
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).getParents().isEmpty()) {
                System.out.println(Feat.getFeats().get(nodes.get(i).getFeat()).getName());
                nodes.remove(i);
            }
        }
        System.out.println(nodes.size());
        map.addAttribute("nodeArr", nodes);
        return "tree";
    }
    
    @RequestMapping(value = "/tree/{id}", method = RequestMethod.GET)
    public String featTree(@PathVariable(value = "id") String idStr, ModelMap map) {
        Long id = Long.parseLong(idStr);
        Feat f = null;
        if (Feat.getFeats().isEmpty()) {
            Feat.loadFromSQL();
        }
        f = Feat.findFeatById(id);
        FeatNode fn = new FeatNode(f.getId());
        FeatNode[] arr = new FeatNode[1];
        arr[0] = fn;
        //System.out.println(fn.listTree("-"));
        map.addAttribute("nodeArr", arr);
        return "tree";
    }
}
