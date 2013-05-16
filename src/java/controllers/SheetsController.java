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
import models.CharacterSheet;
import models.Feat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sergi_000
 */
@Controller
@RequestMapping("/sheets")
public class SheetsController {
    
    private void mapSheets(ModelMap map) {
        CharacterSheet.Load();
        Collection<CharacterSheet> sheets = CharacterSheet.getSheets().values();
        
        if(sheets.isEmpty()){
            System.err.println("Sheets are empty");
        }
        List<CharacterSheet> sheetList = new ArrayList<CharacterSheet>();
        sheetList.addAll(sheets);
        Collections.sort(sheetList, new CharacterSheetComparator());
        
        map.addAttribute("sheets", sheetList);
    }
    
    
        @RequestMapping(method = RequestMethod.GET)
        public String list(ModelMap map) {
        
        mapSheets(map);
        return "sheets";
     }
}


class CharacterSheetComparator implements Comparator<CharacterSheet> {
    @Override
    public int compare(CharacterSheet o1, CharacterSheet o2) {
        return o1.getName().compareTo(o2.getName());
    }
}