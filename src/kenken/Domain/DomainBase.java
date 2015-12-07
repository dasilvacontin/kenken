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
public abstract class DomainBase {
    public String _id;
    public abstract String serialize();
    public static DomainBase deserialize(String[] props) { return null; }
    public abstract Boolean matchesQuery(String key, String value);
}
