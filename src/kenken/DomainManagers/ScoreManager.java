/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kenken.DomainManagers;
import kenken.Domain.Score;
/**
 *
 * @author dasilvacontin
 */
public class ScoreManager extends DomainManager<Score> {
    public ScoreManager() {
        super("./data/Domain/Score.db", Score.class);
    }
}
