
package edu.esprit.gui;

import edu.esprit.entities.Question;

/**
 *
 * @author MSI
 */
public final class QuestHolder {
    private Question quest;
    private final static QuestHolder INSTANCE =new QuestHolder();

    public QuestHolder() {
    }
    
    public static QuestHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setQuest (Question s){
        this.quest =s;
    }
    
        public Question getQuest (){
        return  this.quest;
    }
}
