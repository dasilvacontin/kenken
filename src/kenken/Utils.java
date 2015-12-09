/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author dasilvacontin
 */
public class Utils {
    public static <T> LinkedList<T> asLinkedList(T...elements) {
        LinkedList<T> newList = new LinkedList<>();
        newList.addAll(Arrays.asList(elements));
        return newList;
    }
}
