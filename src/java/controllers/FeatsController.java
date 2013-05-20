/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import models.Feat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import utils.RpgConnection;



/**
 *
 * @author sergi_000
 */
@Controller
@RequestMapping("/feats")
public class FeatsController {
    
    Long selectedFeatId;
    
    private void mapFeatList(ModelMap map) { 
        Collection<Feat> feats = Feat.getFeats().values();
        
        
        if(feats.isEmpty()){
            System.out.println("Loading feats from database");
            Feat.loadFromSQL();
            if(feats.isEmpty()) {
                System.err.println("No feats in database");
            }
        }
        List<Feat> featList = new ArrayList<Feat>();
        featList.addAll(feats);
        Collections.sort(featList, new FeatComparator());
        map.addAttribute("visits",(RpgConnection.getVisits()));
        map.addAttribute("feats", featList);
    }
    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap map) {
        System.out.println(request.getRemoteAddr());
        RpgConnection.registerLog(request.getRemoteAddr());
        mapFeatList(map);
        return "list";
    }
    
    
    @RequestMapping(value="/feats/{id}", method = RequestMethod.GET)
    public String feat(@PathVariable(value="id") String idStr, ModelMap map) {
        
        
        mapFeatList(map);
        Long id = Long.parseLong(idStr);
        selectedFeatId = id;
        map.addAttribute("feat", Feat.findFeatById(id));
        return "list";
    }
    
    @RequestMapping(method = RequestMethod.POST, params="featData")
    public String updateFeat(@RequestParam("featData") String[] featData, ModelMap map) {  
        
        map.addAttribute("feat", Feat.findFeatById(selectedFeatId));
        mapFeatList(map);
        return "list";
      
    }
    
    @RequestMapping(value="/feats/reload", method = RequestMethod.GET)
    public String reload(ModelMap map) {
        
        Feat.loadFromSQL();
        mapFeatList(map);        
        return "list";
    }
    
   
}
class FeatComparator implements Comparator<Feat> {
    @Override
    public int compare(Feat o1, Feat o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
