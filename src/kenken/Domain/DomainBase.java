/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.Domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dasilvacontin
 */
public class DomainBase {
    public String serialize() {
        return "domain-base";
    }
    
    public static DomainBase deserialize(String str) {
        DomainBase obj = new DomainBase();
        return obj;
    }
    
    public String description() {
        return "kek";
    }
    
    public Boolean matchesQuery(String key, String value) {
        return false;
    }
}
