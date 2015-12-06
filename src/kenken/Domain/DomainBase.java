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
public class DomainBase implements Serializable {
    public DomainBase(List<String> data) {
        // nada
    }
    public String description() {
        return "kek";
    }
}
