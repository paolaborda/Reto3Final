
package com.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  
public class serviciosSkate {
    
    @Autowired   
    private SkateRepository metodoscrud;
    
    public List<Skate> getAll(){
        return metodoscrud.getAll();
    }   
    
    public Optional<Skate> getSkate(int id){
        return metodoscrud.getSkate(id); 
    }
    
    public Skate save( Skate skate){
        if (skate.getId()==null){
         return metodoscrud.save(skate);
         }else{
            Optional<Skate> evt=metodoscrud.getSkate(skate.getId());
            if(evt.isEmpty()){
            return metodoscrud.save(skate);    
            }else{
                return skate;
            }
        }
    } 
    
}
