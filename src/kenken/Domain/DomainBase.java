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
public class DomainBase {
    
    public String _id;
    
    public String serialize() {
        if (_id == null) {
            return "[Missing _id value]";
        } else {
            return _id;
        }
    }
    
    public static DomainBase deserialize(String[] props) throws Exception {
        throw new Exception("Method not meant to be used.");
    }
    
    public Boolean matchesQuery(String key, String value) throws Exception {
        throw new Exception("Method not meant to be used.");
    }
}
